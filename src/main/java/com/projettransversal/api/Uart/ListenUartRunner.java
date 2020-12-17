package com.projettransversal.api.Uart;

import com.projettransversal.api.MQTT.MQTTService;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import com.projettransversal.api.Models.MapItem;
import com.projettransversal.api.Services.Services.IncidentService;
import com.projettransversal.api.Services.Services.MapItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListenUartRunner implements CommandLineRunner {

    @Value("${uart.comm.port}")
    String uartPort;

    private IncidentService _incidentService;
    private MapItemService _mapItemService;
    private MQTTService _mqttService;

    public ListenUartRunner(IncidentService incidentService, MapItemService mapItemService, MQTTService mqttService) {
        _incidentService = incidentService;
        _mapItemService = mapItemService;
        _mqttService = mqttService;
    }


    @Override
    @Async
    public void run(String... args) throws Exception {
        _mqttService.sendToBroker("mes feux");
        /*try {
            SerialPort comPort = SerialPort.getCommPort(uartPort);
            comPort.setComPortParameters(115200,8,1,0);
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,0,0);
            comPort.openPort();
            System.out.println("Listen on : " + comPort.getSystemPortName());

            while (true)
            {
                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(20);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                comPort.readBytes(readBuffer, readBuffer.length);

                String data = new String(readBuffer, StandardCharsets.UTF_8);

                data = data.replaceAll(" ", "");
                data = data.replaceAll("\"", "");
                data = data.replaceAll("'", "");
                data = data.replaceAll("(?:\\n|\\r)", "");
                System.out.println("Received by UART on port " + comPort.getSystemPortName() + " : " + data);

                String[] incidentsTypes = data.split("&");
                for (String incidentsType : incidentsTypes) {
                    String[] incidents = incidentsType.split("/");

                    for (int i = 1; i < incidents.length ; i++ ) {
                        List<Integer> positionsList = new ArrayList<Integer>();

                        for (String position : incidents[i].split(",")) {
                            positionsList.add(Integer.parseInt(position));
                        }

                        insert(incidents[0], positionsList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in getting COM connexion");
        }*/
    }

    public void insert(String type, List<Integer> positions){
        MapItem mapItem = _mapItemService.findByCoordinates(positions.get(0), positions.get(1));
        if (mapItem == null) {
            System.out.println("No mapItem found for these coordinates. Return");
            return;
        }

        Incident incident = new Incident();
        incident.setMapItem(mapItem);
        incident.setIntensity(positions.get(2));

        switch(type) {
            case "F":
                incident.setIncidentType(IncidentType.FIRE);
                break;
            case "I":
                incident.setIncidentType(IncidentType.FLOOD);
                break;
            case "A":
                incident.setIncidentType(IncidentType.ACCIDENT);
                break;
            case "T":
                incident.setIncidentType(IncidentType.TORNADO);
                break;
        }

        List<Incident> similarIncidents = _incidentService.findByData(incident);
        if (similarIncidents.size() == 0) {
            _incidentService.insertOrUpdate(incident);
            System.out.println("Incident inserted");
        } else {
            System.out.println("Incident already present ! Not inserted");
        }
    }
}

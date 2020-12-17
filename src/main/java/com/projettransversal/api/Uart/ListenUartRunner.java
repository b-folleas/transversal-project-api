package com.projettransversal.api.Uart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        try {
            SerialPort comPort = SerialPort.getCommPort(uartPort);
            comPort.setComPortParameters(115200,8,1,0);
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,0,0);
            comPort.openPort();
            StringBuilder msg = new StringBuilder();
            System.out.println("Listen on : " + comPort.getSystemPortName());

            // F/1,1,1/2,2,2/3,3,3&I/4,4,4/6,6,6
            while (true)
            {
                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(20);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                comPort.readBytes(readBuffer, readBuffer.length);

                String data = new String(readBuffer, StandardCharsets.UTF_8);
                List<Incident> incidents = new ArrayList<Incident>();

                data = data.replaceAll(" ", "");
                data = data.replaceAll("\"", "");
                data = data.replaceAll("'", "");
                data = data.replaceAll("(?:\\n|\\r)", "");
                msg.append(data);
                System.out.println("Received by UART on port " +
                        comPort.getSystemPortName() + " : " + data +
                        ", current message is : " + msg.toString());

                // message not complete
                if (msg.charAt(msg.length() - 1) != '$') {
                    continue;
                }

                // remove $
                msg = new StringBuilder(msg.substring(0, msg.length() - 1));

                System.out.println("Complete message received : " + msg.toString());

                String[] incidentsTypes = msg.toString().split("&");
                msg = new StringBuilder();
                for (String incidentsType : incidentsTypes) {
                    String[] incidentsList = incidentsType.split("/");

                    for (int i = 1; i < incidentsList.length ; i++ ) {
                        List<Integer> positionsList = new ArrayList<Integer>();

                        for (String position : incidentsList[i].split(",")) {
                            positionsList.add(Integer.parseInt(position));
                        }

                        incidents.add(mapToIncident(incidentsList[0], positionsList));
                    }
                }

                List<Incident> incidentsToAdd = new ArrayList<Incident>();
                for (Incident incident : incidents) {
                    List<Incident> similarIncidents = _incidentService.findByData(incident);
                    if (similarIncidents.size() == 0) {
                        incidentsToAdd.add(incident);
                    } else {
                        System.out.println("Incident already present ! Not inserted");
                    }
                }

                _incidentService.insertOrUpdateMultiple(incidentsToAdd);
                System.out.println(incidentsToAdd.size() + " incidents inserted");
                System.out.println(new ObjectMapper().writeValueAsString(incidentsToAdd));
                // _mqttService.sendToBroker(new ObjectMapper().writeValueAsString(incidentsToAdd));
            }
        } catch (Exception e) {
            System.out.println("Error in getting COM connexion");
        }
    }

    public Incident mapToIncident(String type, List<Integer> positions){
        MapItem mapItem = _mapItemService.findByCoordinates(positions.get(0), positions.get(1));
        if (mapItem == null) {
            System.out.println("No mapItem found for these coordinates. Return");
            return null;
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

        return incident;
    }
}

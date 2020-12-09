package com.projettransversal.api.Uart;

import com.fazecast.jSerialComm.SerialPort;
import com.projettransversal.api.Services.Services.IncidentService;
import com.projettransversal.api.Services.Services.MapItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListenUartRunner implements CommandLineRunner {

    private IncidentService _incidentService;
    private MapItemService _mapItemService;

    public ListenUartRunner(IncidentService incidentService, MapItemService mapItemService) {
        _incidentService = incidentService;
        _mapItemService = mapItemService;
    }

    @Override
    @Async
    public void run(String... args) throws Exception {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        try {
            while (true)
            {
                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(20);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                comPort.readBytes(readBuffer, readBuffer.length);

                String data = new String(readBuffer, StandardCharsets.UTF_8);
                System.out.println(data);

                // F/1,1,1/2,2,2/3,3,3&I/4,4,4/6,6,6

                data = data.replaceAll(" ", "");
                data = data.replaceAll("\"", "");
                data = data.replaceAll("'", "");
                data = data.replaceAll("(?:\\n|\\r)", ""); ;

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
        }
        comPort.closePort();
    }

    public void insert(String type, List<Integer> positions){
        System.out.println(type + " " + positions.get(0) + " " + positions.get(1) + " " + positions.get(2));

        /*_mapItemService.
        Incident incident = new Incident();

        switch(type) {
            case "F":
                _incidentService.insertOrUpdate(null);
                break;
        }
        _incidentService.insertOrUpdate(null);*/
    }
}
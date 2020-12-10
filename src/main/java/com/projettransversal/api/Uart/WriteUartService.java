package com.projettransversal.api.Uart;

import com.fazecast.jSerialComm.SerialPort;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Models.IncidentType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WriteUartService {

    @Async
    public void write(List<Incident> incidents) {
        SerialPort comPort = SerialPort.getCommPort("COM3");
        comPort.setComPortParameters(9600,8,1,0);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,0,0);
        comPort.openPort();
        System.out.println("Opening port to write : " + comPort.getSystemPortName());
        try {
            String formattedData = formatData(incidents);
            System.out.println(formattedData);
            byte[] buffer = formattedData.getBytes(StandardCharsets.UTF_8);
            if(comPort.writeBytes(buffer, buffer.length) == -1) {
                System.out.println("Error in sending UART to port " + comPort.getSystemPortName());
            } else {
                System.out.println("Data sent by port " + comPort.getSystemPortName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            comPort.closePort();
        }
    }

    public String formatData(List<Incident> incidents) {
        StringBuilder returnString = new StringBuilder();

        List<Incident> fires = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.FIRE)
                .collect(Collectors.toList());

        List<Incident> floods = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.FLOOD)
                .collect(Collectors.toList());

        List<Incident> accidents = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.ACCIDENT)
                .collect(Collectors.toList());

        List<Incident> tornados = incidents
                .stream()
                .filter(i -> i.getIncidentType() == IncidentType.TORNADO)
                .collect(Collectors.toList());


        if (fires.size() > 0) {
            returnString.append("F");
            addIncidents(fires, returnString);
        }

        if (floods.size() > 0) {
            if (!returnString.toString().isEmpty()) {
                returnString.append("&");
            }

            returnString.append("I");
            addIncidents(floods, returnString);
        }

        if (accidents.size() > 0) {
            if (!returnString.toString().isEmpty()) {
                returnString.append("&");
            }

            returnString.append("A");
            addIncidents(accidents, returnString);
        }

        if (tornados.size() > 0) {
            if (!returnString.toString().isEmpty()) {
                returnString.append("&");
            }

            returnString.append("T");
            addIncidents(tornados, returnString);
        }

        return returnString.toString();
    }

    private void addIncidents(List<Incident> incidents, StringBuilder returnString) {
        for (Incident incident : incidents) {
            returnString.append("/");
            returnString.append(String.valueOf(incident.getMapItem().getPosX()));
            returnString.append(",");
            returnString.append(String.valueOf(incident.getMapItem().getPosX()));
            returnString.append(",");
            returnString.append(String.valueOf(Math.round(incident.getIntensity())));
        }
    }
}

package com.projettransversal.api.Uart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.SerialPort;
import com.projettransversal.api.MQTT.MQTTService;
import com.projettransversal.api.Models.Incident;
import com.projettransversal.api.Services.Services.IncidentService;
import com.projettransversal.api.Services.Services.MapItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class ListenUartRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ListenUartRunner.class);

    @Value("${uart.comm.port}")
    String uartPort;

    @Value("${uart.enable}")
    Boolean uartEnable;

    private final IncidentService _incidentService;
    private final MapItemService _mapItemService;
    private final MQTTService _mqttService;

    public ListenUartRunner(IncidentService incidentService, MapItemService mapItemService, MQTTService mqttService) {
        _incidentService = incidentService;
        _mapItemService = mapItemService;
        _mqttService = mqttService;
    }


    @Override
    @Async
    public void run(String... args) throws Exception {
        if(this.uartEnable) {
            try {
                UartHelper uartHelper = new UartHelper(_mapItemService, _incidentService);

                SerialPort comPort = SerialPort.getCommPort(uartPort);
                comPort.setComPortParameters(115200,8,1,0);
                comPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,0,0);
                comPort.openPort();
                StringBuilder msg = new StringBuilder();
                logger.info("Listen on : " + comPort.getSystemPortName());

                // F/1,1,1/2,2,2/3,3,3&I/4,4,4/6,6,6@
                while (true)
                {
                    try {
                        while (comPort.bytesAvailable() == 0) {
                            Thread.sleep(20);
                        }

                        byte[] readBuffer = new byte[comPort.bytesAvailable()];
                        comPort.readBytes(readBuffer, readBuffer.length);
                        String data = new String(readBuffer, StandardCharsets.UTF_8);
                        data = uartHelper.sanitizeMessage(data);
                        msg.append(data);

                        logger.info("Received by UART on port " +
                                comPort.getSystemPortName() + " : " + data +
                                ", current message is : " + msg.toString());

                        StringBuilder msgFull;
                        String msgContent = msg.toString();
                        if(msgContent.contains("@")) {
                            msgFull = new StringBuilder(msgContent.split("@")[0]);
                            msg = (msgContent.split("@").length > 1) ?
                                    new StringBuilder(msgContent.split("@")[1]) :
                                    new StringBuilder();
                        } else {
                            continue;
                        }

                        logger.info("Complete message received : " + msgFull.toString());

                        List<Incident> incidents = uartHelper.mapMessageToIncidents(msgFull.toString());
                        List<Incident> incidentsToAdd = uartHelper.keepNewIncidents(incidents);

                        _incidentService.insertOrUpdateMultiple(incidentsToAdd);
                        logger.info(incidentsToAdd.size() + " incidents inserted");

                        _mqttService.sendToBroker(new ObjectMapper().writeValueAsString(incidentsToAdd));
                        logger.info(incidentsToAdd.size() + " incidents send by MQTT");
                    } catch (Exception e) {
                        logger.error("Error in recieving packet");
                        msg = new StringBuilder();
                    }
                }
            } catch (Exception e) {
                logger.error("Error in getting COM connexion");
            }
        } else {
            logger.info("Listen to uart not enable");
        }
    }
}

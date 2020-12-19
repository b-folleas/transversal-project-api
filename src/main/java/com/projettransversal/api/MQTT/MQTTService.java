package com.projettransversal.api.MQTT;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MQTTService {

    private final Logger logger = LoggerFactory.getLogger(MQTTService.class);


    public MQTTService() {
    }

    public void sendToBroker(String payload) {
        try {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(payload.getBytes());
            MQTTBuilder.getInstance().publish("content", mqttMessage);
        } catch (MqttException d) {
            logger.info("Error in sending data to brocker");
        }
    }
}

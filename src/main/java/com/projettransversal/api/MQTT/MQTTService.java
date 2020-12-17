package com.projettransversal.api.MQTT;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MQTTService {

    public MQTTService() {
    }

    public void sendToBroker(String payload) {
        try {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(payload.getBytes());
            MQTTBuilder.getInstance().publish("content", mqttMessage);
            System.out.println("Data send to brocker successfully");
        } catch (MqttException d) {
            System.out.println("Error in sending data to brocker");
        }
    }
}

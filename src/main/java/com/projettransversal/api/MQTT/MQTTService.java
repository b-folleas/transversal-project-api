package com.projettransversal.api.MQTT;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.stereotype.Service;

@Service
public class MQTTService {

    public MQTTService() {
    }

    public void publish(String payload) throws MqttPersistenceException, MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        MQTTBuilder.getInstance().publish("content", mqttMessage);
    }
}

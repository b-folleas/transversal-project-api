package com.projettransversal.api.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQTTBuilder {

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.broker.connexion-string}")
    private String brockerAddress;

    private static String clientIdStatic;
    private static String brockerAddressStatic;

    @Value("${mqtt.client.id}")
    public void setClientIdStatic(String id){
        MQTTBuilder.clientIdStatic = id;
    }

    @Value("${mqtt.broker.connexion-string}")
    public void setBrockerAddressStatic(String address){
        MQTTBuilder.brockerAddressStatic = address;
    }

    private static IMqttClient instance;

    private MQTTBuilder() {
    }

    public static IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(brockerAddressStatic, clientIdStatic);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }
}

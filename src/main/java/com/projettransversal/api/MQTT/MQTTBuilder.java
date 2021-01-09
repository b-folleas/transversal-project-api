package com.projettransversal.api.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQTTBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MQTTBuilder.class);
    private static String clientIdStatic;
    private static String brockerAddressStatic;
    private static IMqttClient instance;
    @Value("${mqtt.client.id}")
    private String clientId;
    @Value("${mqtt.broker.connexion-string}")
    private String brockerAddress;

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
            logger.error("Error in getting MQTT instance with address " + brockerAddressStatic);
        }

        return instance;
    }

    @Value("${mqtt.client.id}")
    public void setClientIdStatic(String id) {
        MQTTBuilder.clientIdStatic = id;
    }

    @Value("${mqtt.broker.connexion-string}")
    public void setBrockerAddressStatic(String address) {
        MQTTBuilder.brockerAddressStatic = address;
    }
}

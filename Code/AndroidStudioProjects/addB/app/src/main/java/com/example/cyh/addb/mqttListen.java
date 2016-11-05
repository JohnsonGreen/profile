package com.example.cyh.addb;

/**
 * Created by root on 16-10-23.
 */

public class mqttListen {


    public Mqtt mListen(String topic, String clientId) {
        Mqtt mqtt = new Mqtt();
        mqtt.setBroker("tcp://120.76.52.55:1883");
        mqtt.setTopic(topic);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setQos(1);
        mqtt.setClientId(clientId);

        mqtt.init();
        mqtt.listen();
        return mqtt;
    }
}
package com.example.cyh.billards;

/**
 * Created by root on 16-10-23.
 */

public class mqttListen {

    public Mqtt mListen(String topic ) {
        Mqtt mqtt = new Mqtt();
        mqtt.setBroker("tcp://120.76.52.55:1883");
        mqtt.setTopic(topic);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setQos(1);
        mqtt.setClientId("twtandroid");
        mqtt.init();
        mqtt.listen();
        return mqtt;
    }
}
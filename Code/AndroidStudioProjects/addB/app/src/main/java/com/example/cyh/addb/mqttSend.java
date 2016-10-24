package com.example.cyh.addb;

/**
 * Created by root on 16-10-24.
 */

public class mqttSend {
    public Mqtt mSend(String topic, String content) {
        Mqtt mqtt = new Mqtt();
        mqtt.setBroker("tcp://120.76.52.55:1883");
        mqtt.setTopic(topic);
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setQos(1);
        mqtt.setClientId("twtandroid1");
        mqtt.setContent(content);
        mqtt.init();
        mqtt.send();
        return mqtt;
    }
}

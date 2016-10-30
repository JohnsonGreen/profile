package com.example.cyh.billards;

/**
 * Created by root on 16-10-24.
 */

public class mqttSend {
    public Mqtt mSend(String topic, String content,String clientId) {
        Mqtt mqtt = new Mqtt();
        mqtt.setBroker("tcp://120.76.52.55:1883");
        mqtt.setTopic(topic);
        mqtt.setUserName("hehehe");
        mqtt.setPassword("password");
        mqtt.setQos(1);
        mqtt.setClientId(clientId);
        mqtt.setContent(content);
        mqtt.init();
        //mqtt.send();
        return mqtt;
    }
}

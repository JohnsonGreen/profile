package com.example.cyh.billards;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by paruke on 16/8/10.
 */

public class SecondActivity extends AppCompatActivity {
    private ImageButton imageButton;
    Button button = null;

    String sensor = "accel";

    //接收常连接的消息
    public Handler handler;

    //加速度值的呈现
    TextView acc_text;

    LiduChartView liduChart = null;

    private Mqtt mqtt;
    private Mqtt mSend;

    private int  sensorValue = 25;


    private Handler accHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 0x12345){
                acc_text.setText(String.valueOf(sensor));
            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageButton = (ImageButton)findViewById(R.id.imageButton2);
        mSend = new mqttSend().mSend("coordinate","1","sendY=1");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,ThirdActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
                */
                mSend.setContent("1");
                mSend.send();
                Toast.makeText(getApplicationContext(), "发送成功！", Toast.LENGTH_SHORT).show();

            }
        });

        imageButton = (ImageButton)findViewById(R.id.imageButton3);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
               // intent.setClass(SecondActivity.this,ForthActivity.class);
                intent.setClass(SecondActivity.this,ChooseActivity.class);


              //  Bundle bundle = new Bundle();
               // intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        button = (Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,LihsiActivity.class);
                startActivity(intent);

            }
        });


        //给
        imageButton = (ImageButton)findViewById(R.id.imageButton4);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layout=LayoutInflater.from(SecondActivity.this);
                View view_fog=layout.inflate(R.layout.activity_fog, null);

                acc_text = (TextView)view_fog.findViewById(R.id.acc_text);
                //System.out.println(String.valueOf(sensorValue));
                acc_text.setText(String.valueOf(sensorValue));

                liduChart = (LiduChartView)view_fog.findViewById(R.id.fogChart);

                //System.out.println(liduChart.toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            accHandler.sendEmptyMessage(0x1234);
                        }
                    }
                }).start();

                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,LiduActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        mqtt = new Mqtt();
        setMqtt();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    try{

                        decodeJson(mqtt.ret);

                        if(liduChart != null){
                            liduChart.setMessage(sensorValue);
                        }
                        else{
                            System.out.println("liduChart is null");
                        }
                    }
                    catch(JSONException e){
                        System.out.println("Json解析出错");
                        e.printStackTrace();
                    }
                }
            }
        };
        mqtt.setHandler(handler);



       /*
        imageButton = (ImageButton)findViewById(R.id.imageButton4);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,LiduActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        */


    }


    public void decodeJson(String jsonValue) throws JSONException {
        if(jsonValue != null){
            JSONObject sensorJson = new JSONObject(mqtt.ret);
            double d = Double.parseDouble(sensorJson.getString(sensor));
            sensorValue = (int)d;
        }
    }

    public void setMqtt(){
        mqtt.setBroker("tcp://115.29.109.2:61613");
        mqtt.setTopic("sensor");
        mqtt.setUserName("admin");
        mqtt.setPassword("password");
        mqtt.setContent("content");
        mqtt.setQos(1);
        mqtt.setClientId("hahahahhhahah");

        mqtt.init();
        mqtt.listen();
    }

}
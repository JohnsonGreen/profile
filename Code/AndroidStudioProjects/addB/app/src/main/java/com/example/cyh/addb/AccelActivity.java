package com.example.cyh.addb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AccelActivity extends AppCompatActivity {

    private LiduChartView liduChart = null;
    private TextView acc_text;
    private Mqtt mqttListen = null;
    private String topic = "accel";
    private String clientId = "accel";
    private int senorValue = 30;
    private int MaxDataSize;

    private List<Integer> data = new ArrayList<Integer>();

    private Handler liduHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 0x3456){
                acc_text.setText(String.valueOf(senorValue));
            }
        }
    };

    private Handler hand = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 0x1234){
                LiduChartView liduChart = (LiduChartView) findViewById(R.id.LiduChart);
                //LiduChartView.this.invalidate();
                liduChart.invalidate();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accel);

        acc_text = (TextView)findViewById(R.id.acc_text);
        acc_text.setText(String.valueOf(senorValue) + "g");
        liduChart = (LiduChartView)findViewById(R.id.LiduChart);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    liduHandler.sendEmptyMessage(0x3456);
                }
            }
        }).start();


        mqttListen = new mqttListen().mListen(topic,clientId);

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                        if(mqttListen.ret != "message arrived:sensor server ok") {
                            try {
                                senorValue = Integer.parseInt(mqttListen.ret);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        if(liduChart != null){
                            liduChart.setMessage(senorValue);
                            liduChart.setHandler(hand);
                            data = liduChart.getDataList();
                            MaxDataSize = liduChart.getMaxSize();
                        }
                        else{
                            System.out.println("liduChart is null");
                        }

                      if(data.size() >= MaxDataSize){
                         data.remove(0);
                     }
                      data.add(senorValue);
                      hand.sendEmptyMessage(0x1234);
                }

            }
        };
        mqttListen.setHandler(handler);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if(data.size() >= MaxDataSize){
//                        data.remove(0);
//                    }
//                    data.add(senorValue);
//                    hand.sendEmptyMessage(0x1234);
//                }
//            }
//        }).start();







    }

}

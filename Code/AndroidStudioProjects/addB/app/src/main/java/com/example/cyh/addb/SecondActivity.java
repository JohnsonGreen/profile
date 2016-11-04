package com.example.cyh.addb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by paruke on 16/8/10.
 */

public class SecondActivity extends Activity {

    private ImageButton pointButton = null;
    private ImageButton chooseButton = null;
    private ImageButton liduButton = null;
    private Mqtt mSend = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        mSend = new mqttSend().mSend("coordinate","1","sendY=1");

        pointButton = (ImageButton)findViewById(R.id.point_button);
        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSend.setContent("1");
                mSend.send();
                Toast.makeText(getApplicationContext(), "发送成功！", Toast.LENGTH_SHORT).show();

            }
        });

        chooseButton = (ImageButton)findViewById(R.id.choose_button);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        liduButton = (ImageButton)findViewById(R.id.lidu_button);
        liduButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, AccelActivity.class);
                startActivity(intent);

               // Toast.makeText(getApplicationContext(), "发送成功！", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSend = null;
    }

}
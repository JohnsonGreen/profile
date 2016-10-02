package com.example.cyh.addbuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();
        int data = bundle.getInt("count");//读出数据
        setTitle("button"+data+"clicked!");

    }
}

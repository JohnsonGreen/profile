package com.example.cyh.addb;

import android.app.Activity;
import android.os.Bundle;

public class Second extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();
        int data = bundle.getInt("count");//读出数据
        setTitle("button"+data+"clicked!");

    }
}

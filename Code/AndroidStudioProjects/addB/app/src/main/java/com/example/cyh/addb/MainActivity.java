package com.example.cyh.addb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.LogRecord;



public class MainActivity extends Activity {

    private TextView textview1;
    private Button button1;
    //获取手机屏幕分辨率的类
    private DisplayMetrics dm;
    private Handler handler = null;
    private Integer remain_height = 0;
    private Integer remain_width = 0;
    private Integer margin = 0;
    private RelativeLayout lay = null;
    private Mqtt mqListen = null;
    private Mqtt mqSend = null;

    //private LinkedHashMap<String,String> coordinate;

    private String[][] coordinate = new String[20][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageButton confirm_button = (ImageButton)findViewById(R.id.confirm_button);
        confirm_button.setClickable(false);
        System.out.println("+++++++++++++++++++++++++++++++++++");
             //Mqtt mqini = mqttinit();

             //mqini.setContent("heheda!!!!!");
             //mqini.send();

             //mqini.setTopic("#");
             //mqini.listen();


        mqListen = new mqttListen().mListen("#");
        mqSend = new mqttSend().mSend("coordinate","Hello");

        System.out.println("-----------------------------------");


        getremainHW();

        LinkedHashMap<String,String> ma = strToMap("way=0&rec0=358@456@9@brown&rec2=695@168@9@syellow&rec3=851@565@9@syellow&rec4=763@484@9@bred&end=a");

        changeUI(ma);



        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){

                    System.out.println("shoudao");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);
                    layout.removeView(lay);    //移除 动态添加的view

                    if( ! mqListen.ret.equals("NoData")){
                        LinkedHashMap<String,String> ma = strToMap(mqListen.ret);
                        changeUI(ma);
                    }
                }
            }
        };
        mqListen.setHandler(handler);


        //1794 x 1080
        //1600 x 1080
        //qiu: 53
        //1366 x 667


    }

    protected Mqtt  mqttinit() {
            Mqtt mqtt = new Mqtt();
            mqtt.setBroker("tcp://120.76.52.55:1883");
            mqtt.setTopic("twtandroid1");
            mqtt.setUserName("admin");
            mqtt.setPassword("password");
            mqtt.setQos(1);
            String SerialNumber = android.os.Build.SERIAL;
            mqtt.setClientId(SerialNumber);
            mqtt.setContent("hello");
            mqtt.init();
            mqtt.send();
            return mqtt;
    }




    protected  LinkedHashMap toMap(String jsonString) throws JSONException {

            JSONObject jsonObject = new JSONObject(jsonString);

        LinkedHashMap result = new LinkedHashMap();
            Iterator iterator = jsonObject.keys();
            String key = null;
            String value = null;

            while (iterator.hasNext()) {

                key = (String) iterator.next();
                value = jsonObject.getString(key);
                result.put(key, value);

            }
            return result;
        }


    protected  LinkedHashMap<String,String> strToMap(String str){

        LinkedHashMap<String,String> result = new LinkedHashMap();
        String[] sArray = str.split("\\&");
        for(String s : sArray){
            System.out.println(s);
            String[] stemp = s.split("\\=");

            if(stemp[0].equals("end")){
                continue;
            }

            System.out.println("stemp[0]: " + stemp[0]);
            System.out.println("stemp[0]: " + stemp[1]);

            result.put(stemp[0], stemp[1]);
        }

        for(String key : result.keySet()) {
            System.out.println("key:" + key);
            System.out.println("values:" + result.get(key));
        }

        return result;

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    protected Integer other_pixels(){

        Integer button_hight = dip2px(this,50);                      //

        int statusBarHeight = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        System.out.println(button_hight);
        System.out.println(statusBarHeight);

        return  button_hight + statusBarHeight;
    }

    protected void getremainHW(){
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Integer width = dm.widthPixels;
        Integer height = dm.heightPixels;


        remain_height = height - other_pixels() - 10;
        remain_width = (int)(remain_height * 667 / 1366.0f + 0.5f);
        margin = (width - remain_width) / 2;
    }

    protected void changeUI(LinkedHashMap<String,String> ma){

        LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);
        lay = new RelativeLayout(this);
        ViewGroup.LayoutParams lp_fullWidth =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageButton Btn[] = new ImageButton[20];
        int tot = 0;
        if(ma.get("way").equals("0")){

            for(String key : ma.keySet()) {
                if(key.equals("way")){
                    continue;
                }
                System.out.println("key:" + key);
                System.out.println("values:" + ma.get(key));

                String[] valArray = ma.get(key).split("\\@");

                //int value = Integer.parseInt(ma.get(key));
                String source_x = null;
                String source_y = null;



                int  x = 0,y = 0,j = 0;
                for(String s : valArray){
                    if(j == 0 ){
                        source_x = s;
                        coordinate[tot][0] = s;
                        y = (int)((Integer.parseInt(s)/1366.0f) * remain_height) + 5;
                    }else if(j == 1){
                        source_y = s;
                        coordinate[tot][1] = s;
                        x = (int)((Integer.parseInt(s)/667.0f) * remain_width) + margin;
                    }else{
                        break;
                    }
                    j++;
                }


                Btn[tot]=new ImageButton(this);
                Btn[tot].setId(2000+tot);
                if(tot == 0)
                    Btn[tot].setImageResource(R.drawable.circle_white);
                else
                    Btn[tot].setImageResource(R.drawable.circle_green);

                Btn[tot].setScaleType(ImageView.ScaleType.CENTER );
                RelativeLayout.LayoutParams btParams = new  RelativeLayout.LayoutParams(55,64);

                if(tot == 0)
                    btParams.setMargins(x ,y,0,0);   //左上右下
                else
                    btParams.setMargins(x,y,0,0);   //左上右下

                //btParams.addRule(RelativeLayout.BELOW, 1);
                lay.addView(Btn[tot],btParams);        //将按钮放入layout组件

                tot++;

            }
        }

        layout.addView(lay,lp_fullWidth);

        for( int k = 0; k <= tot-1; k++) {
            //这里不需要findId，因为创建的时候已经确定哪个按钮对应哪个Id
            Btn[k].setTag(k);    //为按钮设置一个标记，来确认是按下了哪一个按钮
            Btn[k].setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton confirm_button = (ImageButton)findViewById(R.id.confirm_button);
                    confirm_button.setClickable(true);
                    confirm_button.setOnClickListener(new Button.OnClickListener(){

                        @Override
                        public void onClick(View v) {

                            Toast.makeText(getApplicationContext(), "信息已发送 ", Toast.LENGTH_SHORT).show();
                            String codinate = coordinate[k][0] + "@" + coordinate[k][1];
                            mqSend.setContent(codinate);
                            mqSend.send();
                        }
                    });


                }
            });
        }
    }


}



package com.example.cyh.addb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends Activity {

    private TextView textview1;
    private Button button1;
    //获取手机屏幕分辨率的类
    private DisplayMetrics dm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        System.out.println("+++++++++++++++++++++++++++++++++++");
         Mqtt mqini = mqttinit();

             mqini.setContent("heheda!!!!!");
             mqini.send();


        System.out.println("-----------------------------------");





        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Integer width = dm.widthPixels;
        Integer height = dm.heightPixels;

        //System.out.println("width:  "+ width);
        //System.out.println("height:  "+ height);



        Integer remain_height = height - other_pixels() - 10;
        Integer remain_width = (int)(remain_height * 667 / 1366.0f + 0.5f);
        Integer  margin = (width - remain_width) / 2;


        Map<String,String> ma = strToMap("way=0&rec0=358@456@9@brown&rec2=695@168@9@syellow&rec3=851@565@9@syellow&rec4=763@484@9@bred&end=a");

        //1794 x 1080
        //1600 x 1080
        //qiu: 53
        //1366 x 667


       LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);

        RelativeLayout lay = new RelativeLayout(this);

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

                int x = 0,y = 0,j = 0;
                for(String s : valArray){
                    if(j == 0 ){
                        y = (int)((Integer.parseInt(s)/1366.0f) * remain_height) + 5;
                    }else if(j == 1){
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


        for(int k = 0; k <= tot-1; k++) {
            //这里不需要findId，因为创建的时候已经确定哪个按钮对应哪个Id
            Btn[k].setTag(k);    //为按钮设置一个标记，来确认是按下了哪一个按钮

            Btn[k].setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = (Integer) v.getTag();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, Second.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("count", i);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //MainActivity.this.finish();
                }
            });
        }




    }

    protected Mqtt  mqttinit() {
            Mqtt mqtt = new Mqtt();
            mqtt.setBroker("tcp://120.76.52.55:1883");
            mqtt.setTopic("twtandroid1");
            mqtt.setUserName("admin");
            mqtt.setPassword("password");
            mqtt.setQos(1);
            mqtt.setClientId("twtandroid1");
            mqtt.setContent("hello");
            mqtt.init();
            mqtt.send();
            return mqtt;
    }


    protected  Map toMap(String jsonString) throws JSONException {

            JSONObject jsonObject = new JSONObject(jsonString);



            Map result = new HashMap();
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


    protected  Map<String,String> strToMap(String str){

        Map<String,String> result = new HashMap();
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


}



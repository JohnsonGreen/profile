package com.example.cyh.addb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;



public class MainActivity extends Activity {

    private TextView textview1;
    private Button button1;
    //获取手机屏幕分辨率的类
    private DisplayMetrics dm;
    private Handler handler = null;
    private int remain_height = 0;
    private RelativeLayout lay = null;
    private int remain_width = 0;
    private int margin = 0;
    private int hole_margin = 0;
    private Mqtt mqListen = null;
    private Mqtt mqSend = null;

    private int hole_width = 80;
    private int hole_padding = 10;
    private int hole_id = -1;
    private int enable_id = -1;
    private int enable_hole_id = -1;
    private int btnid = 0;
    private int button_hight_dp = 70;  //返回键+ 确认键的高度dp

    private int tot = 0;            //识别到的台球总数

    private String white_x = null;
    private String white_y = null;


    private  Hights high = new Hights();

    private ImageButton Btn[] = new ImageButton[50];
    private ImageButton holeButton[] = new ImageButton[6];

    private boolean hole_enable = false;
    private boolean ball_enable = false;

    private boolean flag = false;
    private int mutex = 0;

    //private LinkedHashMap<String,String> coordinate;

    private String[][] coordinate = new String[50][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        System.out.println("getScreenHeight： " + high.getScreenHeight(this));
        System.out.println("getBottomStatusHeight： " + high.getBottomStatusHeight(this));
        System.out.println("getDpi： " + high.getDpi(this));
        System.out.println("getTitleHeight： " + high.getTitleHeight(this));
        System.out.println("getStatusHeight： " + high.getStatusHeight(this));

        other_pixels();
        getremainHW();

        mqListen = new mqttListen().mListen("hehehe","getCoordinate");
        mqSend = new mqttSend().mSend("coordinate","0","sendY=0");
        getremainHW();

        LinkedHashMap<String,String> ma = null;
        try {
            ma = strToMap("{\"way\":\"0\",\"rec2\":\"800@100@8@white\",\"rec3\":\"700@320@8@black\",\"rec4\":\"500@300@8@bred\",\"end\":\"\\n\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        changeUI(ma);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                   // mutex = 1;   //测试用
                    if(mutex == 0){
                        if(!flag){
                            mutex = 1;
                            TimerTask task = new TimerTask(){
                                public void run(){
                                    flag = true;
                                    this.cancel();
                                    mutex = 0;
                                }
                            };
                            Timer timer = new Timer();
                            timer.schedule(task, 2000);

                        }else{

                            //System.out.println("shoudao");
                            LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);
                            if( ! mqListen.ret.equals("NoData")){
                                LinkedHashMap<String,String> mat = null;
                                try {
                                    mat = strToMap(mqListen.ret);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(isChanged(mat)){
                                    layout.removeView(lay);    //移除 动态添加的view
                                    changeUI(mat);
                                }
                            }

                            flag = false;
                        }
                    }


                }
            }
        };
        mqListen.setHandler(handler);


        ImageButton returnButton = (ImageButton)findViewById(R.id.return_button);
        returnButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        //1794 x 1080
        //1600 x 1080
        //qiu: 53
        //1366 x 667
        //球桌大小 1488x800
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


    protected  LinkedHashMap<String,String> strToMap(String str) throws JSONException {

      LinkedHashMap<String,String> result = new LinkedHashMap();
       // String[] sArray = str.split("&");

        JSONObject jsonObject = new JSONObject(str);
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;

        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            if(key.equals("end"))
                continue;
            result.put(key, value);

        }


        return result;

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    protected int other_pixels(){

        int button_hight = dip2px(this,button_hight_dp);
        int statusBarHeight = high.getStatusHeight(this);

        System.out.println("button_hight: " + button_hight);
        System.out.println("statusBarHeight: " + statusBarHeight);

        return  button_hight + statusBarHeight;
    }


    protected void getremainHW(){
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        remain_height = height - other_pixels();
        System.out.println("remain_height" + remain_height);
        remain_width = (int)(remain_height * 800 / 1488.0f + 0.5f);
        System.out.println("remain_width" + remain_width);
        margin = (width - remain_width) / 2;
        System.out.println("margin" + margin);
    }

    protected boolean isChanged(LinkedHashMap<String,String> ma){

          if(ma.isEmpty() || (ma.size() - 1) != tot ){
              return true;
          }

         int tx,ty;
         for(String key : ma.keySet()) {
             if(key.equals("way")){
                 continue;
             }

             String[] valArray = ma.get(key).split("\\@");

             int  x = 0,y = 0,j = 0;
             for(String s : valArray){
                 if(j == 0 ){
                    x = Integer.parseInt(s);
                 }else if(j == 1){
                    y = Integer.parseInt(s);
                    break;
                 }
                 j++;
             }
             int i = 0;
            for(;i < tot;i++){

                tx = Integer.parseInt(coordinate[i][0]);
                ty = Integer.parseInt(coordinate[i][1]);

                if(Math.pow(tx-x,2) + Math.pow(ty-y,2) < 625){        //存在跟之前坐标一样的球
                    i--;
                    break;
                }
            }
             if(i == tot ){
                 return true;
             }
         }

        return false;

    }


    protected void changeUI(LinkedHashMap<String,String> ma){


        int white_id = -1;

        int lf_border = (int)(75 * remain_height / 1488.0 + 0.5f);
        int to_border = (int)(87 * remain_height / 1488.0 + 0.5f);
        int ri_border = (int)(77 * remain_height / 1488.0 + 0.5f);
        int do_border = (int)(73 * remain_height / 1488.0 + 0.5f);
        int real_desk_width = remain_width - lf_border - ri_border;
        int real_desk_hight = remain_height - to_border - do_border;



        int button_width = 40 * real_desk_hight / 1366;                   //button_width是各个台球的按钮

        System.out.println("real_desk_width: " + real_desk_width);
        System.out.println("real_desk_hight: " + real_desk_hight);


        System.out.println("lf_border: " + lf_border);
        System.out.println("ri_border: " + ri_border);
        System.out.println("to_border: " + to_border);
        System.out.println("do_border: " + do_border);


        LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);
        lay = new RelativeLayout(this);
        lay.setBackgroundResource(R.mipmap.desk);

        RelativeLayout.LayoutParams lp_fullWidth =
                new RelativeLayout.LayoutParams(remain_width, remain_height);

        lp_fullWidth.setMargins(margin,0,0,0);

        for(int i = 0; i < 6;i++){
            RelativeLayout.LayoutParams btParams = new  RelativeLayout.LayoutParams(hole_width,hole_width);
            if(i < 3){
               // btParams.setMargins(hole_margin - hole_width/2, i*(remain_height/2),0,0);   //左上右下
                btParams.setMargins(lf_border - hole_width/2, to_border - hole_width/2 + i * (real_desk_hight/2),0,0);   //左上右下
                System.out.println("di  "+ i +"  dian :  x : " + (lf_border - hole_width/2) + " y : " + (to_border - hole_width/2 + i * (real_desk_hight/2)));
            }else{
                btParams.setMargins(lf_border + real_desk_width - hole_width/2, (to_border - hole_width/2) + (i - 3) * (real_desk_hight/2),0,0);   //左上右下
                System.out.println("di  "+ i +"  dian :  x : " + (lf_border + real_desk_width - hole_width/2) + " y : " + ((to_border - hole_width/2) + (i - 3) * (real_desk_hight/2)));
            }


            holeButton[i] = new ImageButton(this);
            holeButton[i].setId(1200+i);
            //holeButton[i].setImageResource(R.mipmap.lan);
            holeButton[i].setBackgroundResource(R.mipmap.hole);
            //holeButton[i].setScaleType(ImageView.ScaleType.CENTER );
            //btParams.addRule(RelativeLayout.BELOW, 1);
            lay.addView(holeButton[i],btParams);        //将按钮放入layout组件

        }

        tot = 0;
        if(ma.get("way").equals("0")){

            for(String key : ma.keySet()) {
                if(key.equals("way")){
                    continue;
                }
                System.out.println("key:" + key);
                System.out.println("values:" + ma.get(key));

                String[] valArray = ma.get(key).split("\\@");

                int  x = 0,y = 0,j = 0;

                boolean isWhite = false;

                int middle = lf_border + real_desk_width/2;

                for(String s : valArray){
                    if(j == 0 ){
                        coordinate[tot][0] = s;

                        y = (int)((Integer.parseInt(s)/1366.0f) * real_desk_hight) + to_border - button_width/2;


                    }else if(j == 1){

                        coordinate[tot][1] = s;
                       int tx = (int)((Integer.parseInt(s)/667.0f) * real_desk_width) + lf_border;

                        x = lf_border + real_desk_width - (tx - lf_border - button_width/2) - button_width;


                    }else if(j == 2){
                        System.out.println("X :" + (x - button_width/2) + "  Y : " + (y - button_width/2) );

                    }else if(j == 3){
                        if(s.equals("white")){
                            white_x = coordinate[tot][0];
                            white_y = coordinate[tot][1];
                            isWhite = true;
                        }
                        break;
                    }
                    j++;
                }

                Btn[tot]=new ImageButton(this);
                Btn[tot].setId(2000+tot);

                if(isWhite){
                    Btn[tot].setBackgroundResource(R.mipmap.white);
                    white_id = tot;
                }
                else{
                    Btn[tot].setBackgroundResource(R.mipmap.ball);
                }

                //Btn[tot].setScaleType(ImageView.ScaleType.CENTER );
                RelativeLayout.LayoutParams btParams = new  RelativeLayout.LayoutParams(button_width,button_width);


                btParams.setMargins(x, y,0,0);   //左上右下
                System.out.println( "第"+tot+"个球 ： 坐标--- X : "+ x+ "  Y : " + y);

                //btParams.addRule(RelativeLayout.BELOW, 1);
                lay.addView(Btn[tot],btParams);        //将按钮放入layout组件

                tot++;
            }
        }


        layout.addView(lay,lp_fullWidth);

        for( int k = 0; k <= tot-1; k++) {

            if(k == white_id)
                continue;

            //这里不需要findId，因为创建的时候已经确定哪个按钮对应哪个Id
            Btn[k].setTag(k);    //为按钮设置一个标记，来确认是按下了哪一个按钮
            Btn[k].setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ImageButton confirm_button = (ImageButton)findViewById(R.id.confirm_button);
                    btnid = v.getId() - 2000;
                   // if(btnid != enable_id){
                        if(enable_id != -1){
                            Btn[enable_id].setBackgroundResource(R.mipmap.ball);
                        }

                        ball_enable = true;
                        ImageButton temp = (ImageButton) v;
                        temp.setBackgroundResource(R.mipmap.ball_on);
                         confirm_button.setClickable(true);
                         confirm_button.setImageResource(R.mipmap.confirm_button_light);
                        enable_id = btnid;
                  //  }
                }
            });
        }
       for(int i = 0;i < 6;i++){
           holeButton[i].setTag(i);
           holeButton[i].setOnClickListener(new Button.OnClickListener(){
               @Override
               public void onClick(View v) {
                   hole_id = v.getId() - 1200;
                   ImageButton confirm_button = (ImageButton)findViewById(R.id.confirm_button);

                       if(enable_hole_id != -1){
                           holeButton[enable_hole_id].setBackgroundResource(R.mipmap.hole);
                       }
                       ImageButton temp = (ImageButton) v;
                       temp.setBackgroundResource(R.mipmap.hole_on);
                       hole_enable = true;
                       confirm_button.setClickable(true);
                       confirm_button.setImageResource(R.mipmap.confirm_button_light);
                       enable_hole_id = hole_id;

               }
           });
       }

        ImageButton confirm_button = (ImageButton)findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(!ball_enable && hole_enable){
                    Toast.makeText(getApplicationContext(), "请再选择一个球！", Toast.LENGTH_SHORT).show();
                }else if(ball_enable && !hole_enable){
                    Toast.makeText(getApplicationContext(), "请再选择一个球洞！", Toast.LENGTH_SHORT).show();
                }else{

                     int temphole = hole_id;
                    if(temphole < 3){
                        temphole +=3;
                    }else{
                        temphole -=3;
                    }

                    String codinate = "0" + "\r\n" + coordinate[btnid][0] + "\r\n" + coordinate[btnid][1] + "\r\n"+(temphole+ 1);
                    mqSend.setContent(codinate);
                    mqSend.send();
                    Toast.makeText(getApplicationContext(), "信息已发送 ", Toast.LENGTH_SHORT).show();

                    ImageButton temp = (ImageButton)v;
                    temp.setClickable(false);
                    temp.setImageResource(R.mipmap.confirm_button_dark);

                    ball_enable = false;
                    hole_enable = false;
                    holeButton[hole_id].setBackgroundResource(R.mipmap.hole);
                    Btn[btnid].setBackgroundResource(R.mipmap.ball);

                }

            }
        });

        confirm_button.setClickable(false);
    }


}



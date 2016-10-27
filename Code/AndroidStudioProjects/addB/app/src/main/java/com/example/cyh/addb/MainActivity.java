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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private RelativeLayout lay = null;
    private Integer remain_width = 0;
    private Integer margin = 0;
    private Mqtt mqListen = null;
    private Mqtt mqSend = null;
    private int button_width = 58;
    private int button_height = 58;
    private int hole_width = 80;
    private int hole_padding = 10;
    private int hole_id = -1;
    private int enable_id = -1;
    private int enable_hole_id = -1;
    private int btnid = 0;

    private ImageButton Btn[] = new ImageButton[20];
    private ImageButton holeButton[] = new ImageButton[6];

    private boolean hole_enable = false;
    private boolean ball_enable = false;

    //private LinkedHashMap<String,String> coordinate;

    private String[][] coordinate = new String[20][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        System.out.println("+++++++++++++++++++++++++++++++++++");
             //Mqtt mqini = mqttinit();
             //mqini.setContent("heheda!!!!!");
             //mqini.send();
             //mqini.setTopic("#");
             //mqini.listen();


        mqListen = new mqttListen().mListen("hahahaha");
        mqSend = new mqttSend().mSend("coordinate","Hello");

        System.out.println("-----------------------------------");
        getremainHW();
        LinkedHashMap<String,String> ma = strToMap("way=0&rec3=100@200@9@syellow&rec2=150@200@9@white&rec4=1100@300@9@bred&end=a");
        changeUI(ma);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){

                    System.out.println("shoudao");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);
                    //layout.removeAllViews();
                   layout.removeView(lay);    //移除 动态添加的view
                    if( ! mqListen.ret.equals("NoData")){
                        LinkedHashMap<String,String> mat = strToMap(mqListen.ret);
                        changeUI(mat);
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
        String[] sArray = str.split("&");
        for(String s : sArray){
            System.out.println(s);
            String[] stemp = s.split("=");

            if(stemp[0].equals("end")){
                continue;
            }

            System.out.println("stemp[0]: " + stemp[0]);
            System.out.println("stemp[1]: " + stemp[1]);

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

        Integer button_hight = 2 * dip2px(this,50);                      //

        System.out.println("button_hight : " + button_hight);

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


        remain_height = height - other_pixels();
        System.out.println("remain_height" + remain_height);
        remain_width = (int)(remain_height * 667 / 1366.0f + 0.5f);
        margin = (width - remain_width) / 2;
    }

    /*
     * 设置控件所在的位置YY，并且不改变宽高，
     * XY为绝对位置
     */

    public static void setLayout(View view,int x,int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,y, x+margin.width, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }


    protected void changeUI(LinkedHashMap<String,String> ma){

        LinearLayout layout = (LinearLayout)findViewById(R.id.linearBelow);
        lay = new RelativeLayout(this);
        ViewGroup.LayoutParams lp_fullWidth =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        for(int i = 0; i < 6;i++){
            RelativeLayout.LayoutParams btParams = new  RelativeLayout.LayoutParams(hole_width,hole_width);
            if(i < 3){
                btParams.setMargins(margin - hole_width/2, i*(remain_height/2),0,0);   //左上右下
                System.out.println("di  "+ i +"  dian :  x : " + (margin - hole_width/2) + " y : " + i*(remain_height/3));
            }else{
                btParams.setMargins(margin + remain_width - hole_width/2,(i-3)*(remain_height/2) ,0,0);   //左上右下
                System.out.println("di  "+ i +"  dian :  x : " + (margin + remain_width - hole_width/2) + " y : " + (i-3)*(remain_height/3));
            }


            holeButton[i] = new ImageButton(this);
            holeButton[i].setId(1200+i);
            //holeButton[i].setImageResource(R.drawable.lan);
            holeButton[i].setBackgroundResource(R.drawable.hole);
            //holeButton[i].setScaleType(ImageView.ScaleType.CENTER );
            //btParams.addRule(RelativeLayout.BELOW, 1);
            lay.addView(holeButton[i],btParams);        //将按钮放入layout组件

        }

        int tot = 0;
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
                for(String s : valArray){
                    if(j == 0 ){
                        coordinate[tot][0] = s;
                        y = (int)((Integer.parseInt(s)/1366.0f) * remain_height) + 5;
                    }else if(j == 1){

                        coordinate[tot][1] = s;
                        x = (int)((Integer.parseInt(s)/667.0f) * remain_width) + margin;
                    }else if(j == 2){
                        continue;
                    }else if(j == 3){
                        if(s.equals("white")){
                            isWhite = true;
                        }
                        break;
                    }
                    j++;
                }

                Btn[tot]=new ImageButton(this);
                Btn[tot].setId(2000+tot);

                if(isWhite)
                    Btn[tot].setBackgroundResource(R.drawable.white);
                else
                    Btn[tot].setBackgroundResource(R.drawable.ball);

                //Btn[tot].setScaleType(ImageView.ScaleType.CENTER );
                RelativeLayout.LayoutParams btParams = new  RelativeLayout.LayoutParams(button_width,button_height);

                btParams.setMargins(x - button_width/2, y - button_height/2,0,0);   //左上右下

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
                    btnid = v.getId() - 2000;
                   // if(btnid != enable_id){
                        if(enable_id != -1){
                            Btn[enable_id].setBackgroundResource(R.drawable.ball);
                        }

                        ball_enable = true;
                        ImageButton temp = (ImageButton) v;
                        temp.setBackgroundResource(R.drawable.ball_on);
                         confirm_button.setClickable(true);
                         confirm_button.setImageResource(R.drawable.confirm_button_light);
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

                   //if(hole_id != enable_hole_id){

                       if(enable_hole_id != -1){
                           holeButton[enable_hole_id].setBackgroundResource(R.drawable.hole);
                       }
                       ImageButton temp = (ImageButton) v;
                       temp.setBackgroundResource(R.drawable.hole_on);
                       hole_enable = true;
                       confirm_button.setClickable(true);
                       confirm_button.setImageResource(R.drawable.confirm_button_light);
                       enable_hole_id = hole_id;

                  // }
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
                    String codinate = coordinate[btnid][0] + "@" + coordinate[btnid][1] + "@"+hole_id;
                    mqSend.setContent(codinate);
                    mqSend.send();
                    Toast.makeText(getApplicationContext(), "信息已发送 ", Toast.LENGTH_SHORT).show();

                    ImageButton temp = (ImageButton)v;
                    temp.setClickable(false);
                    temp.setImageResource(R.drawable.confirm_button_dark);

                    ball_enable = false;
                    hole_enable = false;
                    holeButton[hole_id].setBackgroundResource(R.drawable.hole);
                    Btn[btnid].setBackgroundResource(R.drawable.ball);

                }

            }
        });

        confirm_button.setClickable(false);
    }

}



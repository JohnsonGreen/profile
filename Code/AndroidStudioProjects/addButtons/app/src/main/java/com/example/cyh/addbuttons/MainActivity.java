package com.example.cyh.addbuttons;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView textview1;
    private Button button1;
    //获取手机屏幕分辨率的类
    private DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dm = new DisplayMetrics();   //
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        final int height = dm.heightPixels;

        RelativeLayout layout = new RelativeLayout(this);


        ImageButton Btn[] = new ImageButton[2];
        int j = -1;

        for (int i=0; i< 2; i++) {
            Btn[i]=new ImageButton(this);
            Btn[i].setId(2000+i);

            if(i == 0)
                Btn[i].setImageResource(R.drawable.circle_green);
            else
                Btn[i].setImageResource(R.drawable.circle_white);

            Btn[i].setScaleType(ImageView.ScaleType.CENTER );
            RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams(55,64);

            //设置按钮的宽度和高度
            if (i%4 == 0) {
                j++;
            }
            
           // btParams.leftMargin = 10+ ((width-50)/4+10)*(i%4); //横坐标定位
            //btParams.topMargin = 20 + 55*j; //纵坐标定位


            //btParams.setMargins(10+ ((width-50)/4+10)*(i%4),10+200*j,0,0);   //左上右下
            if(i == 0)
                 btParams.setMargins(500,500,0,0);   //左上右下
             else
                btParams.setMargins(700,800,0,0);   //左上右下

           // btParams.topMargin = 10+200*j;

           // btParams.leftMargin = 30

                    //btParams.topMargin = 0;
                            layout.addView(Btn[i],btParams); //将按钮放入layout组件
        }
        this.setContentView(layout);

        for(int k = 0; k <= Btn.length-1; k++) {
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
}



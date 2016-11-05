package com.example.cyh.addb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by moon on 2016/7/27.
 */
public class LiduChartView extends View {

    private Handler handler;
    private Hights high = new Hights();

    private int XPoint = 40;
    private int YPoint = 410;
    private int XScale = 20;  //刻度长度
    private int YScale = 40;
    private int XLength = 360;
    private int YLength = 400;

    private int otherDp = 160;  //包括其他控件的高度

    private int reHigh = 410;
    private int width = 360;

    private int wmargin = 40;
    private int hmargin = 50;

    private int MaxDataSize = 18;   //最大数量


    private List<Integer> data = new ArrayList<Integer>();
    private String[] YLabel = new String[11];

    private int message = 5;


//
//    private Handler handler = new Handler(){
//        public void handleMessage(Message msg) {
//            if(msg.what == 0x1234){
//                LiduChartView.this.invalidate();
//            }
//        }
//    };


    public List<Integer> getDataList(){return this.data;}
    public int getMaxSize(){return this.MaxDataSize;}

    public void setMessage(int message){
        this.message = message;
    }
    public void setHandler(Handler handler){this.handler = handler;}
    public void setXPoint(int XPoint){ this.XPoint = XPoint;}
    public void setYPoint(int YPoint){ this.YPoint = YPoint;}
    public void setXScale(int XScale){ this.XScale = XScale;}
    public void setYScale(int YScale){ this.YScale = YScale;}
    public void setXLength(int XLength){ this.XLength = XLength;}
    public void setYLength(int YLength){ this.YLength = YLength;}
    public void setMaxDataSize(int MaxDataSize){ this.MaxDataSize = MaxDataSize;}
    public void setYLabel(String[] YLabel){ this.YLabel = YLabel;}


    public void remainHight(Context context){

        width = high.getScreenWidth(context);
        wmargin = dip2px(context,wmargin);
        hmargin = dip2px(context,hmargin);
        reHigh =  high.getDpi(context)
                - high.getBottomStatusHeight(context)
                - high.getStatusHeight(context)
                - dip2px(context,otherDp);
    }



    public LiduChartView(Context context, AttributeSet attrs) {
        super(context, attrs);


        remainHight(context);

        setVariables(context);



        int j = 0;
        for(int i = 0; i < YLabel.length; i++){
            YLabel[i] = String.valueOf(j);
            j = j + 1;
        }

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
//                    data.add(message);
//                    handler.sendEmptyMessage(0x1234);
//                }
//            }
//        }).start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) 4.0);              //设置线宽
        paint.setAntiAlias(true); //去锯齿
        paint.setColor(Color.WHITE);
        //paint.setColor(Color.RED);

        Paint numberPaint = new Paint();
        numberPaint.setStrokeWidth((float) 1.0);
        numberPaint.setAntiAlias(true); //去锯齿
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextSize(40);//设置字体大小
       // numberPaint.setTypeface(typeface);//设置字体类型

//      画Y轴
        canvas.drawLine(XPoint, YPoint - YLength, XPoint, YPoint, paint);

//      Y轴箭头
        canvas.drawLine(XPoint, YPoint - YLength, XPoint - 12, YPoint-YLength + 18, paint);
        canvas.drawLine(XPoint, YPoint - YLength, XPoint + 12, YPoint-YLength + 18 ,paint);

        //添加刻度和文字
        for(int i=0; i < 11; i++) {
            canvas.drawLine(XPoint, YPoint - i * YScale, XPoint + 5, YPoint - i * YScale, paint);  //刻度
            canvas.drawText(YLabel[i], XPoint - 50, YPoint - i * YScale, numberPaint);
        }

        //画X轴
        canvas.drawLine(XPoint, YPoint, XPoint + XLength, YPoint, paint);

        if(data.size() > 1){
            for(int i=1; i<data.size(); i++){
                float m = (float)data.get(i - 1);
                float startY = YPoint - m  * YScale;
                float n = (float)data.get(i);
                float stopY = YPoint - n  * YScale;
                canvas.drawLine(XPoint + (i-1) * XScale, startY, XPoint + i * XScale, stopY, paint);
            }
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void setVariables(Context context){

//        XPoint = dip2px(context,XPoint);
//        YPoint = dip2px(context,YPoint);
//        XScale = dip2px(context,XScale);
//        YScale = dip2px(context,YScale);
//        XLength = dip2px(context,XLength);
//        YLength = dip2px(context,YLength);

        XPoint = wmargin;
        YLength = reHigh - hmargin * 2;
        YPoint = YLength + hmargin;
        XLength =  width - wmargin * 2;
        XScale = XLength / MaxDataSize;
        YScale = YLength / 11;

        System.out.println("XPoint" + XPoint);
        System.out.println("YPoint" + YPoint);
        System.out.println("YLength" + YLength);
        System.out.println("XLength" + XLength);
        System.out.println("XScale" + XScale);
        System.out.println("YScale" + YScale);

    }


}

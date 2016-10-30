package com.example.cyh.billards;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;


/**
 * Created by moon on 2016/7/27.
 */
public class FindballView extends View {
    public FindballView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 3.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);             //空心效果
        Rect r=new Rect();                          //Rect对象
        r.left=50;                                  //左边
        r.top=50;                                   //上边
        r.right=450;                                    //右边
        r.bottom=250;                                   //下边
        canvas.drawRect(r, paint);                      //绘制矩形
        canvas.drawRect(50, 400, 450, 600, paint);      //绘制矩形
    }
}

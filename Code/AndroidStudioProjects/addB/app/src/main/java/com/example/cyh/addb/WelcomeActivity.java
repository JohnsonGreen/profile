package com.example.cyh.addb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by paruke on 16/8/20.
 */

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = (ImageView) findViewById(R.id.imageView4);
                Animation rotateAnim = getRotateAnimation();
                imageView.startAnimation(rotateAnim);
                //rotateAnim = getAlphaAnimation();
                //imageView.startAnimation(rotateAnim);

            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = (ImageView) findViewById(R.id.imageView4);

                Animation rotateAnim = getAlphaAnimation();
                imageView.startAnimation(rotateAnim);

            }
        }, 2500);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, SecondActivity.class);
                startActivity(intent);

                finish();
            }
        }, 3500);


    }




   /*
        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
        //图片点击的时候，启动动画效果

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animation rotateAnim = getRotateAnimation();
                v.startAnimation(rotateAnim);
            }
        });

        imageView.performClick();

    */


    /**
     * 旋转
     * @return
     */
    public Animation getAlphaAnimation() {
        //实例化 AlphaAnimation 主要是改变透明度
        //透明度 从 1-不透明 0-完全透明
        Animation animation = new AlphaAnimation(1.0f, 0.5f);
        //设置动画插值器 被用来修饰动画效果,定义动画的变化率
        animation.setInterpolator(new DecelerateInterpolator());
        //设置动画执行时间
        animation.setDuration(1000);
        return animation;
    }


    public RotateAnimation getRotateAnimation(){
        RotateAnimation animation= new RotateAnimation(0,359, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animation.setFillAfter(true);//设置动画结束后保留当前状态
        animation.setDuration(1500);//动画持续时间
//        animation.setRepeatMode(ValueAnimation.RESTART);//重复类型有两个值，取值为ValueAnimation.RESTART时,表示正序重新开始，当取值为ValueAnimation.REVERSE表示倒序重新开始。
//        animation.setStartOffset(2000);//调用start函数之后等待开始运行的时间，单位为毫秒
//        animation.setZAdjustment(300);//表示被设置动画的内容运行时在Z轴上的位置（top/bottom/normal），默认为normal
        animation.startNow();
        animation.setRepeatCount(0);//设置重复次数  这里的次数是从0开始计数的  即设置为2时执行了3次 设置为INFINITE表示无限循环
        return animation;
    }

    /**位移动画
     * 参数说明：
     * formXDelta: 表示X的起始值
     * toXDelta：X得结束值
     * fromYDelta：Y的起始值
     * toYDelta：Y得结束值
     */
    public TranslateAnimation getTranslateAnimation(){
        TranslateAnimation animation=new TranslateAnimation(-200,300,0,400);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setRepeatCount(2);
        animation.startNow();
        return animation;
    }

    /**缩放动画
     * float fromX 动画起始时 X坐标上的伸缩尺寸
     float toX 动画结束时 X坐标上的伸缩尺寸
     float fromY 动画起始时Y坐标上的伸缩尺寸
     float toY 动画结束时Y坐标上的伸缩尺寸
     int pivotXType 动画在X轴相对于物件位置类型
     float pivotXValue 动画相对于物件的X坐标的开始位置
     int pivotYType 动画在Y轴相对于物件位置类型
     float pivotYValue 动画相对于物件的Y坐标的开始位置
     *
     * @return
     */
    public ScaleAnimation getScaleAnimation(){
        ScaleAnimation animation=new ScaleAnimation(0,2,0,2);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setRepeatCount(2);
        animation.startNow();


        return animation;
    }

    /**动画集合 可以将以上各种动画结合到一起实现
     * 在这里可以设置以上动画的不同次数来达到其他动画结束后还执行某次动画
     * 例如：以上其他动画结束后到达的位置还要多执行一次旋转动画
     * @return
     */
    public AnimationSet getAnimationSet(){
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(getRotateAnimation());
        animationSet.addAnimation(getTranslateAnimation());
        animationSet.addAnimation(getScaleAnimation());
        animationSet.addAnimation(getAlphaAnimation());
        animationSet.setFillAfter(true);
        animationSet.startNow();
        return animationSet;
    }


}


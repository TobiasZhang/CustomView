package com.tt.customview.widget.view;

/**
 * Created by TT on 2017/1/4.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tt.customview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tt.customview.R;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by TT on 2017/1/4.
 */
public class SinView extends View {
    //y=Asin(ωx+φ)+k

    private Paint mPaint;
    private int waveColor;
    private int waveWidth;
    private int waveHeight;

    public SinView(Context context) {
        this(context,null);
    }
    public SinView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.cc,defStyleAttr,0);
        waveColor = typedArray.getColor(R.styleable.cc_titleColor, Color.BLACK);
        typedArray.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#6699ff"));
        mPaint.setAntiAlias(true);
        System.out.println(getWidth()+"----构造 getwidth-----");

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("--onMeasure--");
        System.out.println(getWidth()+"----onMeasure getwidth-----");
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //宽
        /*if(widthMode == MeasureSpec.EXACTLY){//EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
            width = widthSize;
        }else{//AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
            int textWidth = mBound.width();
            width = getPaddingLeft()+getPaddingRight()+textWidth;
        }
        //高
        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            int textHeight = mBound.height();
            height = getPaddingTop() + textHeight + getPaddingBottom();
        }*/

        waveWidth = widthSize;
        waveHeight = heightSize;


    }
    int xOffset = 0;
    int speed = 5;

    int xOffset2 = 0;
    int speed2 = 9;
    int a = 20;
    float[] yArr;

    Timer timer;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(timer!=null){
            timer.cancel();
            timer.purge();
        }
        System.out.println(w+"--onSizeChanged--"+h);
        yArr = new float[getWidth()];
        // 将周期定为view总宽度
        float mCycleFactorW = (float) (2 * Math.PI / waveWidth);
        for(int x = 0; x < getWidth(); x++){
//            int y=Asin(ωx+φ)+k
            float y = (float) (a * Math.sin(mCycleFactorW * x)+getHeight()/2);
            yArr[x] = y;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                xOffset+=speed;
                if(xOffset>=getWidth())
                    xOffset = 0;


                xOffset2+=speed2;
                if(xOffset2>=getWidth())
                    xOffset2 = 0;
                postInvalidate();
            }
        },0,16);
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    postInvalidate();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    xOffset+=speed;
                    if(xOffset>=getWidth())
                        xOffset = 0;


                    xOffset2+=speed2;
                    if(xOffset2>=getWidth())
                        xOffset2 = 0;


                }
            }
        }).start();*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("---onDraw---------------");


// 将周期定为view总宽度
        float mCycleFactorW = (float) (2 * Math.PI / waveWidth);

        for(int x = 0; x < getWidth(); x++){
            int trueX = x + xOffset;
            if(trueX >= yArr.length)
                trueX -= yArr.length;

            canvas.drawLine(x,yArr[trueX],x,getHeight(),mPaint);


            int trueX2 = x + xOffset2;
            if(trueX2 >= yArr.length)
                trueX2 -= yArr.length;
            canvas.drawLine(x,yArr[trueX2],x,getHeight(),mPaint);
        }





           /* int trueX = x + xOffset;
            float y = (float) (a * Math.sin(mCycleFactorW * trueX)+getHeight()/2);
            canvas.drawLine(x,y,x,getHeight(),mPaint);*/


            /*int trueX2 = x + xOffset2;
            float y2 = (float) (a * Math.sin(mCycleFactorW * trueX2)+getHeight()/2);
            //System.out.println(x+"---x---y---"+yArr[x]);
            canvas.drawLine(x,y2,x,getHeight(),mPaint);*/



    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("-onlayout------------");
        super.onLayout(changed, left, top, right, bottom);

    }
}


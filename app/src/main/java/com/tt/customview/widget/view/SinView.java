package com.tt.customview.widget.view;

/**
 * Created by TT on 2017/1/4.
 */

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

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    xOffset+=speed;
                    if(xOffset>=getWidth())
                        xOffset = 0;


                    xOffset2+=speed2;
                    if(xOffset2>=getWidth())
                        xOffset2 = 0;

                    postInvalidate();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("--onMeasure--");
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        yArr = new float[getWidth()];
        System.out.println(w+"--onSizeChanged--"+h);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 将周期定为view总宽度
        float mCycleFactorW = (float) (2 * Math.PI / waveWidth);

        mPaint.setColor(waveColor);

        for(int x = 0; x < getWidth(); x++){
//            int y=Asin(ωx+φ)+k

            /*if(yArr[x]==0){
                int trueX = x + xOffset;

                float y = (float) (a * Math.sin(mCycleFactorW * trueX)+getHeight()/2);
                yArr[x] = y;

            }*/
            int trueX = x + xOffset;
            float y = (float) (a * Math.sin(mCycleFactorW * trueX)+getHeight()/2);
            //System.out.println(x+"---x---y---"+yArr[x]);
            canvas.drawLine(x,y,x,getHeight(),mPaint);



            int trueX2 = x + xOffset2;
            float y2 = (float) (a * Math.sin(mCycleFactorW * trueX2)+getHeight()/2);
            //System.out.println(x+"---x---y---"+yArr[x]);
            canvas.drawLine(x,y2,x,getHeight(),mPaint);


        }
        xOffset+=speed;
        if(xOffset>=getWidth())
            xOffset = 0;


        xOffset2+=speed2;
        if(xOffset2>=getWidth())
            xOffset2 = 0;

        postInvalidate();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("-onlayout------------");
        super.onLayout(changed, left, top, right, bottom);
    }
}


package com.tt.customview.widget.view;

/**
 * Created by TT on 2017/1/4.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.tt.customview.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by TT on 2017/1/4.
 */
public class SinSurfaceView extends SurfaceView {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    /**
     * 用于绘制的线程
     */
    private Thread t;
    /**
     * 线程的控制开关
     */
    private boolean isRunning;


    private Paint mPaint;
    private int waveColor;
    private int waveWidth;
    private int waveHeight;

    public SinSurfaceView(Context context) {
        this(context,null);
    }
    public SinSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SinSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
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




        mHolder = getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // 开启线程
                isRunning = true;
                t = new Thread(new Runnable() {
                    int xx = 1;
                    @Override
                    public void run() {
                        // 不断的进行draw
                        while (isRunning){
                            try
                            {
                                // 获得canvas
                                mCanvas = mHolder.lockCanvas();
                                if (mCanvas != null)
                                {
                                    // drawSomething..
//                                    drawSomething(mCanvas);
                                    xx+=50;
                                    mCanvas.drawLine(0,xx,20,getHeight(),mPaint);

                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            } finally{
                                if (mCanvas != null)
                                    mHolder.unlockCanvasAndPost(mCanvas);
                            }
                            try {
                                Thread.sleep(60);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                t.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // 通知关闭线程
                isRunning = false;
            }
        });

         setZOrderOnTop(true);// 设置画布 背景透明
         mHolder.setFormat(PixelFormat.TRANSLUCENT);

        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常亮
        this.setKeepScreenOn(true);

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

    }



    private void drawSomething(Canvas canvas){
        System.out.println("---onDraw---------------");

        // 将周期定为view总宽度
        float mCycleFactorW = (float) (2 * Math.PI / 300);

        for(int x = 0; x < getWidth(); x++){
//            int y=Asin(ωx+φ)+k
            float y = (float) (a * Math.sin(mCycleFactorW * x + xOffset)+getHeight()/2);


            canvas.drawLine(x,y,x,getHeight(),mPaint);
        }

        xOffset+=speed;
        if(xOffset>=getWidth())
            xOffset = 0;


        xOffset2+=speed2;
        if(xOffset2>=getWidth())
            xOffset2 = 0;


            /*int trueX2 = x + xOffset2;
            if(trueX2 >= yArr.length)
                trueX2 -= yArr.length;
            canvas.drawLine(x,yArr[trueX2],x,getHeight(),mPaint);*/
    }
}


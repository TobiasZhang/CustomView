package com.tt.customview.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by TT on 2017/1/9.
 */
public class SurfaceViewTemplate extends SurfaceView {
    private SurfaceHolder mHolder;
    /**
     * 与SurfaceHolder绑定的Canvas
     */
    private Canvas mCanvas;
    /**
     * 用于绘制的线程
     */
    private Thread t;
    /**
     * 线程的控制开关
     */
    private boolean isRunning;

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // 开启线程
                isRunning = true;
                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 不断的进行draw
                        while (isRunning)
                        {
                            try
                            {
                                // 获得canvas
                                mCanvas = mHolder.lockCanvas();
                                if (mCanvas != null)
                                {
                                    // drawSomething..
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            } finally{
                                if (mCanvas != null)
                                    mHolder.unlockCanvasAndPost(mCanvas);
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

        // setZOrderOnTop(true);// 设置画布 背景透明
        // mHolder.setFormat(PixelFormat.TRANSLUCENT);

        //设置可获得焦点
//        setFocusable(true);
//        setFocusableInTouchMode(true);
        //设置常亮
//        this.setKeepScreenOn(true);
    }

}

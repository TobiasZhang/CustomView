package com.tt.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by TT on 2017/1/4.
 */
public class CustomProgressBar extends View {
    private int foreColor,bgColor;
    private Paint paint;
    private int circleWidth = 20;
    private boolean isNext = false;
    private int progress;

    public CustomProgressBar(Context context) {
        this(context,null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    progress++;
                    if(progress==360){
                        progress = 0;
                        isNext = !isNext;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth()/2;
        int radius = centerX-circleWidth/2;
        paint.setStrokeWidth(circleWidth);
        paint.setAntiAlias(true);
        RectF f = new RectF(circleWidth/2,circleWidth/2,getWidth()-circleWidth/2,getWidth()-circleWidth/2);
        if(!isNext){
            paint.setColor(Color.MAGENTA);
            canvas.drawCircle(centerX,centerX,radius,paint);
            paint.setColor(Color.CYAN);
            canvas.drawArc(f,-90,progress,false,paint);
        }else{
            paint.setColor(Color.CYAN);
            canvas.drawCircle(centerX,centerX,radius,paint);
            paint.setColor(Color.MAGENTA);
            canvas.drawArc(f,-90,progress,false,paint);
        }

    }
}

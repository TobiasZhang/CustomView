package com.tt.customview.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tt.customview.R;

/**
 * Created by TT on 2017/1/5.
 */
public class VolumBar extends View {
    /**
     * 第一圈的颜色
     */
    private int mFirstColor= Color.GREEN;
    /**
     * 第二圈的颜色
     */
    private int mSecondColor=Color.RED;
    /**
     * 圈的宽度
     */
    private int mCircleWidth = 40;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mCurrentCount = 3;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize = 20;
    /**
     * 个数
     */
    private int mCount = 10;

    private Rect mRect;

    public VolumBar(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }
    public VolumBar(Context context)
    {
        this(context, null);
    }
    /**
     * 必要的初始化，获得一些自定义的值
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public VolumBar(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        mImage = BitmapFactory.decodeResource(getResources(),R.drawable.pony03);
        mPaint = new Paint();
        mRect = new Rect();
    }
    /**
     * 重写onDraw
     */
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 定义线段断电形状为圆头
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        int centerPoint = getWidth()/2;// 获取圆心的x坐标
        int radius = centerPoint - mCircleWidth/2;// 半径

        drawOval(canvas, centerPoint, radius);
        /**
         * 计算内切正方形的位置
         */
        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径
        /**
         * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
         */
        double defaultRectWidth = Math.sqrt(2) * relRadius;

        mRect.left = (int) (centerPoint - defaultRectWidth / 2);
        mRect.top = mRect.left;
        mRect.bottom = (int) (mRect.left + defaultRectWidth);
        mRect.right = mRect.bottom;
        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */

        if (mImage.getWidth() < defaultRectWidth && mImage.getHeight() < defaultRectWidth)
        {
            mRect.left = (int) (centerPoint - mImage.getWidth() * 1.0f / 2);
            mRect.right = mRect.left + mImage.getWidth();
            mRect.top = centerPoint - mImage.getHeight()/2;
            mRect.bottom = mRect.top + mImage.getHeight();
        }
        //图片有任何一边大于园内切矩形边长时
        if(mImage.getWidth() >= defaultRectWidth || mImage.getHeight() >= defaultRectWidth){
            double rate = Math.max(mImage.getWidth(),mImage.getHeight())/defaultRectWidth;//压缩比率
            mRect.left = (int)(centerPoint-mImage.getWidth()/rate/2);
            mRect.right = (int)(centerPoint+mImage.getWidth()/rate/2);
            mRect.top = (int)(centerPoint-mImage.getHeight()/rate/2);
            mRect.bottom = (int)( centerPoint+mImage.getHeight()/rate/2);
        }
        // 绘图
        canvas.drawBitmap(mImage,null,mRect,mPaint);

    }
    /**
     * 根据参数画出每个小块
     *
     * @param canvas
     * @param centre
     * @param radius
     */
    private void drawOval(Canvas canvas,int centre,int radius){
        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        float itemSize = (360*1.0f - mSplitSize*mCount)/mCount;
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        mPaint.setColor(mFirstColor);// 设置圆环的颜色
        for(int i = 0; i < mCount; i++){//第一圈底色
            canvas.drawArc(oval, (itemSize+mSplitSize)* i+100 ,itemSize,false,mPaint);//+100是为了让正下面的空隙与水平线垂直（令正下方空隙左边的块作为起始块）
        }
        mPaint.setColor(mSecondColor); // 设置圆环的颜色
        for (int i = 0; i < mCurrentCount; i++)// 第二圈根据进度画圆弧
        {
            canvas.drawArc(oval, i * (itemSize + mSplitSize) + 100, itemSize, false, mPaint);
        }

    }
    /**
     * 添加触摸监听
     */
    /**
     * 当前数量+1
     */
    public void up()
    {
        if(mCurrentCount >= mCount)
            return;
        mCurrentCount++;
        postInvalidate();
    }
    /**
     * 当前数量-1
     */
    public void down()
    {
        if(mCurrentCount <= 0)
            return;
        mCurrentCount--;
        postInvalidate();
    }

    private int xDown, xUp;
    int emitOffset = 70;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) event.getY();
                System.out.println(xDown+"----xDown------ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
               /* xUp = (int) event.getY();
                if (xUp > xDown)// 下滑
                {
                    down();
                } else
                {
                    up();
                }*/
                break;

            case MotionEvent.ACTION_MOVE:
                xUp = (int) event.getY();
                int dY = xUp - xDown;
                if(Math.abs(dY) > emitOffset){
                    boolean isDown = xUp > xDown;
                    xDown = xUp;
                    if(isDown)//向下滑动
                        down();
                    else//向下滑动
                        up();

                }
                break;
        }
        return true;
    }
}

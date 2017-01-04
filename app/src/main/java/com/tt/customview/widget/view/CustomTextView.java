package com.tt.customview.widget.view;

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
public class CustomTextView extends View {
    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;


    public CustomTextView(Context context) {
        this(context,null);
    }
    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.cc,defStyleAttr,0);

        mTitleText = typedArray.getString(R.styleable.cc_titleText);
        mTitleTextColor = typedArray.getColor(R.styleable.cc_titleColor, Color.BLACK);
        mTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.cc_titleSize,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
/*
        for(int i = 0; i < typedArray.getIndexCount(); i ++){
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.cc_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.cc_titleColor:
                    mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.cc_titleSize:
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
                    break;
            }
        }
*/
        typedArray.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(),mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = -1;
        int height;
        //宽
        if(widthMode == MeasureSpec.EXACTLY){//EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
            width = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){//AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
            int textWidth = mBound.width();
            width = getPaddingLeft()+getPaddingRight()+textWidth;
        }else{
            System.out.println("widthMode----"+widthMode);
        }
        //高
        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            int textHeight = mBound.height();
            height = getPaddingTop() + textHeight + getPaddingBottom();
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        System.out.println(getWidth()+"------getMeasuredWidth");
        System.out.println(getHeight()+"------getMeasuredHeight");

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight() / 2 + mBound.height() / 2,mPaint);

        System.out.println(getWidth()+"------getWidth");
        System.out.println(getHeight()+"------getHeight");
    }
}

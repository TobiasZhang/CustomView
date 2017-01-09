package com.tt.customview.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by TT on 2017/1/8.
 */
public class ViewWithVDH extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    public ViewWithVDH(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper = ViewDragHelper.create(this, 1, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                   /* int topBorder = 0;
                    int bottomBorder = getHeight() - child.getHeight();
                    if(top <= topBorder)
                        top = topBorder;
                    else if(top >bottomBorder)
                        top = bottomBorder;*/
                return top;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
               /* final int leftBound = 0;
                final int rightBound = getWidth() - child.getWidth();

                left = Math.min(Math.max(left, leftBound), rightBound);*/

                return left;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                viewDragHelper.settleCapturedViewAt(0,0);
                invalidate();
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                viewDragHelper.captureChildView(getChildAt(0),pointerId);
            }

            @Override
            public int getViewHorizontalDragRange(View child)
            {
                return getMeasuredWidth()-child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child)
            {
                return getMeasuredHeight()-child.getMeasuredHeight();
            }


        });
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll()
    {
        if(viewDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }

}

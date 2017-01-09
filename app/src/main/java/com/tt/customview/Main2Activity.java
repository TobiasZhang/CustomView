package com.tt.customview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;

public class Main2Activity extends AppCompatActivity {
    ImageView img;
    View root;
    Scroller scroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*img = (ImageView) findViewById(R.id.img);
        root = findViewById(R.id.root);
        scroller = new Scroller(this);*/
    }


    public void go(View v){
        root.scrollBy(0,10);
    }

    int lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int currY = (int) event.getY();
//        System.out.println(root.getScrollY()+"---getScrollY");
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                lastY = currY;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dy = lastY - currY ;
//                int scrollY = root.getScrollY();
//                System.out.println(scrollY+"-scrollY----------------dy-"+dy);
//                if(dy < 0 && scrollY+dy <= 0){
//                    dy = -scrollY;
//                }
//                root.scrollBy(0,dy);
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        }
        return super.onTouchEvent(event);
    }
}

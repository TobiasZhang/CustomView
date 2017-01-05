package com.tt.customview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tt.customview.widget.viewgroup.FlowLayout;

public class MainActivity extends AppCompatActivity {
    FlowLayout flowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);

        flowLayout.addView(getTv("牛逼"));
        flowLayout.addView(getTv("呵呵"));
        flowLayout.addView(getTv("你大爷"));
        flowLayout.addView(getTv("购物车"));
        flowLayout.addView(getTv("机械键盘"));
        flowLayout.addView(getTv("大法师"));
        flowLayout.addView(getTv("咖喱棒棒"));
        flowLayout.addView(getTv("狗"));
        flowLayout.addView(getTv("鼠标垫"));
        flowLayout.addView(getTv("水浒"));
        flowLayout.addView(getTv("登山"));
        flowLayout.addView(getTv("打麻将"));
        flowLayout.addView(getTv("半岛铁匠"));
        flowLayout.addView(getTv("tt"));
        flowLayout.addView(getTv("a"));

    }

    TextView getTv(String str){
        TextView txt = new TextView(this);
        txt.setPadding(40,10,80,10);
        txt.setTextColor(Color.parseColor("#6699ff"));
        txt.setBackgroundResource(R.drawable.shape);
        ViewGroup.MarginLayoutParams mlp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = 10;
        mlp.setMargins(margin,margin,margin,margin);
        txt.setLayoutParams(mlp);

        txt.setTextSize(20);
        txt.setText(str);

        return txt;
    }
}

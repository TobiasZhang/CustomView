package com.tt.customview;

import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        System.out.println("----s----1111");
        System.out.println("----s----1111");
        LruCache<String,String> lruCache = new LruCache<>(20);
    }


}

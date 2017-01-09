package com.tt.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }
    public void btnClick(View v){
        String s = null;
        switch(v.getId()){
            case R.id.btn1:
                s="1";
                break;
            case R.id.btn2:
                s="2";
                break;
            case R.id.btn3:
                s="3";
                break;
        }
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}

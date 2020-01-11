package com.example.zh.clms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zh.clms.R;

public class GuideActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        System.out.println("Happy 1024 Programmer's Day" +
//                "好兄弟们要给自己加个鸡腿哦");

        //隐藏ActionBar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_guide);
        stagnation();

    }

    //让Activity停滞3秒
    private void stagnation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.guide_activity_in_anim, R.anim
                        .guide_activity_out_anim);
                finish();
                overridePendingTransition(R.anim.guide_activity_in_anim, R.anim
                        .guide_activity_out_anim);

            }
        }, 3000);
    }
}

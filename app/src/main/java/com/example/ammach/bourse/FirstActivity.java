package com.example.ammach.bourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        img=(ImageView) findViewById(R.id.icon);
        Animation customAlpha= AnimationUtils.loadAnimation(this, R.anim.custom_alpha);
        img.setAnimation(customAlpha);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                startActivity(new Intent(FirstActivity.this,MainActivity.class));
                finish();
            }
        }, 4300);
    }

}

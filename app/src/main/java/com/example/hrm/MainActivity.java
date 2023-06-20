package com.example.hrm;

import static com.example.hrm.R.*;
import static com.example.hrm.R.id.hrm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

private TextView text1;

private int count=0;
Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(layout.activity_main);
        text1=findViewById(id.text1);
       ImageView Hrm = findViewById(id.hrm);
        Animation logoAnimation = AnimationUtils.loadAnimation(MainActivity.this, anim.zoom_animation);

        Animation text1Animaion = AnimationUtils.loadAnimation(MainActivity.this, anim.zoom_animation);
        Hrm.startAnimation(logoAnimation);
        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Hrm.setVisibility(View.VISIBLE);
                Hrm.startAnimation(logoAnimation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                text1.setVisibility(View.VISIBLE);
                text1.startAnimation(text1Animaion);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
                finish();
            }
        },5000);

    }
}
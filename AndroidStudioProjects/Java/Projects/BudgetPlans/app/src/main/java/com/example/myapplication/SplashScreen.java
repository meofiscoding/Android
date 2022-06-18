package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH = 3000;
    private ImageView logo;
    private ImageView name;
    private LottieAnimationView lottieAnimationView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Init animation
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        //logo Animation
        logo = findViewById(R.id.logo);
        logo.animate().translationY(-2100).setDuration(1000).setStartDelay(2500);
        //Text animation
        name = findViewById(R.id.app_name);
        name.animate().translationY(-1600).setDuration(1000).setStartDelay(2500);
        //Lottie Animation
        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView.setAnimation(animation);
        lottieAnimationView.animate().translationYBy(1900).setDuration(1000).setStartDelay(2500);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreen.this, OnboardingActivity1.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH);
    }
}
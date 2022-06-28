package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    private static final int NUM_PAGES = 3;

    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    private TextView name;
    private ImageView splashImg;
    private LottieAnimationView lottieAnimationView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Init animation
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        //Text animation
        name = findViewById(R.id.app_name);
        name.animate().translationY(-2000).setDuration(1000).setStartDelay(4000);
        //Lottie Animation
        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView.setAnimation(animation);
        lottieAnimationView.animate().translationYBy(-2000).setDuration(1000).setStartDelay(4000);
        //Splash Img Animation
        splashImg = findViewById(R.id.splash_image);
        splashImg.animate().translationY(-2800).setDuration(1000).setStartDelay(4000);
        //View Pager
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), 1);
        viewPager.setAdapter(pagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm, int behaviour) {
            super(fm, behaviour);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    ImageView back;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //Init btn Login
        login = findViewById(R.id.btn_login);
        //Init btn Register
        register = findViewById(R.id.btn_register);
        //Back to fragment onClick
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            login.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.start_activity, new OnBoardingFragment3()).commit();
        });
    }
}
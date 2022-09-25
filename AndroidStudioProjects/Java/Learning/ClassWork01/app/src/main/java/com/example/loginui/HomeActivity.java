package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView txtShowName;
    private Button btnClose;

    private void bindingView(){
        txtShowName = findViewById(R.id.txtShowName);
        btnClose = findViewById(R.id.btnClose);
    }
    private void bindingAction(){
        btnClose.setOnClickListener(this::onCloseClick);
    }

    private void onCloseClick(View view) {
        this.finishAffinity();
//        System.exit(0);
    }

    private void receiveIntent(){
        Intent i = this.getIntent();
        String name = i.getStringExtra("name");
        txtShowName.setText("name: "+name);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindingView();
        bindingAction();
        receiveIntent();
    }
}
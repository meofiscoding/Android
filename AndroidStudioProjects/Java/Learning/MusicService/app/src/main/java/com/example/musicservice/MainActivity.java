package com.example.musicservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button startService;
    private Button stopService;

    private void bindingView() {
        startService = findViewById(R.id.start);
        stopService = findViewById(R.id.stop);
    }

    private void bindingAction() {
        startService.setOnClickListener(this::startMusic);
        stopService.setOnClickListener(this::stopMusic);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
    }

    private void stopMusic(View view) {
        Intent bi = new Intent(MyService.ACTION_STOP_MUSIC);
        bi.setPackage("com.example.musicservice");
        stopService(bi);
    }

    private void startMusic(View view) {
        Intent bi = new Intent(MyService.ACTION_START_MUSIC);
        bi.setPackage("com.example.musicservice");
        startService(bi);
    }
}
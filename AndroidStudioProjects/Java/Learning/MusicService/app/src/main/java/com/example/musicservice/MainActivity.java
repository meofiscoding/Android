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
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void startMusic(View view) {
        Song song = new Song("Lùn Thái Vũ", R.drawable.ic_baseline_library_music_24,R.raw.lalung);
        Intent i = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("my_song",song);
        i.putExtras(bundle);
        startService(i);
    }
}
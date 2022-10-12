package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressbar;
    private ImageView imv;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingViews();
        bindingAction();
        imv = findViewById(R.id.imageView);
        progressbar = findViewById(R.id.progressBar);
        String url = "https://baoquocte.vn/stores/news_dataimages/linhnguyen/112020/21/08/4940_hoa-hau-viet-nam-2020-1-1605897132611.jpg?rt=20201121084941";
    }

    private void bindingViews() {
        imv = findViewById(R.id.imageView);
        progressbar = findViewById(R.id.progressBar);
        button = findViewById(R.id.btnDownload);
    }

    private void bindingAction() {
        button.setOnClickListener(this::onDownloadImg);
    }

    private void onDownloadImg(View view) {
        DownloadAsyncTask asyn = new DownloadAsyncTask();
        asyn.execute(url);
    }
}
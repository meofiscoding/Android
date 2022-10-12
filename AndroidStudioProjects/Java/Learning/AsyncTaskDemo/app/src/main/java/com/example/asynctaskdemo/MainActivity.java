package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressbar;
    private ImageView imv;
    private Button button;
    private TextView txtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingViews();
        bindingAction();
    }

    private void bindingViews() {
        imv = findViewById(R.id.imageView);
        progressbar = findViewById(R.id.progressBar);
        button = findViewById(R.id.btnDownload);
        txtUrl = findViewById(R.id.txtUrl);
    }

    private void bindingAction() {
        button.setOnClickListener(this::onDownloadImg);
    }

    private void onDownloadImg(View view) {
        DownloadAsyncTask asyn = new DownloadAsyncTask();
        asyn.execute(txtUrl.getText().toString());
    }

    private class DownloadAsyncTask  extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream inputstream = connection.getInputStream();
                BufferedInputStream buffer = new BufferedInputStream(inputstream);

                Bitmap bitmap = BitmapFactory.decodeStream(buffer);
                return  bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imv.setImageBitmap(bitmap);
            progressbar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}

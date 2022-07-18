package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class CategoryDialog extends Dialog implements android.view.View.OnClickListener {
    private Button getAddCategory;
    private Context c;

    public CategoryDialog(@NonNull Context context) {
        super(context);
        this.c = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.task_category_dialog);
        getAddCategory = (Button) findViewById(R.id.CreateNewBtn);
        getAddCategory.setOnClickListener(v -> {
//            CreateCategory_Dialog acd = new CreateCategory_Dialog(c);
//            this.dismiss();
//            acd.show();
            Intent intent = new Intent();
            intent.setClass(v.getContext(), New_Category.class);
            v.getContext().startActivity(intent);
        });
        ImageView imageView1 = findViewById(R.id.grocery);
        ImageView imageView2 = findViewById(R.id.work);
        ImageView imageView3 = findViewById(R.id.sport);
        ImageView imageView4 = findViewById(R.id.design);
        ImageView imageView5 = findViewById(R.id.university);
        ImageView imageView6 = findViewById(R.id.social);
        ImageView imageView7 = findViewById(R.id.music);
        ImageView imageView8 = findViewById(R.id.health);
        ImageView imageView9 = findViewById(R.id.movie);
        ImageView imageView10 = findViewById(R.id.home);
        ImageView imageView11 = findViewById(R.id.createnew);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
        imageView10.setOnClickListener(this);
        imageView11.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String categoryName;
        switch (v.getId()) {
            case R.id.grocery:
                categoryName = "Grocery";
                break;
            case R.id.work:
                categoryName = "Work";
                break;
            case R.id.sport:
                categoryName = "Sport";
                break;
            case R.id.design:
                categoryName = "Design";
                break;
            case R.id.university:
                categoryName = "University";
                break;
            case R.id.social:
                categoryName = "Social";
                break;
            case R.id.music:
                categoryName = "Music";
                break;
            case R.id.health:
                categoryName = "Health";
                break;
            case R.id.movie:
                categoryName = "Movie";
                break;
            case R.id.home:
                categoryName = "Home";
                break;
            case R.id.createnew:
                break;
        }
    }
}

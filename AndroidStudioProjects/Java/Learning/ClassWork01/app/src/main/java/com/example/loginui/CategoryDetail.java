package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import model.Category;

public class CategoryDetail extends AppCompatActivity {
    private TextView txtCateName;
    private ImageView imgCate;
    private Category category;

    private void bindingView(){
        txtCateName = findViewById(R.id.txt_cateDetail);
        imgCate = findViewById(R.id.img_cateDetail);
    }
    private void receiveIntent(){
        category = (Category) getIntent().getExtras().getSerializable("category");
        imgCate.setImageResource(category.getId_drawable());
        txtCateName.setText(category.getTitle().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        bindingView();
        receiveIntent();
    }
}
package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Category;

public class HomeActivity extends AppCompatActivity {

//    private TextView txtShowName;
//    private Button btnClose;
    private RecyclerView rec_category;
    private DrawerLayout drawerLayout;
    private ImageView hamburgerMenu;

    private void bindingView(){
        rec_category = findViewById(R.id.rec_category);
        drawerLayout = findViewById(R.id.drawerLayout);
        hamburgerMenu = findViewById(R.id.hbgMenu);
//        txtShowName = findViewById(R.id.txtShowName);
//        btnClose = findViewById(R.id.btnClose);
    }
    private void bindingAction(){
        hamburgerMenu.setOnClickListener(this::onMenuClick);
//        btnClose.setOnClickListener(this::onCloseClick);
    }

    private void onMenuClick(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

//    private void onCloseClick(View view) {
//        this.finishAffinity();
//       System.exit(0);
//    }

//    private void receiveIntent(){
//        Intent i = this.getIntent();
//        String name = i.getStringExtra("name");
//        txtShowName.setText("name: "+name);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindingView();
        bindingAction();
        List<Category> categories = new ArrayList<>();
        Category cate1 = new Category("Pharmacy", R.drawable.drugs);
        Category cate2 = new Category("Registry", R.drawable.gift);
        Category cate3 = new Category("Cartwheel", R.drawable.trolley);
        Category cate4 = new Category("Clothing", R.drawable.swimsuit);
        Category cate5 = new Category("Shoes", R.drawable.shoes);
        Category cate6 = new Category("Accessories", R.drawable.handbag);
        Category cate7 = new Category("Baby", R.drawable.baby_clothes);
        Category cate8 = new Category("Home", R.drawable.home_button);
        Category cate9 = new Category("Patio & Garden", R.drawable.barbecue);
        Category cate10 = new Category("Cider", R.drawable.cider);
        Category cate11 = new Category("Cider", R.drawable.cider);
        Category cate12 = new Category("Cider", R.drawable.cider);
        Category cate13 = new Category("Cider", R.drawable.cider);
        Category cate14 = new Category("Cider", R.drawable.cider);
        Category cate15 = new Category("Cider", R.drawable.cider);
        Category cate16 = new Category("Baby", R.drawable.baby_clothes);
        categories.add(cate1);
        categories.add(cate2);
        categories.add(cate3);
        categories.add(cate4);
        categories.add(cate5);
        categories.add(cate6);
        categories.add(cate7);
        categories.add(cate8);
        categories.add(cate9);
        categories.add(cate10);
        categories.add(cate11);
        categories.add(cate12);
        categories.add(cate13);
        categories.add(cate14);
        categories.add(cate15);
        categories.add(cate16);
        CategoryAdapter adapter = new CategoryAdapter(categories, HomeActivity.this);
        rec_category.setLayoutManager(new GridLayoutManager(this, 3));
        rec_category.setAdapter(adapter);
//        receiveIntent();
    }
}
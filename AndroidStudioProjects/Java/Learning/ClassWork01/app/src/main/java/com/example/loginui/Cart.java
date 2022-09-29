package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import model.Category;

public class Cart extends AppCompatActivity {
    private List<Category> carts;
    private RecyclerView rcvCartList;
    private void bindingView(){
        rcvCartList = findViewById(R.id.rcvCart);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindingView();
        carts = new ArrayList<>();
        initData(carts);

        CartAdapter adapter = new CartAdapter(carts, Cart.this);
//        rec_chapters.setLayoutManager(new GridLayoutManager(this, 2));
        rcvCartList.setLayoutManager(new LinearLayoutManager(this));
        rcvCartList.setAdapter(adapter);
    }
    private void initData(List<Category> cart) {
        Category cate1 = new Category("Pharmacy", R.drawable.drugs);
        Category cate2 = new Category("Registry", R.drawable.gift);
        cart.add(cate1);
        cart.add(cate2);
    }

}
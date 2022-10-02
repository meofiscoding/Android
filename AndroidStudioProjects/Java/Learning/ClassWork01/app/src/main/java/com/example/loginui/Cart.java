package com.example.loginui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Category;

public class Cart extends AppCompatActivity {
    private List<Category> carts;
    private RecyclerView rcvCartList;

    private void bindingView() {
        rcvCartList = findViewById(R.id.rcvCart);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bindingView();
        carts = HomeActivity.getInstance().getCart();
        CartAdapter adapter = new CartAdapter(carts, Cart.this);
        rcvCartList.setLayoutManager(new LinearLayoutManager(this));
        rcvCartList.setAdapter(adapter);
    }


}
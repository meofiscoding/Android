package com.example.loginui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nex3z.notificationbadge.NotificationBadge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class HomeActivity extends AppCompatActivity {

//    private TextView txtShowName;
//    private Button btnClose;
    private RecyclerView rec_category;
    private DrawerLayout drawerLayout;
    private ImageView hamburgerMenu;
    private View viewEndAnimation;
    private View cart_menu;
    private NotificationBadge notificationBadge;
    private ImageView viewAnimation;
    private List<Category> categories;
    private Toolbar toolbar;
    private final int notificationId = 101;
    public List<Category> cart;
    private CartAdapter cartAdapter;
    private final String NOTIFICATION_CHANNEL = "My channel";

    private void bindingView(){
        rec_category = findViewById(R.id.rec_category);
        drawerLayout = findViewById(R.id.drawerLayout);
        hamburgerMenu = findViewById(R.id.hbgMenu);
        toolbar = findViewById(R.id.toolBar);
        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);
//        txtShowName = findViewById(R.id.txtShowName);
//        btnClose = findViewById(R.id.btnClose);
    }
    private void bindingAction(){
        hamburgerMenu.setOnClickListener(this::onMenuClick);
//        btnClose.setOnClickListener(this::onCloseClick);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        if (cart.size()!= 0){
            Intent resultIntent = new Intent(this, Cart.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_MUTABLE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.star_big_on)
                    .setContentTitle("Shoppe")
                    .setContentText("You have 2 product is on sale, check out now!!")
                    .setChannelId(NOTIFICATION_CHANNEL)
                    .setContentIntent(pendingIntent);

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, "My notification channel", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(notificationId, builder.build());
        }
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
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        categories = new ArrayList<>();
        initData(categories);
        CategoryAdapter adapter = new CategoryAdapter(categories, HomeActivity.this);
        rec_category.setLayoutManager(new GridLayoutManager(this, 3));
        rec_category.setAdapter(adapter);
        cart = new ArrayList<>();
        adapter.setData(categories, new CategoryAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Category category) {
                AnimationUtil.translateAnimation(viewAnimation, imgAddToCart, toolbar, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        cart.add(category);
                        cartAdapter = new CartAdapter(cart, HomeActivity.this);
                        updateCartCount();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

//        receiveIntent();
    }

    private void updateCartCount() {
        if(notificationBadge == null) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (cart.size() == 0){
                    notificationBadge.setVisibility(View.INVISIBLE);
                }else{
                    notificationBadge.setVisibility(View.VISIBLE);
                    notificationBadge.setText(String.valueOf(cart.size()));
                }
            }
        });
    }

    private void initData(List<Category> categories) {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        cart_menu = menu.findItem(R.id.cart_checkout).getActionView();
        notificationBadge = (NotificationBadge) cart_menu.findViewById(R.id.badge);
        updateCartCount();
        return super.onCreateOptionsMenu(menu);
    }
}
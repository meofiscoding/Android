package com.example.loginui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import model.Category;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //    private TextView txtShowName;
//    private Button btnClose;
    private static final int FRAGMENT_CONTENT_PROVIDER = 1;
    private int currentFragment = FRAGMENT_CONTENT_PROVIDER;
    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView rec_category;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView hamburgerMenu;
    private View viewEndAnimation;
    private View cart_menu;
    private NotificationBadge notificationBadge;
    private ImageView viewAnimation;
    private List<Category> categories;
    private Toolbar toolbar;
    private final int notificationId = 101;
    public List<Category> cart = new ArrayList<>();
    private CartAdapter cartAdapter;
    private final String NOTIFICATION_CHANNEL = "My channel";
    private static HomeActivity uniqInstance;

    public static HomeActivity getInstance() {
        if (uniqInstance == null)
            uniqInstance = new HomeActivity();
        return uniqInstance;
    }

    public List<Category> getCart() {
        return cart;
    }

    public void setCart(List<Category> cart) {
        this.cart = cart;
    }

    private void bindingView() {
        rec_category = findViewById(R.id.rec_category);
        drawerLayout = findViewById(R.id.drawerLayout);
        hamburgerMenu = findViewById(R.id.hbgMenu);
        toolbar = findViewById(R.id.toolBar);
        viewEndAnimation = findViewById(R.id.view_end_animation);
        viewAnimation = findViewById(R.id.view_animation);
        navigationView = findViewById(R.id.navigationView);
//        txtShowName = findViewById(R.id.txtShowName);
//        btnClose = findViewById(R.id.btnClose);
    }

    private void bindingAction() {
        hamburgerMenu.setOnClickListener(this::onMenuClick);
        navigationView.setNavigationItemSelectedListener(this);
//        btnClose.setOnClickListener(this::onCloseClick);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent i = new Intent(this, DatabaseActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }
//        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        if (cart.size() != 0) {
            Intent resultIntent = new Intent(this, Cart.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_MUTABLE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.star_big_on)
                    .setContentTitle("Shoppe")
                    .setContentText("You have " + cart.size() + " product in cart, check out now!!")
                    .setChannelId(NOTIFICATION_CHANNEL)
                    .setContentIntent(pendingIntent);

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, "My notification channel", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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
        setSupportActionBar(toolbar);
        bindingView();
        bindingAction();
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawerLayout)
                .build();

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        categories = new ArrayList<>();
        initData(categories);
        CategoryAdapter adapter = new CategoryAdapter(categories, HomeActivity.this);
        rec_category.setLayoutManager(new GridLayoutManager(this, 3));
        rec_category.setAdapter(adapter);
        adapter.setData(categories, new CategoryAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Category category) {
                AnimationUtil.translateAnimation(viewAnimation, imgAddToCart, toolbar, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        cart.add(category);
                        HomeActivity.getInstance().setCart(cart);
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
        if (notificationBadge == null) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (cart.size() == 0) {
                    notificationBadge.setVisibility(View.INVISIBLE);
                } else {
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.loginui.fragment.CategoryFragment;
import com.example.loginui.fragment.ProductAPIFragment;
import com.example.loginui.fragment.ProductROOMFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CategoryFragment();
            case 1:
                return new ProductROOMFragment();
            case 2:
                return new ProductAPIFragment();
            default:
                return new CategoryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

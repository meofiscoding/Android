package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.ui.index.HomeActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class New_Category extends AppCompatActivity {
    private TextView cancelAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        //Set bottomsheet behaviour
//        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        //Back to fragment onClick
        cancelAction = findViewById(R.id.cancel_action);
        cancelAction.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), HomeActivity.class);
            intent.putExtra("backNavigation", "true");
            startActivity(intent);
        });

    }

}
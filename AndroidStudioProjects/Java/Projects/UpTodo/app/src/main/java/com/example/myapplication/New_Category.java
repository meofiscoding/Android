package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class New_Category extends AppCompatActivity {
    private TextView cancelAction;
    private ConstraintLayout layoutBottomSheet;
    //Bottom sheet behavior
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        layoutBottomSheet = findViewById(R.id.bottomSheet);
        //Set bottomsheet behaviour
//        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        //Back to fragment onClick
        cancelAction = findViewById(R.id.cancel_action);
        cancelAction.setOnClickListener(v -> {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });

    }

}
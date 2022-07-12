package com.example.myapplication.ui.index;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.CalendarFragment;
import com.example.myapplication.FocuseFragment;
import com.example.myapplication.HomeFragment;
import com.example.myapplication.ProfileFragment;
import com.example.myapplication.R;
import com.example.myapplication.SplashScreen;
import com.example.myapplication.databinding.ActivityHomeBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    //Init variable Firebase
    private FirebaseAuth mAuth;
    //Binding
    private ActivityHomeBinding binding;
    //FAB Button
    private FloatingActionButton mFAB;
    //New task layout
    private ConstraintLayout layoutBottomSheet;
    //Bottom sheet behavior
    private BottomSheetBehavior bottomSheetBehavior;
    private int hours, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        replaceFragment(new HomeFragment());
        //onClick button to show layoutBottomSheet
        mFAB = findViewById(R.id.floatingActionButton2);
        //Bottom sheet Assign
        layoutBottomSheet = findViewById(R.id.bottomSheet);
        //Set bottomsheet behaviour
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        //FAB onClick
        mFAB.setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                //Open bottomsheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                //Open timepicker on Img_timer onCLick
                ImageView timer = layoutBottomSheet.findViewById(R.id.timer_img);
                timer.setOnClickListener(view -> {
                    //Init time picker dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            layoutBottomSheet.getContext(), (view1, hourOfDay, minute) -> {
                        //Init hours and minutes
                        hours = hourOfDay;
                        minutes = minute;
                        //Init Calendar
                        Calendar calendar = Calendar.getInstance();
                        //Set hours and minutes
                        calendar.set(0, 0, 0, hours, minutes);
                        //Set selected time on textview
                        //...
                    }, 12, 0, false
                    );
                    //Display previous selected times
                    timePickerDialog.updateTime(hours, minutes);
                    //Show dialog
                    timePickerDialog.show();
                });
                //Flag onClick

                //Category onClick

                //Send onCLick


            } else {
                //Close bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //binding view to navigate between fragment
        binding.transparentNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.calendar:
                    replaceFragment(new CalendarFragment());
                    break;
                case R.id.focuse:
                    replaceFragment(new FocuseFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    //Check if user is authenticated or not
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser == null) {
            startActivity(new Intent(HomeActivity.this, SplashScreen.class));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
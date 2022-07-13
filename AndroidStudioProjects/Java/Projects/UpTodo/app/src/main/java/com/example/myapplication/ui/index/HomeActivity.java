package com.example.myapplication.ui.index;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.CalendarFragment;
import com.example.myapplication.FocuseFragment;
import com.example.myapplication.HomeFragment;
import com.example.myapplication.ProfileFragment;
import com.example.myapplication.R;
import com.example.myapplication.SplashScreen;
import com.example.myapplication.data.TaskDAO;
import com.example.myapplication.databinding.ActivityHomeBinding;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Priority;
import com.example.myapplication.model.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.chip.Chip;
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
    private RadioGroup priorityRadioGroup;
    private RadioButton seleectedradiobutton;
    private int selectedButtonID;
    private CalendarView calendarView;
    private TextView enter_todo_txt;
    private ImageView saveButton;
    private Group calendarGroup;
    private EditText description_txt;

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
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
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
                //Category onClick
                ImageView category = layoutBottomSheet.findViewById(R.id.category_img);
                category.setOnClickListener(v1 -> {
                    Dialog dialog = new Dialog(layoutBottomSheet.getContext());
                    dialog.setContentView(R.layout.task_category_dialog);
                    dialog.show();
                });
                //Flag onClick
                ImageView priority = layoutBottomSheet.findViewById(R.id.priority_todo_button);
                priorityRadioGroup = layoutBottomSheet.findViewById(R.id.radioGroup_priority);

                //Send onCLick
                calendarGroup = layoutBottomSheet.findViewById(R.id.calendar_group);
                calendarView = layoutBottomSheet.findViewById(R.id.calendar_view);
                Chip todaychip = layoutBottomSheet.findViewById(R.id.today_chip);
                Chip tomorrow = layoutBottomSheet.findViewById(R.id.tomorrow_chip);
                Chip nextweekchip = layoutBottomSheet.findViewById(R.id.next_week_chip);
                enter_todo_txt = layoutBottomSheet.findViewById(R.id.enter_todo_et);
                description_txt = layoutBottomSheet.findViewById(R.id.description);
                saveButton = layoutBottomSheet.findViewById(R.id.saveBtn);
                //Assign and Init TaskDAO
                TaskDAO taskDAO = new TaskDAO();
                saveButton.setOnClickListener(v2 -> {
                    String task = enter_todo_txt.getText().toString().trim();
                    String description = description_txt.getText().toString().trim();
                    if (!TextUtils.isEmpty(task)) {
                        Task myTask = new Task(task, description, Priority.HIGH, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), false, new Category("University"));
                        taskDAO.add(myTask).addOnSuccessListener(success -> {
                            Toast.makeText(layoutBottomSheet.getContext(), "task added successfully", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener( err->{
                            Toast.makeText(layoutBottomSheet.getContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }
                 });
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
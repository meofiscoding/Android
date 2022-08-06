package com.example.myapplication.ui.index;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.CalendarFragment;
import com.example.myapplication.CategoryDialog;
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
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements CategoryDialog.OnMyDialogResult {
    //Init variable Firebase
    private FirebaseAuth mAuth;
    //New task layout
    private ConstraintLayout layoutBottomSheet;
    //Bottom sheet behavior
    private BottomSheetBehavior bottomSheetBehavior;
    private int hours, minutes;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonID;
    private CalendarView calendarView;
    private TextView enter_todo_txt;
    private ImageView saveButton;
    private EditText description_txt;
    private Date dueDate;
    private ChipGroup category_tag;
    protected Priority priority;
    protected String categoryName;


    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        replaceFragment(new HomeFragment());
        //onClick button to show layoutBottomSheet
        //FAB Button
        FloatingActionButton mFAB = findViewById(R.id.floatingActionButton2);
        //Bottom sheet Assign
        layoutBottomSheet = findViewById(R.id.bottomSheet);
        //Set bottomsheet behaviour
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        //get intent back navigation
        Intent intent = getIntent();
        if (Boolean.parseBoolean(intent.getStringExtra("backNavigation"))) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            CategoryDialog cdd = new CategoryDialog(layoutBottomSheet.getContext());
            CategoryDialog categoryDialog = new CategoryDialog();
            categoryDialog.show(getSupportFragmentManager(), "category dialog");
        }
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
                        //Set hours and minutes
                        calendar.set(0, 0, 0, hours, minutes);
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
//                    Dialog dialog = new Dialog(layoutBottomSheet.getContext());
//                    dialog.setContentView(R.layout.task_category_dialog);
//                    dialog.show();
                    CategoryDialog cdd = new CategoryDialog();
                    cdd.show(getSupportFragmentManager(), "category dialog");
                });
                //Display category name
                category_tag = layoutBottomSheet.findViewById(R.id.chip_group);
                //Flag Priority onClick
                ImageView flag = layoutBottomSheet.findViewById(R.id.priority_todo_button);
                priorityRadioGroup = layoutBottomSheet.findViewById(R.id.radioGroup_priority);
                flag.setOnClickListener(view3 -> {
                    //set priority visible
                    priorityRadioGroup.setVisibility(priorityRadioGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                    //check which radiobutton has been check
                    priorityRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                        if (priorityRadioGroup.getVisibility() == View.VISIBLE) {
                            selectedButtonID = checkedId;
                            selectedRadioButton = layoutBottomSheet.findViewById(selectedButtonID);
                            if (selectedRadioButton.getId() == R.id.radioButton_high) {
                                priority = Priority.HIGH;
                            } else if (selectedRadioButton.getId() == R.id.radioButton_med) {
                                priority = Priority.MEDIUM;
                            } else {
                                priority = Priority.LOW;
                            }
                        } else {
                            priority = Priority.LOW;
                        }
                    });
                });
                //Get date from Calendar View
                calendarView = layoutBottomSheet.findViewById(R.id.calendar_view);
                calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMoth) -> {
                    //Set clean state of calendar
                    calendar.clear();
                    calendar.set(year, month, dayOfMoth);
                    dueDate = calendar.getTime();
                });
                //SendBtn onCLick
                //Implement today_chip onClick
                Chip today_chip = layoutBottomSheet.findViewById(R.id.today_chip);
                today_chip.setOnClickListener(view5 -> {
                    //set data for today
                    calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    calendar.add(Calendar.DAY_OF_YEAR, 0);
                    calendarView.setDate(calendar.getTime().getTime(), true, true);
                    dueDate = calendar.getTime();
                });
                //Implement tomorrow_chip onClick
                Chip tomorrow = layoutBottomSheet.findViewById(R.id.tomorrow_chip);
                tomorrow.setOnClickListener(view4 -> {
                    //set data for today
                    calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), hours, minutes);
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    calendarView.setDate(calendar.getTime().getTime(), true, true);
                    dueDate = calendar.getTime();
                });
                //Implement next_week_chip onClick
                Chip next_week_chip = layoutBottomSheet.findViewById(R.id.next_week_chip);
                next_week_chip.setOnClickListener(view6 -> {
                    //set data for today
                    calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    calendar.add(Calendar.DAY_OF_YEAR, 7);
                    calendarView.setDate(calendar.getTime().getTime(), true, true);
                    dueDate = calendar.getTime();
                });
                //Assign variable
                enter_todo_txt = layoutBottomSheet.findViewById(R.id.enter_cate_name);
                description_txt = layoutBottomSheet.findViewById(R.id.description);
                saveButton = layoutBottomSheet.findViewById(R.id.saveBtn);
                //Assign and Init TaskDAO
                TaskDAO taskDAO = new TaskDAO();
                saveButton.setOnClickListener(v2 -> {
                    String task = enter_todo_txt.getText().toString().trim();
                    String description = description_txt.getText().toString().trim();
                    if (!TextUtils.isEmpty(task) && categoryName != null) {
                        Task myTask = new Task(task, description, priority, dueDate, Calendar.getInstance().getTime(), false, new Category(categoryName));
                        taskDAO.add(myTask).addOnSuccessListener((success) -> {
                                    Toast.makeText(layoutBottomSheet.getContext(), "task added successfully", Toast.LENGTH_SHORT).show();
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                })
                                .addOnFailureListener(err -> Toast.makeText(layoutBottomSheet.getContext(), err.getMessage(), Toast.LENGTH_SHORT).show());
                    } else {
                        if (TextUtils.isEmpty(task)) {
                            Toast.makeText(layoutBottomSheet.getContext(), "Please choose enter Task name", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(layoutBottomSheet.getContext(), "Please choose one category", Toast.LENGTH_SHORT).show();
                        }
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

    @Override
    public void finish(String result) {
        categoryName = result;
        Chip chip = new Chip(layoutBottomSheet.getContext());
        chip.setText(categoryName);
        chip.setChipBackgroundColor(AppCompatResources.getColorStateList(layoutBottomSheet.getContext(), R.color.primary));
        chip.setCloseIcon(getDrawable(R.drawable.ic_baseline_clear_24));
        chip.setCloseIconVisible(true);
        //onClose chip
         chip.setOnCloseIconClickListener(v -> {
             category_tag.removeView(v);
             categoryName = null;
        });
        category_tag.addView(chip);
    }
}
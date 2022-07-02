package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    //Init instance of back btn
    private ImageView back;
    //Init instance of register with google btn
    private Button register_google;
    //Init instance of Register button
    private Button register;
    //Init instance of Login TextView
    private TextView login;
    //init variable Firebase
    FirebaseAuth mauth;
    //Init instance of EditText email
    private EditText email_txt;
    //Init instance of EditText password
    private EditText password_txt;
    //Init instance of EditText confirm password
    private EditText cf_password_txt;
    //init variable to get EditText content
    private String email;
    private String password;
    private String cf_password;
    //Init variable Img show/hide password
    ImageView imageViewShowHidePassword;
    //init variable Img show/hide confirm password
    ImageView imageViewShowHide_Cf_Password;

    //Get text of EditText
    private void getText() {
        email = email_txt.getText().toString();
        password = password_txt.getText().toString();
        cf_password = cf_password_txt.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Init Firebase Auth
        mauth = FirebaseAuth.getInstance();
        //Init Img show/hide password
        imageViewShowHidePassword = findViewById(R.id.img_show_hide_password);
        imageViewShowHide_Cf_Password = findViewById(R.id.img_show_hide_cf_password);
        //Init instance EditText
        email_txt = findViewById(R.id.email_txt);
        password_txt = findViewById(R.id.password_txt);
        cf_password_txt = findViewById(R.id.cfpassword_txt);
        //Go back activity
        back = findViewById(R.id.backbtn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), LoginActivity.class);
            v.getContext().startActivity(intent);
        });
        //Go to Login activity
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), LoginActivity.class);
            v.getContext().startActivity(intent);
        });
        //Check confirm_password onInputChange
        cf_password_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getText();
//                Toast.makeText(RegisterActivity.this, String.format("%s", password), Toast.LENGTH_SHORT).show();
                if (!s.toString().equals(password)) {
                    cf_password_txt.requestFocus();
                    cf_password_txt.setError("Confirm Password is not match with password");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //Show/hide password using Eye Icon
        imageViewShowHidePassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        imageViewShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password_txt.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    //If password is visible then hide it
                    password_txt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Change icon
                    imageViewShowHidePassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                } else {
                    password_txt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                }
            }
        });
        //Show/hide confirm password using Eye Icon
        imageViewShowHide_Cf_Password.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        imageViewShowHide_Cf_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cf_password_txt.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    //If password is visible then hide it
                    cf_password_txt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Change icon
                    imageViewShowHide_Cf_Password.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                } else {
                    cf_password_txt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHide_Cf_Password.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                }
            }
        });
        //btn Register onClick
        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(v -> {
            //validation EditText
            getText();
            boolean check = validationInfo(email, password, cf_password);
            //Validation form
            if (check) {
                createUser();
            }
        });
    }

    //Validation form function
    private Boolean validationInfo(String email, String password, String cf_password) {
        if (email.length() == 0) {
            email_txt.requestFocus();
            email_txt.setError("Field can not be empty");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            email_txt.requestFocus();
            email_txt.setError("Please enter valid email");
            return false;
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            password_txt.requestFocus();
            password_txt.setError("Password must have minimum 8 characters, contains uppercase letter, lowercase letter, number and special character:");
            return false;
        } else if (password.compareTo(cf_password) != 0) {
            cf_password_txt.requestFocus();
            cf_password_txt.setError("Confirm Password is not match with password");
            return false;
        } else {
            return true;
        }
    }

    //Create User with Firebase
    private void createUser() {
        //Get text in form
        getText();
        //Authentication with Firebase
        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User registered successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Registration Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
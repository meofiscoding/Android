package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //Init instance of back btn
    private ImageView back;
    //Init instance of register with google btn
    private Button register_google;
    //Init instance of Register button
    private Button register;
    //Init instance of Login TextView
    private TextView login;
    //Init instance of EditText email
    private EditText email_txt;
    //Init instance of EditText password
    private EditText password_txt;
    //Init instance of EditText confirm password
    private EditText cf_password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Init instance EditText
        email_txt = findViewById(R.id.email_txt);
        password_txt = findViewById(R.id.password_txt);
        cf_password_txt = findViewById(R.id.cfpassword_txt);
        //validation EditText
        String email = email_txt.getText().toString();
        String password = password_txt.getText().toString();
        String cf_password = cf_password_txt.getText().toString();
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
        //focus out to check confirm password
        cf_password_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Toast.makeText(RegisterActivity.this, "not focus", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //btn Register onClick
        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(v -> {
            Toast.makeText(this, "btn_clicked", Toast.LENGTH_SHORT).show();
            boolean check = validationInfo(email, password, cf_password);
            //Validation form
            if (check) {
                createUser();
            }
        });
    }

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
            cf_password_txt.setError("confirm password is not match with password");
            return false;
        } else {
            return true;
        }
    }

    private void createUser() {

    }
}
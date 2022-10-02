package com.example.loginui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen2 extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtPassword;
    private EditText edtUsername;

    private void bindingView(){
        btnLogin = findViewById(R.id.btn_login);
        edtPassword = findViewById(R.id.edtpassword);
        edtUsername = findViewById(R.id.edtusername);
    }

    private void onLoginClick(View view){
//        if (edtPassword.getText().toString().equals("123456") && edtUsername.getText().toString().equals("admin")){
//            Toast.makeText(LoginScreen2.this, "Login successfully!!", Toast.LENGTH_SHORT).show();
//            //this present for current context
//            Intent i = new Intent(this, HomeActivity.class);
//            i.putExtra("name",edtUsername.getText().toString());
//            startActivityForResult(i,1);
////            startActivity(i);
//        }else{
//            Toast.makeText(LoginScreen2.this, "Login failed", Toast.LENGTH_SHORT).show();
//        }
                    Intent i = new Intent(this, HomeActivity.class);
                    startActivity(i);
    }

    public void bindingAction(){
        btnLogin.setOnClickListener(this::onLoginClick);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);
        bindingView();
        bindingAction();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        edtUsername.getText().clear();
        edtPassword.getText().clear();
    }
}
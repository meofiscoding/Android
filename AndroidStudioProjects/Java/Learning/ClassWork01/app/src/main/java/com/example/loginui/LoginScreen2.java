package com.example.loginui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen2 extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtPassword;
    private EditText edtUsername;
    CheckBox cb_check;
    SharedPreferences spf;
    SharedPreferences.Editor editor;
    private static String USERNAME_KEY = "username";

    private void bindingView(){
        btnLogin = findViewById(R.id.btn_login);
        edtPassword = findViewById(R.id.edtpassword);
        edtUsername = findViewById(R.id.edtusername);
        cb_check = findViewById(R.id.cb_remember);
    }

    private void onLoginClick(View view){
        String userName = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if (cb_check.isChecked()) {
            editor = spf.edit();
            editor.putString(USERNAME_KEY, userName);
            editor.putString("password", password);
            editor.putBoolean("checked", cb_check.isChecked());
            editor.commit();
        } else {
            editor = spf.edit();
            editor.remove(USERNAME_KEY);
            editor.remove("password");
            editor.remove("checked");
            editor.commit();
        }
        if (password.equals("123456") && userName.equals("admin")){
            Toast.makeText(LoginScreen2.this, "Login successfully!!", Toast.LENGTH_SHORT).show();
            //this present for current context
            Intent i = new Intent(this, HomeActivity.class);
//            i.putExtra("name",edtUsername.getText().toString());
//            startActivityForResult(i,1);
            startActivity(i);
        }else{
            Toast.makeText(LoginScreen2.this, "Login failed", Toast.LENGTH_SHORT).show();
        }

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
        spf = this.getSharedPreferences("account_storage", Context.MODE_PRIVATE);
        String usn_str = spf.getString(USERNAME_KEY, "");
        String pass_str = spf.getString("password", "");
        boolean check_stt = spf.getBoolean("checked", false);
        edtUsername.setText(usn_str);
        edtPassword.setText(pass_str);
        cb_check.setChecked(check_stt);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        edtUsername.getText().clear();
        edtPassword.getText().clear();
    }
}
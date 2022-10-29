package com.example.myapplication;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.myapplication.model.Workplace;
import com.example.myapplication.ui.index.HomeActivity;
import com.example.myapplication.ui.index.Workplace_recycle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {
    //Biometric variable
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    //Init instance fingerprint ImageView
    private ImageView fingerprint;
    //Init instance of Firebase Authentication
    private FirebaseAuth mAuth;
    //Init instance of register redirect
    private TextView register;
    //Init instance of backbtn
    private ImageView back;
    //Init button Login
    private Button login;
    //init variable to get EditText content
    private String email;
    private String password;
    //Init instance of EditText confirm password
    private EditText password_txt;
    //Init instance of EditText email
    private EditText email_txt;
    //Init variable Img show/hide password
    ImageView imageViewShowHidePassword;

    //Get text of EditText
    private void getText() {
        email = email_txt.getText().toString();
        password = password_txt.getText().toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Init instance EditText
        email_txt = findViewById(R.id.email_txt);
        password_txt = findViewById(R.id.password_txt);
        getText();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //Init Img show/hide password
        imageViewShowHidePassword = findViewById(R.id.img_show_hide_password);
        //Check if biometric authentication is available in device
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this, "App can authenticate using biometrics.", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "No biometric features available on this device.", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Biometric features are currently unavailable.", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, 101010);
                break;
        }
        //Showing biometric authentication
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        fingerprint = findViewById(R.id.fingerprint);
        fingerprint.setOnClickListener(view -> biometricPrompt.authenticate(promptInfo));
        //Register Text view
        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), RegisterActivity.class);
            v.getContext().startActivity(intent);
        });
        //Go back activity
        back = findViewById(R.id.backbtn);
        back.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(v.getContext(), StartActivity.class);
            v.getContext().startActivity(intent);
        });
        //Onclick Login button
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> loginUser());

        //Show/hide password using Eye Icon
        imageViewShowHidePassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        imageViewShowHidePassword.setOnClickListener(v -> {
            if (password_txt.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                //If password is visible then hide it
                password_txt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                //Change icon
                imageViewShowHidePassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
            } else {
                password_txt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                imageViewShowHidePassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
            }
        });
    }

    private void loginUser() {
        //Get text in form
        getText();
        //Authentication with Firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "User login successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, Workplace_recycle.class));
            } else {
                Toast.makeText(LoginActivity.this, "Login error"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
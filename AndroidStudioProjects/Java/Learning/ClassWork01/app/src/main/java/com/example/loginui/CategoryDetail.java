package com.example.loginui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.Category;

public class CategoryDetail extends AppCompatActivity {
    private TextView txtCateName;
    private ImageView imgCate;
    private Category category;
    private EditText edtPhoneNumber;
    private Button btnCall;
    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;

    private void bindingView(){
        txtCateName = findViewById(R.id.txt_cateDetail);
        imgCate = findViewById(R.id.img_cateDetail);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnCall = findViewById(R.id.btnCall);
    }
    private void receiveIntent(){
        category = (Category) getIntent().getExtras().getSerializable("category");
        imgCate.setImageResource(category.getId_drawable());
        txtCateName.setText(category.getTitle().toString());
    }
    private void bindingAction(){
        btnCall.setOnClickListener(this::onCallClick);
    }

    private void onCallClick(View view) {
        askPermissionAndCall();
    }

    private void askPermissionAndCall() {

        // With Android Level >= 23, you have to ask the user
        // for permission to Call.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

            // Check if we have Call permission
            int callPermisson = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (callPermisson != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Permission Call")
                            .setMessage("This permission is needed because this and that")
                            .setPositiveButton("OK", (DialogInterface dialogInterface, int i) -> {
                                this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST_CODE_CALL_PHONE);
                                return;
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            }else{
                this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST_CODE_CALL_PHONE);
                return;
            }
        }
        this.makeCall();
    }

    @SuppressLint("MissingPermission")
    private void makeCall() {
        String phoneNumber = edtPhoneNumber.getText().toString(); //this.editTextPhoneNumber.getText().toString();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            this.startActivity(callIntent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Your call failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE_CALL_PHONE:
                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (CALL_PHONE).
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();
                    this.makeCall();
                }
                // Cancelled or denied.
                else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    // When results returned
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_PERMISSION_REQUEST_CODE_CALL_PHONE) {
            if (resultCode == RESULT_OK) {
                // Do something with data (Result returned).
                Toast.makeText(this, "Action OK", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        bindingView();
        bindingAction();
        receiveIntent();
    }
}
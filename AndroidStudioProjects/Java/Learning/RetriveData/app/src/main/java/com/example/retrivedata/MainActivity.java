package com.example.retrivedata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.example.loginui.MyContentProvider";
    private static final String PRODUCTS_TABLE = "product";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCTS_TABLE);
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_PRODUCT_NAME = "ProductName";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "ProductDescription";
    public static final String COLUMN_PRICE = "ProductPrice";
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Retrieve student records
        Cursor c = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Log.d("MainActivity", c.getString(c.getColumnIndex(COLUMN_ID)) + ", " + c.getString(c.getColumnIndex(COLUMN_PRODUCT_NAME)));
            } while (c.moveToNext());
        }
    }
}
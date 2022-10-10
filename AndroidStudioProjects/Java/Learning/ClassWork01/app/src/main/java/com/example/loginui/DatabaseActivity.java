package com.example.loginui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import model.Product;

public class DatabaseActivity extends AppCompatActivity {
    private TextView id;
    private EditText productName;
    private EditText description;
    private EditText price;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnfind;

    private void bindingView() {
        id = (TextView) findViewById(R.id.texViewPID);
        productName = (EditText) findViewById(R.id.editTextProductName);
        description = (EditText) findViewById(R.id.editTextDescription);
        price = (EditText) findViewById(R.id.editTextPrice);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnfind = findViewById(R.id.buttonFind);
    }

    private void bindingAction() {
        btnAdd.setOnClickListener(this::addProduct);
        btnfind.setOnClickListener(this::findProduct);
        btnDelete.setOnClickListener(this::deleteProduct);
    }

    private void deleteProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteProduct(productName.getText().toString());
        if (result)
        {
            id.setText("Record Deleted");
            productName.setText("");
            description.setText("");
            price.setText("");
        }
        else
            id.setText("No Match Found");
    }

    private void findProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, "goods.db", null, 1);
        Product product = dbHandler.findProduct(productName.getText().toString());
        if (product != null) {
            id.setText(String.valueOf(product.get_id()));
            description.setText(String.valueOf(product.get_productDescription()));
            price.setText(String.valueOf(product.get_productPrice()));
        } else {
            id.setText("No Match Found");
        }
    }

    private void addProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, MyDBHandler.DATABASE_NAME, null, 1);
        double productPrice = Double.parseDouble(price.getText().toString());
        String productDescription = description.getText().toString();
        Product product = new Product(productName.getText().toString(), productDescription, productPrice);
        if (dbHandler.addProduct(product) == -1) {
            id.setText("add product failed");
            return;
        }
        productName.setText("");
        price.setText("");
        description.setText("");
        id.setText("add product success");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        bindingView();
        bindingAction();
    }
}
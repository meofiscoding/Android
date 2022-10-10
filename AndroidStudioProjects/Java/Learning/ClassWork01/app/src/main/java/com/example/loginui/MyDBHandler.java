package com.example.loginui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Product;

public class MyDBHandler extends SQLiteOpenHelper {

    private ContentResolver myCR;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "goods.db";
    public static final String TABLE_PRODUCTS = "product";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_PRODUCT_NAME = "ProductName";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "ProductDescription";
    public static final String COLUMN_PRICE = "ProductPrice";


    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "create table product\n" +
                "(\n" +
                "    Id                 INTEGER not null\n" +
                "        constraint product_pk\n" +
                "            primary key autoincrement,\n" +
                "    ProductName        TEXT,\n" +
                "    ProductDescription TEXT,\n" +
                "    ProductPrice       REAL\n" +
                ");";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            onCreate(db);
        }
    }


    public Long addProduct(Product product) {
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_PRODUCTNAME, product.getProductName());
//        values.put(COLUMN_QUANTITY, product.getQuantity());
//        SQLiteDatabase db = this.getWritableDatabase();
//        Long result = db.insert(TABLE_PRODUCTS, null, values);
//        db.close();
//        return result;

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.get_productName());
        values.put(COLUMN_PRICE, product.get_productPrice());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.get_productDescription());
        myCR.insert(MyContentProvider.CONTENT_URI, values);
        return Long.valueOf(0);
    }


    public Product findProduct(String productname) {
//        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
//                COLUMN_PRODUCTNAME + " = \"" + productname + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Product product = new Product();
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            product.setID(Integer.parseInt(cursor.getString(0)));
//            product.setProductName(cursor.getString(1));
//            product.setQuantity(Integer.parseInt(cursor.getString(2)));
//            cursor.close();
//        } else {
//            product = null;
//        }
//        db.close();
//        return product;

        String[] projection = {COLUMN_ID, COLUMN_PRODUCT_NAME, COLUMN_PRODUCT_DESCRIPTION, COLUMN_PRICE};
        String selection = "ProductName = \"" + productname + "\"";
        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI, projection, selection, null, null);
        Product product = new Product();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.set_id(Integer.parseInt(cursor.getString(0)));
            product.set_productName(cursor.getString(1));
            product.set_productDescription(cursor.getString(2));
            product.set_productPrice(Double.parseDouble(cursor.getString(3)));
            cursor.close();
        } else {
            product = null;
        }
        return product;
    }

    public boolean deleteProduct(String productname) {
//        boolean result = false;
//        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
//                COLUMN_PRODUCTNAME + " = \"" + productname + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Product product = new Product();
//        if (cursor.moveToFirst()) {
//            product.setID(Integer.parseInt(cursor.getString(0)));
//            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
//                    new String[]{String.valueOf(product.getID())});
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;

        boolean result = false;
        String selection = "productname = \"" + productname + "\"";
        int rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI, selection, null);
        if (rowsDeleted > 0) result = true;
        return result;
    }
}

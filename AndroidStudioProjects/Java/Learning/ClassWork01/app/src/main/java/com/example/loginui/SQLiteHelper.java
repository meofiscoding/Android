package com.example.loginui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "productDB";
    public static String TABLE_NAME = "product";
    public static String ID_COLUMN = "id";
    public static String NAME_COLUMN = "name";
    public static String QUANTITY_COLUMN = "quantity";

    String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+ "("
         + ID_COLUMN +"INTEGER PRIMARY KEY, " +
            NAME_COLUMN + "TEXT NOT NULL, " + QUANTITY_COLUMN +"INTEGER)";

    public static int VERSION = 1;
    public SQLiteHelper(@Nullable Context context,
                       @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + ID_COLUMN + " INTEGER PRIMARY KEY," +
                NAME_COLUMN
                + " TEXT," + QUANTITY_COLUMN + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

//        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String DROP_TABLE = "DROP TABLE if EXISTS product1";
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        }
    }
}

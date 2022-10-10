package com.example.loginui;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;


public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.loginui.MyContentProvider";
    private static final String PRODUCTS_TABLE = "product";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCTS_TABLE);
    private SQLiteDatabase db;
    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;
    private static HashMap<String, String> PRODUCTS_PROJECTION_MAP;
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS);
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#", PRODUCTS_ID);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        MyDBHandler dbHelper = new MyDBHandler(context, "goods.db", null, 1);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MyDBHandler.TABLE_PRODUCTS);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case PRODUCTS_ID:
                queryBuilder.appendWhere(MyDBHandler.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            case PRODUCTS:
                queryBuilder.setProjectionMap(PRODUCTS_PROJECTION_MAP);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
//        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case PRODUCTS:
                rowsDeleted = db.delete(MyDBHandler.TABLE_PRODUCTS, selection, selectionArgs);
                break;
            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                rowsDeleted = db.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            /**
             * Get all student records
             */
            case PRODUCTS:
                return "vnd.android.cursor.dir/vnd.example.backupdata";
            /**
             * Get a particular student
             */
            case PRODUCTS_ID:
                return "vnd.android.cursor.item/vnd.example.backupdata";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
//        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case PRODUCTS:
                id = db.insert(MyDBHandler.TABLE_PRODUCTS, null, values);
                /**
                 * If record is added successfully
                 */
                if (id > 0) {
                    Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                    return _uri;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return Uri.parse(PRODUCTS_TABLE + "/" + id);
    }

    private MyDBHandler myDB;

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = myDB.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case PRODUCTS:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, selection, selectionArgs);
                break;
            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS,values, MyDBHandler.COLUMN_ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }
}

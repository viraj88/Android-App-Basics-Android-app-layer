package com.example.notify;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class MyContentProvider extends ContentProvider {

    // creating object of database
    // to perform query
    private SQLiteDatabase db;

    // declaring name of the database
    static final String DATABASE_NAME = "BookDetailDB";

    // declaring table name of the database
    static final String TABLE_NAME = "Bookss";

    // declaring version of the database
    static final int DATABASE_VERSION = 1;

    static final String PROVIDER_NAME = "com.example.notify.provider";

    static final String URL = "content://" + PROVIDER_NAME + "/Bookss";

    // parsing the content URI
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String id = "id";
    static final String title = "title";

    static final String Author = "Author";

    static  final String year = "year";
    static final int uriCode = 1;
    static UriMatcher uriMatcher;
    private static HashMap<String, String> values;

    // sql query to create the table
    static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME
            + " (id TEXT NOT NULL, "
            + " title TEXT NOT NULL, "
            +" Author TEXT NOT NULL, "
            +" year TEXT NOT NULL)";
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    static {

        // to match the content URI
        // every time user access table under content provider
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // to access whole table
        uriMatcher.addURI(PROVIDER_NAME, "Bookss", uriCode);

        // to access a particular row
        // of the table
        //uriMatcher.addURI(PROVIDER_NAME, "users/*", uriCode);
    }

    public MyContentProvider() {
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "com.example.notify.provider/Books";
            default:
                //throw new IllegalArgumentException("Unsupported URI: " + uri);
                return "Not";
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null,
                null, sortOrder);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

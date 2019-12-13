package com.example.va;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.va.ShoppingContract.ShoppingEntry;

import androidx.annotation.Nullable;

public class ShoppingDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shopping_list.db";
    public static final int DATABASE_VERSION = 1;

    public ShoppingDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SHOPPINGLIST_TABLE = "CREATE TABLE " +
                ShoppingEntry.TABLE_NAME + " (" +
                ShoppingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ShoppingEntry.COLUMN_NAME + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_SHOPPINGLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ShoppingEntry.COLUMN_NAME);
        onCreate(db);
    }
}

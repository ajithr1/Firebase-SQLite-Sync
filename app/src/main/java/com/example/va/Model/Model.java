package com.example.va.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.va.Model.ShoppingDbHelper.*;


public class Model implements ModelInterface {

    private ShoppingDbHelper shoppingDbHelper;
    Cursor cursor;

    @Override
    public int insertToDb(String name, Context context) {
        shoppingDbHelper = new ShoppingDbHelper(context);
        SQLiteDatabase db = shoppingDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if (result == -1) {
            return cursor.getPosition();
        }else {
            return -1;
        }
    }

    @Override
    public Cursor getAllData() {
        //shoppingDbHelper = new ShoppingDbHelper(context);
        SQLiteDatabase db = shoppingDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);
        return cursor;
    }
}

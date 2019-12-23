package com.example.va.Model;

import android.content.Context;
import android.database.Cursor;

public interface ModelInterface {

    int insertToDb(String name, Context context);

    public Cursor getAllData();
}

package com.example.va.Presenter;

import android.content.Context;

import com.example.va.Model.*;
import com.example.va.View.*;

public class Presenter implements PresenterInterface {

    private ViewInterface view;
    private ModelInterface model;

    @Override
    public int insertDataInDb(String name, Context context) {
        model = new Model();
        return model.insertToDb(name, context);
    }
}

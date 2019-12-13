package com.example.va;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import static com.example.va.R.layout.activity_shopping;

public class ShoppingActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_shopping);

        button = findViewById(R.id.create);
        recyclerView = findViewById(R.id.rc);


        ShoppingDbHelper shoppingDbHelper = new ShoppingDbHelper(this);
        sqLiteDatabase = shoppingDbHelper.getWritableDatabase();
    }

    public void create(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddShopList addShopList = new AddShopList();
        fragmentManager.beginTransaction().replace(R.id.con, addShopList).commit();

        //recyclerView.setVisibility(View.INVISIBLE);
        //button.setVisibility(View.INVISIBLE);
    }
}

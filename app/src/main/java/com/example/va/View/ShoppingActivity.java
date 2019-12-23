package com.example.va.View;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.va.R;
import com.example.va.Model.ShoppingDbHelper;
import com.example.va.Presenter.*;

import java.util.ArrayList;

import static com.example.va.R.layout.activity_shopping;

public class ShoppingActivity extends AppCompatActivity
        implements AddShopList.OnFragmentInteractionListener, ViewInterface {

    private SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Button button;
    NameAdapter nameAdapter;
    ArrayList<RecyclerName> arrayList;
    FragmentManager fragmentManager;
    Fragment fragment;

    PresenterInterface presenter;

    public static final String TAG = "ajju";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_shopping);

        ShoppingDbHelper shoppingDbHelper = new ShoppingDbHelper(this);
        sqLiteDatabase = shoppingDbHelper.getWritableDatabase();

        button = findViewById(R.id.create);
        recyclerView = findViewById(R.id.rc);
        recyclerView.setHasFixedSize(true);

        arrayList = new ArrayList<>();

        //adapter = new NameAdapter(arrayList, getAllItems());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void create(View view) {
        fragmentManager = getSupportFragmentManager();
        AddShopList addShopList = new AddShopList();
        fragmentManager.beginTransaction().addToBackStack(null).add(R.id.con, addShopList).commit();
    }

    /*private Cursor getAllItems() {
        return sqLiteDatabase.query(
                ShoppingContract.ShoppingEntry.TABLE_NAME, null, null,
                null, null, null,
                null
        );
    }*/

    @Override
    public void onFragmentInteraction(String sendBackText) {
        sendDataToPresenter(sendBackText);
        onBackPressed();
    }

    @Override
    public void sendDataToPresenter(String name) {
        presenter = new Presenter();
        int b = presenter.insertDataInDb(name, getApplicationContext());
        if (b == -1) {
            Log.d(TAG, "sendDataToPresenter: insert failed");
        }else {
            Log.d(TAG, "sendDataToPresenter: inserted");
            arrayList.add(new RecyclerName(name));
            adapter = new NameAdapter(arrayList);
            adapter.notifyItemInserted(b);
        }
    }
}

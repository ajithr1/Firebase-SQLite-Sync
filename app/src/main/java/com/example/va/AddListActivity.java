package com.example.va;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddListActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.architecture.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.architecture.EXTRA_TITLE";

    private EditText editTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        editTextTitle = findViewById(R.id.enter_name);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel_black_24dp);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("EDIT NOTE");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        }else {
            setTitle("ADD NOTE");
        }
    }

    private void saveNote(){
        String title = editTextTitle.getText().toString();

        if (title.trim().isEmpty()){
            Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    public void save(View view) {
        saveNote();
    }
}

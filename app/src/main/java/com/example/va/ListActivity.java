package com.example.va;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ListViewModal listViewModal;
    public static final int ADD_REQ = 1;
    public static final int EDIT_REQ = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ListAdapter noteAdapter = new ListAdapter();
        recyclerView.setAdapter(noteAdapter);

        listViewModal = ViewModelProviders.of(this).get(ListViewModal.class);
        listViewModal.getAllNotes().observe(this, new Observer<List<Lift>>() {
            @Override
            public void onChanged(List<Lift> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                listViewModal.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ListActivity.this, "List deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        noteAdapter.setOnItemClickListner(new ListAdapter.OnItemCLickListner() {
            @Override
            public void onItemClick(Lift note) {
                Intent intent = new Intent(ListActivity.this, AddListActivity.class);

                intent.putExtra(AddListActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddListActivity.EXTRA_TITLE, note.getTitle());
                startActivityForResult(intent, EDIT_REQ);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQ && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddListActivity.EXTRA_TITLE);

            Lift note = new Lift(title);
            listViewModal.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        }else if (requestCode == EDIT_REQ && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddListActivity.EXTRA_ID, -1);

            if (id == -1){
                Toast.makeText(this, "List can't be executed", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddListActivity.EXTRA_TITLE);

            Lift note = new Lift(title);
            note.setId(id);
            listViewModal.update(note);
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void add(View view) {

        Intent intent = new Intent(ListActivity.this, AddListActivity.class);
        startActivityForResult(intent, ADD_REQ);
    }
}

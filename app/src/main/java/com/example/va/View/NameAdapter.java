package com.example.va.View;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.va.R;
import com.example.va.Model.*;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {

    private ArrayList<RecyclerName> list;
    private Cursor cursor;

    NameAdapter(ArrayList<RecyclerName> list){
        this.list = list;
        //this.cursor = cursor;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        NameViewHolder nameViewHolder = new NameViewHolder(v);
        return nameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {

        RecyclerName recyclerName = list.get(position);

        holder.textView.setText(recyclerName.getName());

        /*if (!cursor.moveToPosition(position)){
            return;
        }

        //String name = cursor.getString(cursor.getColumnIndex(ShoppingContract.ShoppingEntry.COLUMN_NAME));
        //long id = cursor.getLong(cursor.getColumnIndex(ShoppingContract.ShoppingEntry._ID));

        //RecyclerName recyclerName = list.get(position);
        //holder.textView.setText(name);
        //holder.itemView.setTag(id);*/
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    static class NameViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        NameViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name_recyler_item);
        }
    }

    void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}

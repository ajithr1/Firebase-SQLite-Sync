package com.example.va;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private List<Lift> list =new ArrayList<>();
    private OnItemCLickListner listner;

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        Lift lift = list.get(position);

        holder.textView.setText(lift.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setNotes(List<Lift> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public Lift getNoteAt(int position){
        return list.get(position);
    }

    class ListHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ListHolder(@NonNull final View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION){
                        listner.onItemClick(list.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemCLickListner {
        void onItemClick(Lift lift);
    }

    public void setOnItemClickListner(OnItemCLickListner listner){
        this.listner = listner;
    }
}

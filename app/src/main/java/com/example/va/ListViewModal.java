package com.example.va;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListViewModal extends AndroidViewModel {

    private ListRepository listRepository;
    private LiveData<List<Lift>> lists;

    public ListViewModal(@NonNull Application application) {
        super(application);

        listRepository = new ListRepository(application);
        lists = listRepository.getAllLifts();
    }

    public void insert(Lift note){
        listRepository.insert(note);
    }

    public void delete(Lift note){
        listRepository.delete(note);
    }

    public void update(Lift note){
        listRepository.update(note);
    }

    public void deleteAllNotes(){
        listRepository.deleteAllLifts();
    }

    public LiveData<List<Lift>> getAllNotes() {
        return lists;
    }
}

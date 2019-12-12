package com.example.va;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListDao {

    @Insert
    void insert(Lift lift);

    @Delete
    void delete(Lift lift);

    @Update
    void update(Lift lift);

    @Query("DELETE FROM list_table")
    void deleteAll();

    @Query("SELECT * FROM list_table")
    LiveData<List<Lift>> getAllLists();
}

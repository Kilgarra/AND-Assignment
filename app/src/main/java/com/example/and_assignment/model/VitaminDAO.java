package com.example.and_assignment.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VitaminDAO {
    @Insert
    void addVitamin(Vitamin vitamin);

    @Update
    void updateVitamin(Vitamin vitamin);

    @Query("DELETE FROM vitamin_table WHERE id=:id")
    void deleteVitamin(int id);

    @Query("SELECT * FROM vitamin_table")
    LiveData<List<Vitamin>> getAllVitamins();

    @Query("SELECT * FROM vitamin_table WHERE id=:id")
    LiveData<Vitamin> getVitaminById(int id);
}

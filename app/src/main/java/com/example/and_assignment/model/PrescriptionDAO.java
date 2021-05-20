package com.example.and_assignment.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PrescriptionDAO {
    @Insert
    void addPrescription(Prescription prescription);

    @Update
    void updatePrescription(Prescription prescription);

    @Query("DELETE FROM prescription_table WHERE id=:id")
    void deletePrescription(int id);

    @Query("SELECT * FROM prescription_table")
    LiveData<List<Prescription>> getAllPrescriptions();

    @Query("SELECT * FROM prescription_table WHERE id=:id")
    LiveData<Prescription> getPrescriptionById(int id);

    @Query("UPDATE prescription_table SET name=:name AND hour=:hour AND minute=:minute WHERE id=:id")
    void editPrescription(int id, String name, int hour, int minute);



}

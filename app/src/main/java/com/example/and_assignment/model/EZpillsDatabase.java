package com.example.and_assignment.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Prescription.class, Vitamin.class}, version = 2)
public abstract class EZpillsDatabase extends RoomDatabase {
    private static EZpillsDatabase instance;
    public abstract PrescriptionDAO prescriptionDAO();
    public abstract VitaminDAO vitaminDAO();

    public static synchronized EZpillsDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    EZpillsDatabase.class, "ezpills_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

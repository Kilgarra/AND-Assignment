package com.example.and_assignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
@Entity(tableName = "prescription_table")
public class Prescription {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int hour;
    private int minute;
    public Prescription() {
    }

    public Prescription(String name, int hour, int minute) {
        this.name = name;
        this.hour=hour;
        this.minute=minute;
    }
    public Prescription(Prescription prescription){
        this.name=prescription.name;
        this.hour=prescription.hour;
        this.minute= prescription.minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}

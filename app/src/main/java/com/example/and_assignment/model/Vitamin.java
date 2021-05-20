package com.example.and_assignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vitamin_table")
public class Vitamin {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int hour;
    private int minute;

    public Vitamin() {
    }

    public Vitamin(String name, int hour, int minute) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
    }
    public Vitamin(Vitamin vitamin){
        this.name=vitamin.name;
        this.hour=vitamin.hour;
        this.minute=vitamin.minute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

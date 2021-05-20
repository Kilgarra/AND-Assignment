package com.example.and_assignment.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsRepository {
    SharedPreferences preferences;
    private static SharedPrefsRepository instance;

    private SharedPrefsRepository(Context context){
        preferences=context.getSharedPreferences("myPreferences", MODE_PRIVATE);

    }
    public static SharedPrefsRepository getInstance(Context context){
        if (instance==null){
            instance=new SharedPrefsRepository(context);
        }
        return instance;
    }
    public String getPreference(String key, String defaultName){
        return preferences.getString(key,defaultName);
    }
    public void savePreferences(String key, String value){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key, value);
        editor.apply();
        return;
    }
}

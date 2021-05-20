package com.example.and_assignment.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_assignment.data.SharedPrefsRepository;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel extends AndroidViewModel {
    private MutableLiveData<List<String>> data;
    private SharedPrefsRepository repository;
    private Context context=getApplication().getBaseContext();

    public SettingsViewModel(Application application){
        super(application);
        repository=SharedPrefsRepository.getInstance(context);
    }
    public LiveData<List<String>> getData(){
        if (data==null){
            data=new MutableLiveData<>();
            loadPrefs();
        }
        return data;
    }
    private void loadPrefs(){
        List<String> cache=new ArrayList<>();
        cache.add(repository.getPreference("time format", "12 hour"));
        data.setValue(cache);
    }
    public void savePref(String key, String value){
        repository.savePreferences(key, value);
        loadPrefs();
    }
}

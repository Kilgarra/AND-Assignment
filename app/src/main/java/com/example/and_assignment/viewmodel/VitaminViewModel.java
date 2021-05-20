package com.example.and_assignment.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_assignment.data.VitaminRepository;
import com.example.and_assignment.model.Vitamin;

import java.util.List;

public class VitaminViewModel extends AndroidViewModel {
    private VitaminRepository vitaminRepository;

    public VitaminViewModel(Application app){
        super(app);
        vitaminRepository=VitaminRepository.getInstance(app);
    }
    public LiveData<List<Vitamin>> getAllVitamins(){
        return vitaminRepository.getAllVitamins();
    }
    public void addVitamin(Vitamin vitamin){
        vitaminRepository.addVitamin(vitamin);
    }
    public LiveData<Vitamin> getVitaminById(int id){
        return vitaminRepository.getVitaminById(id);
    }
    public void deleteVitamin(int id){
        vitaminRepository.deleteVitamin(id);
    }
    public void updateVitamin(Vitamin vitamin){
        vitaminRepository.updateVitamin(vitamin);
    }
}

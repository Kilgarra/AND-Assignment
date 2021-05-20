package com.example.and_assignment.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.and_assignment.model.EZpillsDatabase;
import com.example.and_assignment.model.Prescription;
import com.example.and_assignment.model.PrescriptionDAO;
import com.example.and_assignment.model.Vitamin;
import com.example.and_assignment.model.VitaminDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VitaminRepository {
    private static VitaminRepository instance;
    private VitaminDAO vitaminDAO;
    private ExecutorService executorService;

    private VitaminRepository(Application application){
        EZpillsDatabase database=EZpillsDatabase.getInstance(application);
        vitaminDAO=database.vitaminDAO();
        executorService= Executors.newFixedThreadPool(2);

    }

    public static synchronized  VitaminRepository getInstance(Application application){
        if(instance==null){
            instance=new VitaminRepository(application);
        }
        return instance;
    }
    public LiveData<List<Vitamin>> getAllVitamins() {
        return vitaminDAO.getAllVitamins();
    }
    public void addVitamin(Vitamin vitamin){
        executorService.execute(() ->vitaminDAO.addVitamin(vitamin));
    }
    public LiveData<Vitamin> getVitaminById(int id){
        return vitaminDAO.getVitaminById(id);
    }
    public void deleteVitamin(int id){
        executorService.execute(()-> vitaminDAO.deleteVitamin(id));
    }
    public void updateVitamin(Vitamin vitamin){
        executorService.execute(()-> vitaminDAO.updateVitamin(vitamin));
    }
}

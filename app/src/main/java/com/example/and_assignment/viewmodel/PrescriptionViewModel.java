package com.example.and_assignment.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_assignment.data.PrescriptionRepository;
import com.example.and_assignment.model.Prescription;

import java.util.List;

public class PrescriptionViewModel extends AndroidViewModel {
    private  PrescriptionRepository prescriptionRepository;




    public PrescriptionViewModel(Application app){
        super(app);
        prescriptionRepository = PrescriptionRepository.getInstance(app);
    }

    public LiveData<List<Prescription>> getAllPrescriptions(){
        return prescriptionRepository.getAllPrescriptions();
    }
    public void addPrescription(Prescription prescription){
        prescriptionRepository.addPrescription(prescription);
    }
    public LiveData<Prescription> getPrescriptionById(int id){
        return prescriptionRepository.getPrescriptionById(id);
    }
    public void deletePrescription(int id){
        prescriptionRepository.deletePrescription(id);
    }
    public void editPrescription(int id, String name, int hour, int minute){
        prescriptionRepository.editPrescription(id,name,hour,minute);
    }
    public void updatePrescription(Prescription prescription){
        prescriptionRepository.updatePrescription(prescription);
    }


}

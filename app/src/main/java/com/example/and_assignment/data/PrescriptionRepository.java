package com.example.and_assignment.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.and_assignment.model.EZpillsDatabase;
import com.example.and_assignment.model.Prescription;
import com.example.and_assignment.model.PrescriptionDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PrescriptionRepository {
    private static PrescriptionRepository instance;
    private PrescriptionDAO prescriptionDAO;
    private ExecutorService executorService;

    private PrescriptionRepository(Application application){
        EZpillsDatabase database=EZpillsDatabase.getInstance(application);
        prescriptionDAO=database.prescriptionDAO();
        executorService= Executors.newFixedThreadPool(2);

    }

    public static synchronized  PrescriptionRepository getInstance(Application application){
        if(instance==null){
            instance=new PrescriptionRepository(application);
        }
        return instance;
    }
    public LiveData<List<Prescription>> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }
    public void addPrescription(Prescription prescription){
        executorService.execute(() ->prescriptionDAO.addPrescription(prescription));
    }
    public LiveData<Prescription> getPrescriptionById(int id){
        return prescriptionDAO.getPrescriptionById(id);
    }
    public void deletePrescription(int id){
        executorService.execute(()-> prescriptionDAO.deletePrescription(id));
    }
    public void editPrescription(int id, String name, int hour, int minute){
        executorService.execute(()->prescriptionDAO.editPrescription(id,name,hour,minute));
    }
    public void updatePrescription(Prescription prescription){
        executorService.execute(()->prescriptionDAO.updatePrescription(prescription));
    }


//    public void init(String userId) {
//        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance("https://ezpills-6d58c-default-rtdb.europe-west1.firebasedatabase.app/");
//        databaseReference=firebaseDatabase.getReference().child("users").child(userId);
//        prescriptions = new PrescriptionLiveData(databaseReference.child("prescriptionMap"));
//    }

//    public void savePrescription(PrescriptionList prescriptions) {
//
//        databaseReference.setValue(new PrescriptionList(prescriptions.getPrescriptionMap()));
//    }
//
//    public PrescriptionLiveData getPrescriptions() {
////        String content=prescriptions.getValue().toString();
////        Log.i("content",content);
//        return prescriptions;
//    }

}

package com.example.and_assignment.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrescriptionList {
    private HashMap<String,Prescription> prescriptionMap;
//    private ArrayList<Prescription> prescriptionList;
    public PrescriptionList(){
        prescriptionMap=new HashMap<>();
//        prescriptionList=new ArrayList<>();
    }
    public PrescriptionList(HashMap<String, Prescription> map){
        this.prescriptionMap=map;
    }
//    public PrescriptionList(PrescriptionList prescriptionList){
//        this.prescriptionList=prescriptionList;
//    }

//    public ArrayList<Prescription> getPrescriptionList() {
//        return prescriptionList;
//    }
    public HashMap<String, Prescription> getPrescriptionMap(){
        return prescriptionMap;
    }
    public void setPrescriptionMap(HashMap<String, Prescription> map){
        this.prescriptionMap=map;
    }

//    public void setPrescriptionList(ArrayList<Prescription> prescriptionList) {
//        this.prescriptionList = prescriptionList;
//    }
//    public void addPrescription(Prescription prescription){
//        prescriptionList.add(prescription);
//
//    }
//    public void deletePrescription(Prescription prescription){
//        if (prescriptionList.contains(prescription)){
//            prescriptionList.remove(prescription);
//        }
//    }
}

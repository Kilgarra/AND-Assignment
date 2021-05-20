package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Prescription;
import com.example.and_assignment.viewmodel.PrescriptionViewModel;
import com.example.and_assignment.viewmodel.SettingsViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SelectedPrescriptionFragment extends Fragment implements View.OnClickListener {


    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    Button editPrescriptionButton;
    Button deletePrescriptionButton;
    private PrescriptionViewModel prescriptionViewModel;
    SettingsViewModel settingsViewModel;
    TextView nameTextView;
    TextView timeTextView;


    Prescription selectedPrescription;
    int id;
    ArrayList<Prescription> prescriptionArrayList;

    public SelectedPrescriptionFragment() {
    }


    public static SelectedPrescriptionFragment newInstance() {
        SelectedPrescriptionFragment fragment = new SelectedPrescriptionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_prescription, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mainActivity=getActivity().findViewById(R.id.Main_activity);
        bar=mainActivity.findViewById(R.id.bottomAppBar);
        button=mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        navController= Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group, false);
        editPrescriptionButton=mainActivity.findViewById(R.id.editSelectedPrescriptionButton);
        editPrescriptionButton.setOnClickListener(this);
        deletePrescriptionButton=mainActivity.findViewById(R.id.deleteSelectedPrescriptionButton);
        deletePrescriptionButton.setOnClickListener(this);

        settingsViewModel=new ViewModelProvider(this).get(SettingsViewModel.class);
        prescriptionViewModel = new ViewModelProvider(this).get(PrescriptionViewModel.class);
        nameTextView=mainActivity.findViewById(R.id.selected_prescription_name);
        timeTextView=mainActivity.findViewById(R.id.selected_prescription_time_textview);
        id=Integer.parseInt(this.getArguments().getString("prescriptionNumberKey"));
        selectedPrescription=new Prescription();
        prescriptionArrayList=new ArrayList<>();

        prescriptionViewModel.getAllPrescriptions().observe(getViewLifecycleOwner(), prescriptions -> {
            selectedPrescription=prescriptions.get(id);
            nameTextView.setText(selectedPrescription.getName());

            settingsViewModel.getData().observe(getViewLifecycleOwner(), prefs ->{
                String changeMinute;
                if (selectedPrescription.getMinute()<10){
                    changeMinute="0"+ selectedPrescription.getMinute();
                }else{
                    changeMinute=Integer.toString(selectedPrescription.getMinute());
                }
                if (prefs.get(0).equals("24 hour")){
                    timeTextView.setText(selectedPrescription.getHour()+":"+changeMinute);
                }else if (prefs.get(0).equals("12 hour")){
                    if (selectedPrescription.getHour()>12){
                        int newHour=selectedPrescription.getHour()-12;
                        timeTextView.setText(newHour+":"+changeMinute+" PM");
                    } else {
                        timeTextView.setText(selectedPrescription.getHour()+":"+changeMinute+" AM");
                    }

                }
            });


        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_button:
                getActivity().onBackPressed();
                if (navController.getCurrentBackStackEntry().getDestination().getId()==R.id.mainFragment){
                    bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    button.setImageResource(R.drawable.ic_round_add_24);
                    bar.getMenu().setGroupVisible(R.id.group, true);
                }
                break;
            case R.id.editSelectedPrescriptionButton:
                Bundle bundle=new Bundle();
                bundle.putString("idForEditView", Integer.toString(selectedPrescription.getId()));
                navController.navigate(R.id.action_selectedPrescriptionFragment_to_editFragment, bundle);
                break;
            case R.id.deleteSelectedPrescriptionButton:
                getActivity().onBackPressed();
                prescriptionViewModel.deletePrescription(selectedPrescription.getId());
                Toast.makeText(getActivity(), "Prescription deleted", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
package com.example.and_assignment.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Prescription;
import com.example.and_assignment.model.Vitamin;
import com.example.and_assignment.viewmodel.PrescriptionViewModel;
import com.example.and_assignment.viewmodel.SettingsViewModel;
import com.example.and_assignment.viewmodel.VitaminViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EditFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    View mainActivity;
    PrescriptionViewModel prescriptionViewModel;
    VitaminViewModel vitaminViewModel;
    SettingsViewModel settingsViewModel;

    BottomAppBar bar;
    FloatingActionButton button;
    EditText editText;
    TimePicker timePicker;
    Button editButton;
    int id;
    Prescription prescription;
    Vitamin vitamin;
    boolean isComingFromPrescription = false;
    int hour;
    int minute;


    public EditFragment() {
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = getActivity().findViewById(R.id.Main_activity);
        prescriptionViewModel = new ViewModelProvider(this).get(PrescriptionViewModel.class);
        vitaminViewModel = new ViewModelProvider(this).get(VitaminViewModel.class);
        navController = Navigation.findNavController(view);

        bar = mainActivity.findViewById(R.id.bottomAppBar);
        bar.getMenu().setGroupVisible(R.id.group, false);
        button = mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        editText = mainActivity.findViewById(R.id.edit_prescription_name_editText);
        timePicker = mainActivity.findViewById(R.id.edit_prescription_time_timePicker);
        editButton = mainActivity.findViewById(R.id.edit_button);
        editButton.setOnClickListener(this);


        //check if coming from selected prescription or vitamin
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.getData().observe(getViewLifecycleOwner(), prefs -> {
            if (prefs.get(0).equals("24 hour")) {
                timePicker.setIs24HourView(true);
            } else if (prefs.get(0).equals("12 hour")) {
                timePicker.setIs24HourView(false);
            }
        });
        if (navController.getPreviousBackStackEntry().getDestination().getId() == R.id.selectedPrescriptionFragment) {
            id = Integer.parseInt(this.getArguments().getString("idForEditView"));
            prescriptionViewModel.getPrescriptionById(id).observe(getViewLifecycleOwner(), prescription -> {
                editText.setText(prescription.getName());
                timePicker.setHour(prescription.getHour());
                timePicker.setMinute(prescription.getMinute());
                hour=prescription.getHour();
                minute=prescription.getMinute();
                this.prescription = prescription;
                isComingFromPrescription = true;

            });
        } else if (navController.getPreviousBackStackEntry().getDestination().getId() == R.id.selectedVitaminFragment) {
            id = Integer.parseInt(this.getArguments().getString("idForEditView"));
            vitaminViewModel.getVitaminById(id).observe(getViewLifecycleOwner(), vitamin -> {
                editText.setText(vitamin.getName());
                timePicker.setHour(vitamin.getHour());
                timePicker.setMinute(vitamin.getMinute());
                hour=vitamin.getHour();
                minute=vitamin.getMinute();
                this.vitamin = vitamin;
                isComingFromPrescription = false;
            });
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                getActivity().onBackPressed();
                if (navController.getCurrentBackStackEntry().getDestination().getId() == R.id.mainFragment) {
                    bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    button.setImageResource(R.drawable.ic_round_add_24);
                    bar.getMenu().setGroupVisible(R.id.group, true);
                }
                break;
            case R.id.edit_button:
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                boolean timeIsChanged=false;

                if (!editText.getText().toString().isEmpty() && editText.getText() != null) {
                    getActivity().onBackPressed();
                    if (isComingFromPrescription) {
                        Prescription updatePrescription = new Prescription();
                        updatePrescription.setId(prescription.getId());
                        updatePrescription.setName(editText.getText().toString());

                        if(hour!=timePicker.getHour() ||minute!=timePicker.getMinute()){
                            updatePrescription.setHour(timePicker.getHour());
                            updatePrescription.setMinute(timePicker.getMinute());
                            intent.putExtra(AlarmClock.EXTRA_HOUR, updatePrescription.getHour());
                            intent.putExtra(AlarmClock.EXTRA_MINUTES, updatePrescription.getMinute());
                            timeIsChanged=true;
                        }else{
                            updatePrescription.setHour(hour);
                            updatePrescription.setMinute(minute);
                            timeIsChanged=false;
                        }

                        prescriptionViewModel.updatePrescription(updatePrescription);
                        Toast.makeText(getActivity(), "Prescription edited", Toast.LENGTH_SHORT).show();


                    } else {
                        Vitamin updateVitamin = new Vitamin();
                        updateVitamin.setId(vitamin.getId());
                        updateVitamin.setName(editText.getText().toString());

                        if(hour!=timePicker.getHour() ||minute!=timePicker.getMinute()){
                            updateVitamin.setHour(timePicker.getHour());
                            updateVitamin.setMinute(timePicker.getMinute());
                            intent.putExtra(AlarmClock.EXTRA_HOUR, updateVitamin.getHour());
                            intent.putExtra(AlarmClock.EXTRA_MINUTES, updateVitamin.getMinute());
                            timeIsChanged=true;
                        }else{
                            updateVitamin.setHour(hour);
                            updateVitamin.setMinute(minute);
                            timeIsChanged=false;
                        }


                        vitaminViewModel.updateVitamin(updateVitamin);
                        Toast.makeText(getActivity(), "Vitamin edited", Toast.LENGTH_SHORT).show();

                    }
                    if(timeIsChanged){
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(getActivity(), "You need to type in a name", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }
}
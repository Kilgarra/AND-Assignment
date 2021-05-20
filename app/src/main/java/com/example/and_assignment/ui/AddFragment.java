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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    Button addButton;
    PrescriptionViewModel prescriptionViewModel;
    VitaminViewModel vitaminViewModel;
    EditText name;
    TimePicker time;
    Spinner spinner;
    String spinnerChoice;
    SettingsViewModel settingsViewModel;


    public AddFragment() {
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = getActivity().findViewById(R.id.Main_activity);
        bar = mainActivity.findViewById(R.id.bottomAppBar);
        button = mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        addButton = mainActivity.findViewById(R.id.add_prescription_button);
        addButton.setOnClickListener(this);
        name = mainActivity.findViewById(R.id.add_prescription_name_editText);
        time = mainActivity.findViewById(R.id.add_prescription_time_timePicker);

        spinner = mainActivity.findViewById(R.id.add_type_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainActivity.getContext(), R.array.types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //navController= Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group, false);

        prescriptionViewModel = new ViewModelProvider(this).get(PrescriptionViewModel.class);
        vitaminViewModel = new ViewModelProvider(this).get(VitaminViewModel.class);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.getData().observe(getViewLifecycleOwner(), prefs -> {
            if (prefs.get(0).equals("24 hour")) {
                time.setIs24HourView(true);
            } else if (prefs.get(0).equals("12 hour")) {
                time.setIs24HourView(false);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_button:
                getActivity().onBackPressed();
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                button.setImageResource(R.drawable.ic_round_add_24);
                bar.getMenu().setGroupVisible(R.id.group, true);
                break;
            case R.id.add_prescription_button:
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

                boolean hasType = false;
                if (!name.getText().toString().isEmpty() && name.getText().toString() != null) {


                    if (spinnerChoice.equals("Prescription") || spinnerChoice.equals("Orvosi recept") ) {
                        Prescription prescriptionToAdd = new Prescription(name.getText().toString(), time.getHour(), time.getMinute());
                        prescriptionViewModel.addPrescription(prescriptionToAdd);
                        intent.putExtra(AlarmClock.EXTRA_HOUR, prescriptionToAdd.getHour());
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, prescriptionToAdd.getMinute());
                        Log.i("information", "Prescription added");
                        hasType = true;
                    } else if (spinnerChoice.equals("Vitamin")) {
                        Vitamin vitaminToAdd = new Vitamin(name.getText().toString(), time.getHour(), time.getMinute());
                        vitaminViewModel.addVitamin(vitaminToAdd);

                        intent.putExtra(AlarmClock.EXTRA_HOUR, vitaminToAdd.getHour());
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, vitaminToAdd.getMinute());
                        Log.i("information", "Vitamin added");
                        hasType = true;
                    } else {
                        Toast.makeText(getActivity(), "You need to select a type", Toast.LENGTH_SHORT).show();
                        hasType = false;
                    }

                } else {
                    Toast.makeText(getActivity(), "You need to type in a name", Toast.LENGTH_SHORT).show();
                }
                if (hasType) {
                    name.setText("");
                    spinner.setSelection(0);
                    startActivity(intent);
                }

                break;


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerChoice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
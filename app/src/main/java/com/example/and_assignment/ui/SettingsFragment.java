package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.and_assignment.R;
import com.example.and_assignment.viewmodel.SettingsViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SettingsFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    SettingsViewModel settingsViewModel;
    Switch aSwitch;

    public SettingsFragment() {
    }


    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_settings, container, false);
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

        aSwitch=mainActivity.findViewById(R.id.settings_switch);
        settingsViewModel=new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsViewModel.getData().observe(getViewLifecycleOwner(), prefs ->{
            if (aSwitch!= null){
                aSwitch.setText(prefs.get(0));
                if (prefs.get(0).equals("12 hour")){
                    aSwitch.setChecked(false);
                }else if(prefs.get(0).equals("24 hour")){
                    aSwitch.setChecked(true);
                }
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    aSwitch.setText(R.string.time_format_switch_24);

                }else{
                    aSwitch.setText(R.string.time_format_switch_12);
                }
                settingsViewModel.savePref("time format", aSwitch.getText().toString());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_button:
                getActivity().onBackPressed();
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                button.setImageResource(R.drawable.ic_round_add_24);
                bar.getMenu().setGroupVisible(R.id.group, true);

        }
    }
}
package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Vitamin;
import com.example.and_assignment.viewmodel.SettingsViewModel;
import com.example.and_assignment.viewmodel.VitaminViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SelectedVitaminFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    Button editVitaminButton;
    Button deleteVitaminButton;
    private VitaminViewModel vitaminViewModel;
    TextView nameTextView;
    TextView timeTextView;
    SettingsViewModel settingsViewModel;

    Vitamin selectedVitamin;
    int id;
    ArrayList<Vitamin> vitaminArrayList;


    public SelectedVitaminFragment() {
    }

    public static SelectedVitaminFragment newInstance() {
        SelectedVitaminFragment fragment = new SelectedVitaminFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_vitamin, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mainActivity=getActivity().findViewById(R.id.Main_activity);
        bar=mainActivity.findViewById(R.id.bottomAppBar);
        button=mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        navController= Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group, false);
        editVitaminButton=mainActivity.findViewById(R.id.editSelectedVitaminButton);
        editVitaminButton.setOnClickListener(this);
        deleteVitaminButton=mainActivity.findViewById(R.id.deleteSelectedVitaminButton);
        deleteVitaminButton.setOnClickListener(this);

        vitaminViewModel=new ViewModelProvider(this).get(VitaminViewModel.class);
        settingsViewModel=new ViewModelProvider(this).get(SettingsViewModel.class);
        nameTextView=mainActivity.findViewById(R.id.selected_vitamin_name);
        timeTextView=mainActivity.findViewById(R.id.selected_vitamin_time_textview);
        id=Integer.parseInt(this.getArguments().getString("vitaminNumberKey"));
        selectedVitamin=new Vitamin();
        vitaminArrayList=new ArrayList<>();

        vitaminViewModel.getAllVitamins().observe(getViewLifecycleOwner(), vitamins -> {
            selectedVitamin=vitamins.get(id);
            nameTextView.setText(selectedVitamin.getName());
            settingsViewModel.getData().observe(getViewLifecycleOwner(), prefs ->{
                String changeMinute;
                if (selectedVitamin.getMinute()<10){
                    changeMinute="0"+ selectedVitamin.getMinute();
                }else{
                    changeMinute=Integer.toString(selectedVitamin.getMinute());
                }
                if (prefs.get(0).equals("24 hour")){
                    timeTextView.setText(selectedVitamin.getHour()+":"+changeMinute);
                }else if (prefs.get(0).equals("12 hour")){
                    if (selectedVitamin.getHour()>12){
                        int newHour=selectedVitamin.getHour()-12;
                        timeTextView.setText(newHour+":"+changeMinute+" PM");
                    } else {
                        timeTextView.setText(selectedVitamin.getHour()+":"+changeMinute+" AM");
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
            case R.id.editSelectedVitaminButton:
                Bundle bundle=new Bundle();
                bundle.putString("idForEditView", Integer.toString(selectedVitamin.getId()));
                navController.navigate(R.id.action_selectedVitaminFragment_to_editFragment, bundle);
                break;
            case R.id.deleteSelectedVitaminButton:
                getActivity().onBackPressed();
                vitaminViewModel.deleteVitamin(selectedVitamin.getId());
                Toast.makeText(getActivity(), "Vitamin deleted", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
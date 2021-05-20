package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.and_assignment.R;
import com.example.and_assignment.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class MainFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    Button goToPrescriptions;
    Button goToVitamins;

    private MainActivityViewModel mainActivityViewModel;



    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        navController= Navigation.findNavController(view);
        mainActivity=getActivity().findViewById(R.id.Main_activity);
        bar=mainActivity.findViewById(R.id.bottomAppBar);
        button=mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        goToPrescriptions=mainActivity.findViewById(R.id.goToPrescriptions);
        goToPrescriptions.setOnClickListener(this);
        goToVitamins=mainActivity.findViewById(R.id.goToVitamins);
        goToVitamins.setOnClickListener(this);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        checkIfSignedIn();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private void checkIfSignedIn() {
        mainActivityViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
            } else
                startLoginActivity();
        });
    }
    private void startLoginActivity() {
        navController.navigate(R.id.action_global_sign_in);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_button:
                navController.navigate(R.id.action_mainFragment_to_addFragment);
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                button.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                break;
            case R.id.goToPrescriptions:
                navController.navigate(R.id.action_mainFragment_to_prescriptionsFragment);
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                button.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                break;
            case R.id.goToVitamins:
                navController.navigate(R.id.action_mainFragment_to_vitaminsFragment);
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                button.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                break;


        }
    }
}
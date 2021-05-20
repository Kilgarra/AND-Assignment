package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.and_assignment.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AboutFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;


    public AboutFragment() {
    }


    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
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
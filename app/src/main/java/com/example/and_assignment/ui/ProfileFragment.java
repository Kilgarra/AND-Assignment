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
import com.example.and_assignment.viewmodel.MainActivityViewModel;
import com.example.and_assignment.viewmodel.PrescriptionViewModel;
import com.example.and_assignment.viewmodel.VitaminViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    private MainActivityViewModel viewModel;
    PrescriptionViewModel prescriptionViewModel;
    VitaminViewModel vitaminViewModel;

    TextView emailTextView;
    TextView nameTextView;
    TextView numberOfPrescriptionsTV;
    TextView numberOfVitaminsTV;



    public ProfileFragment() {
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mainActivity=getActivity().findViewById(R.id.Main_activity);
        bar=mainActivity.findViewById(R.id.bottomAppBar);
        button=mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);

        //find nav controller and set menu on appbar invisible
        navController= Navigation.findNavController(view);
        bar.getMenu().setGroupVisible(R.id.group, false);
        //find sign out button and set listener
        Button signOutButton=mainActivity.findViewById(R.id.button2);
        signOutButton.setOnClickListener(this);
        //instantiating view model and check if signed in
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        prescriptionViewModel=new ViewModelProvider(this).get(PrescriptionViewModel.class);
        vitaminViewModel=new ViewModelProvider(this).get(VitaminViewModel.class);
        nameTextView=mainActivity.findViewById(R.id.Profile_name);
        emailTextView=mainActivity.findViewById(R.id.profile_email_textview);
        numberOfPrescriptionsTV=mainActivity.findViewById(R.id.profile_prescriptions_text);
        numberOfVitaminsTV=mainActivity.findViewById(R.id.profile_vitamins_text);
        checkIfSignedIn();
        prescriptionViewModel.getAllPrescriptions().observe(getViewLifecycleOwner(), prescriptions -> {
            numberOfPrescriptionsTV.setText(Integer.toString(prescriptions.size()));
        });
        vitaminViewModel.getAllVitamins().observe(getViewLifecycleOwner(), vitamins -> {
            numberOfVitaminsTV.setText(Integer.toString(vitamins.size()));
        });

    }
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                nameTextView.setText(user.getDisplayName());
                emailTextView.setText(user.getEmail());

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
                getActivity().onBackPressed();
                if (navController.getCurrentBackStackEntry().getDestination().getId()==R.id.mainFragment){
                    bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                    button.setImageResource(R.drawable.ic_round_add_24);
                    bar.getMenu().setGroupVisible(R.id.group, true);
                }
                break;
            case R.id.button2:
                signOut(v);
                break;
        }
    }
    public void signOut(View v) {
        viewModel.signOut();
    }

}
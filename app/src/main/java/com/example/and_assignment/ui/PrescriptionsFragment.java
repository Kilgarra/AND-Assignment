package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Prescription;
import com.example.and_assignment.ui.adapters.PrescriptionsAdapter;
import com.example.and_assignment.viewmodel.PrescriptionViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class PrescriptionsFragment extends Fragment implements View.OnClickListener, PrescriptionsAdapter.OnListItemClickListener  {


    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton btn;
    private PrescriptionViewModel prescriptionViewModel;
    RecyclerView prescriptionRecyclerView;
    PrescriptionsAdapter prescriptionsAdapter;
    private ArrayList<Prescription> prescriptionArrayList;

    public PrescriptionsFragment() {
    }


    public static PrescriptionsFragment newInstance() {
        PrescriptionsFragment fragment = new PrescriptionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_prescriptions, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        mainActivity = getActivity().findViewById(R.id.Main_activity);
        bar = mainActivity.findViewById(R.id.bottomAppBar);
        btn = mainActivity.findViewById(R.id.floating_button);
        btn.setOnClickListener(this);
        bar.getMenu().setGroupVisible(R.id.group, false);


        prescriptionArrayList=new ArrayList<>();
        prescriptionsAdapter = new PrescriptionsAdapter(prescriptionArrayList, this);
        prescriptionViewModel = new ViewModelProvider(this).get(PrescriptionViewModel.class);
        prescriptionViewModel.getAllPrescriptions().observe(getViewLifecycleOwner(), prescriptions -> {
            prescriptionsAdapter.updateData(prescriptions);
        });


        prescriptionRecyclerView = mainActivity.findViewById(R.id.fragment_prescriptions_recycler_view);
        prescriptionRecyclerView.hasFixedSize();
        prescriptionRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity.getContext()));
        prescriptionRecyclerView.setAdapter(prescriptionsAdapter);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
//

            case R.id.floating_button:
                getActivity().onBackPressed();
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                btn.setImageResource(R.drawable.ic_round_add_24);
                bar.getMenu().setGroupVisible(R.id.group, true);
                break;

        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        int prescriptionNumber = clickedItemIndex;
        Bundle bundle=new Bundle();
        bundle.putString("prescriptionNumberKey", Integer.toString(prescriptionNumber));
        navController.navigate(R.id.action_prescriptionsFragment_to_selectedPrescriptionFragment, bundle);
    }
}
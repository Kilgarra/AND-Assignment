package com.example.and_assignment.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Vitamin;
import com.example.and_assignment.ui.adapters.VitaminAdapter;
import com.example.and_assignment.viewmodel.VitaminViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VitaminsFragment extends Fragment implements View.OnClickListener, VitaminAdapter.OnListItemClickListener {

    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    private VitaminViewModel vitaminViewModel;
    RecyclerView vitaminRecyclerView;
    VitaminAdapter vitaminAdapter;
    private ArrayList<Vitamin> vitaminArrayList;

    public VitaminsFragment() {
    }


    public static VitaminsFragment newInstance() {
        VitaminsFragment fragment = new VitaminsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vitamins, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        navController=Navigation.findNavController(view);
        mainActivity=getActivity().findViewById(R.id.Main_activity);
        bar=mainActivity.findViewById(R.id.bottomAppBar);
        button=mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        bar.getMenu().setGroupVisible(R.id.group, false);

        vitaminArrayList=new ArrayList<>();
        vitaminAdapter=new VitaminAdapter(vitaminArrayList, this);
        vitaminViewModel=new ViewModelProvider(this).get(VitaminViewModel.class);
        vitaminViewModel.getAllVitamins().observe(getViewLifecycleOwner(), vitamins -> {
            vitaminAdapter.updateData(vitamins);
        });

        vitaminRecyclerView=mainActivity.findViewById(R.id.fragment_vitamins_recycler_view);
        vitaminRecyclerView.hasFixedSize();
        vitaminRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity.getContext()));
        vitaminRecyclerView.setAdapter(vitaminAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_button:
                getActivity().onBackPressed();
                bar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                button.setImageResource(R.drawable.ic_round_add_24);
                bar.getMenu().setGroupVisible(R.id.group, true);
                break;

        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        int vitaminNumber=clickedItemIndex;
        Bundle bundle=new Bundle();
        bundle.putString("vitaminNumberKey", Integer.toString(vitaminNumber));
        navController.navigate(R.id.action_vitaminsFragment_to_selectedVitaminFragment, bundle);

    }
}
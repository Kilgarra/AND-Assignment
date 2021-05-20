package com.example.and_assignment.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.and_assignment.R;
import com.example.and_assignment.viewmodel.MainActivityViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floating_btn;
    BottomAppBar appBar;
    NavController navController;
    MainActivityViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floating_btn=findViewById(R.id.floating_button);

        appBar=findViewById(R.id.bottomAppBar);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);

        setUpBackButtonForMenuItems();
        setUpBackButtonForProfile();
        viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getCurrentUser().observe(this, user ->{
            if (user!=null){
                Toast.makeText(this, "Welcome back "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void setUpBackButtonForProfile() {
        appBar.setNavigationOnClickListener((navigation) -> {
            switch(navController.getCurrentDestination().getId()){
                case R.id.mainFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_mainFragment_to_profileFragment);
                    break;
                case R.id.aboutFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_aboutFragment_to_profileFragment);
                    break;
                case R.id.settingsFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_settingsFragment_to_profileFragment);
                    break;
                case R.id.addFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_addFragment_to_profileFragment);
                    break;
                case R.id.feedbackFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_feedbackFragment_to_profileFragment);
                    break;
                case R.id.contactFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_contactFragment_to_profileFragment);
                    break;
                case R.id.prescriptionsFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_prescriptionsFragment_to_profileFragment);
                    break;
                case R.id.vitaminsFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_vitaminsFragment_to_profileFragment);
                    break;
                case R.id.selectedPrescriptionFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_selectedPrescriptionFragment_to_profileFragment);
                    break;
                case R.id.selectedVitaminFragment:
                    appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                    floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                    navController.navigate(R.id.action_selectedVitaminFragment_to_profileFragment);
                    break;

            }
        });
    }

    public void setUpBackButtonForMenuItems(){
        appBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.settings_menu_item:
                    if (navController.getCurrentDestination().getId()==R.id.settingsFragment){
                        return false;
                    }else {
                        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                        navController.navigate(R.id.action_mainFragment_to_settingsFragment);
                    }
                    return true;
                case R.id.about_menu_item:
                    if (navController.getCurrentDestination().getId()==R.id.aboutFragment){
                        return false;
                    }else {
                        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                        navController.navigate(R.id.action_mainFragment_to_aboutFragment);
                    }
                    return true;
                case R.id.contact_menu_item:
                    if (navController.getCurrentDestination().getId()==R.id.contactFragment){
                        return false;
                    }else {
                        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                        navController.navigate(R.id.action_mainFragment_to_contactFragment);
                    }
                    return true;
                case R.id.feedback_menu_item:
                    if (navController.getCurrentDestination().getId()==R.id.feedbackFragment){
                        return false;
                    }else {
                        appBar.setFabAlignmentMode(appBar.FAB_ALIGNMENT_MODE_END);
                        floating_btn.setImageResource(R.drawable.ic_baseline_arrow_back_24);
                        navController.navigate(R.id.action_mainFragment_to_feedbackFragment);
                    }
                    return true;
                default:
                    return false;
            }
        });
    }
}
package com.example.and_assignment.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.and_assignment.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.cketti.mailto.EmailIntentBuilder;

public class FeedbackFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    View mainActivity;
    BottomAppBar bar;
    FloatingActionButton button;
    Button submitButton;
    EditText feedbackET;


    public FeedbackFragment() {

    }


    public static FeedbackFragment newInstance() {
        FeedbackFragment fragment = new FeedbackFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mainActivity=getActivity().findViewById(R.id.Main_activity);
        bar=mainActivity.findViewById(R.id.bottomAppBar);
        button=mainActivity.findViewById(R.id.floating_button);
        button.setOnClickListener(this);
        submitButton=mainActivity.findViewById(R.id.feedback_submit);
        submitButton.setOnClickListener(this);
        feedbackET=mainActivity.findViewById(R.id.feedback_editText);


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
                break;
            case R.id.feedback_submit:
                Intent intent= EmailIntentBuilder.from(getActivity())
                        .to("ezpills@contact.com")
                        .subject("EZpills feedback")
                        .body(feedbackET.getText().toString())
                        .build();
                startActivity(Intent.createChooser(intent,"Send Email Using: "));
                feedbackET.setText("");
                break;

        }
    }
}
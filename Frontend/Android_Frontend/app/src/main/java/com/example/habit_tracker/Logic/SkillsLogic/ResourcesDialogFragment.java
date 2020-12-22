package com.example.habit_tracker.Logic.SkillsLogic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.habit_tracker.R;
import com.example.habit_tracker.SkillActivity;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

public class ResourcesDialogFragment extends DialogFragment {

    EditText nameText;
    EditText linkText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_resources, null);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SkillActivity.learningResourcesList.add(
                                new SkillsModel.LearningResources(
                                        nameText.getText().toString(),
                                        linkText.getText().toString()));
                        LearningResourcesAdapter.values.add( nameText.getText().toString());
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        ((SkillActivity) activity).refreshLearningResources();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                }).setView(view);
        // Create the AlertDialog object and return it
        nameText = (EditText)view.findViewById(R.id.resource_name);
        linkText = (EditText)view.findViewById(R.id.resource_link);
        return builder.create();
    }


}

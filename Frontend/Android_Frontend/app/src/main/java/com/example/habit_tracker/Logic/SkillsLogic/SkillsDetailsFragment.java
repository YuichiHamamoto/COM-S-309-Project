package com.example.habit_tracker.Logic.SkillsLogic;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_tracker.Logic.HabitLogic.Habits;
import com.example.habit_tracker.Logic.Project;
import com.example.habit_tracker.R;
import com.example.habit_tracker.SkillActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SkillsDetailsFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    public RecyclerView recyclerView;
    private RecyclerView.Adapter habitAdapter;
    private RecyclerView.Adapter projectAdapter;
    private RecyclerView.Adapter resourceAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextInputEditText tagText;
    TextInputEditText groupText;
    Button deleteButton;
    Button saveButton;
    Button cancelButton;
    Button editButton;
    public List<SkillsModel.LearningResources> res = new ArrayList<>();
    boolean isPopulated = false;

    Bundle initState;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        initState = this.getArguments();
        if(initState != null)
        {
            isPopulated = true;
        }
        return inflater.inflate(R.layout.skills_details_fragment, parent, false);
    }
    public String getTagText() {
        Log.d("TagTEXT", tagText.getText().toString());
        return tagText.getText().toString();
    }
    public String getGroupText() {
        Log.d("groupText", tagText.getText().toString());
        return groupText.getText().toString();
    }

    public void setTagText(String tag) {
        Log.d("SET", tag);
        tagText.setText(tag);
    }
    public void setGroupText(String group) {
        groupText.setText(group);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tagText = (TextInputEditText)view.findViewById(R.id.tag_input_field);
        groupText = (TextInputEditText)view.findViewById(R.id.group_input_field);
        deleteButton = (Button)view.findViewById(R.id.delete_button);
        saveButton = (Button)view.findViewById(R.id.save_button);
        cancelButton = (Button)view.findViewById(R.id.cancel_button);
        //editButton = (Button)view.findViewById(R.id.edit_button);


        if(isPopulated)
        {
            setTagText(initState.getString("tag"));
            setGroupText(initState.getString("group"));
            deleteButton.setVisibility(View.VISIBLE);
            SkillActivity.isEdit = true;
        }
        setUpResourcesRecycler(view);
        setUpHabitsRecycler(view);
        setUpProjectsRecycler(view);
    }

    /**
     * Update Recycler View for Learning Resources after one is added
     * @return Void
     */
    public Void updateResources()
    {
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
        return null;
    }

    /**
     *
     * Set up Recycler View component to display Learning Resources associated with this skill tag
     * @param view
     */
    public void setUpResourcesRecycler(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.resources_recycle);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();

        for(SkillsModel item: SkillActivity.skillList)
        {
            if(item.getSkillTag().equals(getTagText()))
            {
                if(item.getResources() != null){
                    res = Arrays.asList(item.getResources());
                }
            }
        }
        for(SkillsModel.LearningResources item: res)
        {
           input.add(item.name);
        }
        resourceAdapter = new LearningResourcesAdapter(input);
        recyclerView.setAdapter(resourceAdapter);
    }

    /**
     *
     * Set up Recycler View component to display Habits associated with this skill tag
     * @param view
     */
    public void setUpHabitsRecycler(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.habits_recycler);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();

        for(Habits item: SkillActivity.habitList)
        {
            if(item.getTag().equals(getTagText()))
            {
                input.add(item.getName());
            }
        }

        habitAdapter = new HabitsRecyclerAdapter(input);
        recyclerView.setAdapter(habitAdapter);
    }

    /**
     *
     * Set up Recycler View component to display Habits associated with this skill tag
     * @param view
     */
    public void setUpProjectsRecycler(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.projects_recycler);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();

        for(Project item: SkillActivity.projectList)
        {
            for(String skillTag: item.getSkills())
            {
                if(skillTag.equals(getTagText()))
                {
                    input.add(item.getName());
                }
            }
        }

        projectAdapter = new ProjectsRecyclerAdapter(input);
        recyclerView.setAdapter(projectAdapter);
    }

}

package com.example.habit_tracker.Logic.SkillsLogic;

import android.os.Bundle;
import android.view.*;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_tracker.R;
import com.example.habit_tracker.SkillActivity;

import java.util.ArrayList;
import java.util.List;

public class SkillsListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.skills_list_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpSkillsRecycler(view);
    }

    /**
     * Set up Recycler View to display list of Skills for this User
     * @param view
     */
    public void setUpSkillsRecycler(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.skillListRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();

        for(SkillsModel item: SkillActivity.skillList)
        {
            input.add(item.getSkillTag());
        }
        mAdapter = new SkillListRecyclerAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }
}

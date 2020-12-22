package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.habit_tracker.Logic.Project;
import com.example.habit_tracker.Logic.SkillsLogic.*;
import com.example.habit_tracker.Logic.HabitLogic.*;

import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillActivity extends AppCompatActivity implements IView, FragmentCommListener {

    Gson gson = new Gson();

    public static ArrayList<SkillsModel> skillList;
    public static ArrayList<Habits> habitList;
    public static ArrayList<Project> projectList;
    public static ArrayList<SkillsModel.LearningResources> learningResourcesList = new ArrayList<>();

    UserFile currFile;
    String getResponse = "";
    boolean init = true;
    public static boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        getSupportActionBar().setTitle("Skill");

        getReq();

    }

    public void refreshLearningResources()
    {
        SkillsDetailsFragment frag = (
                SkillsDetailsFragment)getSupportFragmentManager().findFragmentByTag("DetailsFrag");
        frag.updateResources();
    }
    public void addSkill(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new SkillsDetailsFragment(), "DetailsFrag");
        ft.addToBackStack(null);
        ft.commit();
    }

    public void openSkill(View view, String name) {
        Bundle args = new Bundle();
        int index = -1;
        for(int i = 0; i < skillList.size(); i++)
        {
            if(skillList.get(i).getSkillTag().equals(name))
            {
                index = i;
            }
        }
        if(index == -1)
        {
            return;
        }
        args.putString("tag", skillList.get(index).getSkillTag());
        args.putString("group", skillList.get(index).getGroupTag());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SkillsDetailsFragment frag = new SkillsDetailsFragment();
        frag.setArguments(args);
        ft.replace(R.id.placeholder, frag, "DetailsFrag");
        ft.addToBackStack("DetailsFrag");
        ft.commit();
    }

    public void cancelDetails(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new SkillsListFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void saveDetails(View view) {
        this.postReq();
        getReq();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new SkillsListFragment());
        ft.addToBackStack(null);
        ft.commit();
        isEdit = false;
    }

    public void deleteSkill(View view) {
        this.postReqDel();
        getReq();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new SkillsListFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void addLearningResource(View view) {
        DialogFragment frag = new ResourcesDialogFragment();
        frag.show(getSupportFragmentManager(), "resources");
    }

    public void goToUrl (View view, String name) {
        SkillsDetailsFragment frag = (
                SkillsDetailsFragment)getSupportFragmentManager().findFragmentByTag("DetailsFrag");
        Uri uriUrl = null;
        for(SkillsModel.LearningResources item: frag.res)
        {
            if(item.name.equals(name)){
                uriUrl = Uri.parse(item.link);
            }
        }
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    public void getReq(){
        ServerRequest serverRequest = new ServerRequest();
        SkillsLogic logic = new SkillsLogic(this,serverRequest);
        logic.updateSkills("GET",null);
    }

    private void postReq(){
        SkillsDetailsFragment frag = (
                SkillsDetailsFragment)getSupportFragmentManager().findFragmentByTag("DetailsFrag");

        SkillsModel skillSend = new SkillsModel();
        skillSend.setSkillTag(frag.getTagText());
        skillSend.setGroupTag(frag.getGroupText());



        List<SkillsModel.LearningResources> sendResources = new ArrayList<>();
        for(SkillsModel item: SkillActivity.skillList)
        {
            if(item.getSkillTag().equals(frag.getTagText()))
            {
                if(item.getResources() != null){
                    sendResources = Arrays.asList(item.getResources());
                }
            }
        }
        learningResourcesList.addAll(sendResources);

        skillSend.setResources(learningResourcesList.toArray(new SkillsModel.LearningResources[0]));

        if(isEdit)
        {
            for(int i = 0; i < skillList.size(); i++)
            {
                if(skillList.get(i).getSkillTag().equals(frag.getTagText()))
                {
                   skillList.remove(i);
                }
            }
        }

        //skillList = new ArrayList<>(); //Resetting Skills list
        skillList.add(skillSend);
        final String skillJsonSend = gson.toJson(skillList);
        currFile.setSkills(skillJsonSend);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(currFile.toJSON());
        }catch (JSONException err){
            Log.d("Error in json post", err.toString());
        }

        ServerRequest serverRequest = new ServerRequest();
        SkillsLogic SkillsLogic = new SkillsLogic(this,serverRequest);
        SkillsLogic.updateSkills("POST",jsonObject);

    }
    private void postReqDel(){
        SkillsDetailsFragment frag = (
                SkillsDetailsFragment)getSupportFragmentManager().findFragmentByTag("DetailsFrag");

        for(int i = 0; i < skillList.size(); i++)
        {
            if(skillList.get(i).getSkillTag().equals(frag.getTagText()))
            {
                skillList.remove(i);
            }
        }
        final String skillJsonSend = gson.toJson(skillList);
        currFile.setSkills(skillJsonSend);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(currFile.toJSON());
        }catch (JSONException err){
            Log.d("Error in json post", err.toString());
        }

        ServerRequest serverRequest = new ServerRequest();
        SkillsLogic SkillsLogic = new SkillsLogic(this,serverRequest);
        SkillsLogic.updateSkills("POST",jsonObject);

    }

    public void toToDo(View view){
        Intent in = new Intent(SkillActivity.this,homeActivity.class);
        startActivity(in);
    }
    public void toHabit(View view){
        Intent in = new Intent(SkillActivity.this,HabitActivity.class);
        startActivity(in);
    }
    public void toStat(View view){
        Intent in = new Intent(SkillActivity.this,StatisticActivity.class);
        startActivity(in);
    }
    public void toProject(View view){
        Intent in = new Intent(SkillActivity.this,ProjectActivity.class);
        startActivity(in);
    }
    public void toSetting(View view){
        Intent in = new Intent(SkillActivity.this,SettingActivity.class);
        startActivity(in);
    }

    @Override
    public void showText(int n,String s) {
        getResponse = s;
        currFile =  gson.fromJson(getResponse,UserFile.class);

        Type skillArrayType = new TypeToken<ArrayList<SkillsModel>>(){}.getType();
        skillList = gson.fromJson(currFile.getSkills(), skillArrayType);
        Log.d("CURRFILE", skillList.toString());

        Type habitArrayType = new TypeToken<ArrayList<Habits>>(){}.getType();
        habitList = gson.fromJson(currFile.getHabits(), habitArrayType);
        Log.d("CURRFILE", habitList.toString());

        Type projectArrayType = new TypeToken<ArrayList<Project>>(){}.getType();
        projectList = gson.fromJson(currFile.getProjects(), projectArrayType);
        Log.d("CURRFILE", projectList.toString());

        if(init)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.placeholder, new SkillsListFragment());
            ft.commit();
            init = false;
        }
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }

    @Override
    public void communicate(String comm) {

    }
}
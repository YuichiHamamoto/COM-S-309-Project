package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.habit_tracker.Logic.HabitLogic.Habits;
import com.example.habit_tracker.Logic.Project;
import com.example.habit_tracker.Logic.ProjectLogic;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsModel;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will have the list of project from the user 
 * @author Jay Edwards 
 */
public class ProjectActivity extends AppCompatActivity implements IView {
    ListView listV;
    public static List<Project> listItems = new ArrayList<Project>();
    Button bt_addP;
    public static UserFile userFile;
    Gson gson = new Gson();
    public static int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        getSupportActionBar().setTitle("Project");
        bt_addP = findViewById(R.id.goToAddButton);
        listV = findViewById(R.id.proj_list);
        getReq();
        System.out.println(listItems.toString());
        ArrayAdapter<Project> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItems);
        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                Intent in = new Intent(ProjectActivity.this, ProjectDetailsActivity.class);
                startActivity(in);
            }
        });
    }
    /** 
     * this method takes you to the Add Project view
     * @param view 
     */
    public void toAddProject(View view){
        Intent in = new Intent(ProjectActivity.this,AddProject.class);
        startActivity(in);
    }
    public void toToDo(View view){
        Intent in = new Intent(ProjectActivity.this,homeActivity.class);
        startActivity(in);
    }
    /** 
     * this method takes you to the HabitActivity
     * @param view 
     */
    public void toHabit(View view){
        Intent in = new Intent(ProjectActivity.this,HabitActivity.class);
        startActivity(in);
    }
    /** 
     * this method takes you to the StatisticActivity
     * @param view 
     */
    public void toStat(View view){
        Intent in = new Intent(ProjectActivity.this,StatisticActivity.class);
        startActivity(in);
    }
    /** 
     * this method takes you to the SkillActivity
     * @param view 
     */
    public void toSkill(View view){
        Intent in = new Intent(ProjectActivity.this,SkillActivity.class);
        startActivity(in);
    }
    /** 
     * this method takes you to the SettingActivity
     * @param view 
     */
    public void toSetting(View view){
        Intent in = new Intent(ProjectActivity.this,SettingActivity.class);
        startActivity(in);
    }

    @Override
    public void showText(int n,String s) {
        userFile =  gson.fromJson(s,UserFile.class);
        Type projectArrayType = new TypeToken<ArrayList<Project>>(){}.getType();
        listItems = gson.fromJson(userFile.getProjects(), projectArrayType);
        Log.d("CURRFILE", listItems.toString());


    }
    public void getReq(){
        ServerRequest serverRequest = new ServerRequest();
        ProjectLogic logic = new ProjectLogic(this,serverRequest);
        logic.updateProject("GET",null);
    }


    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}
package com.example.habit_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habit_tracker.Logic.Project;
import com.example.habit_tracker.Logic.ProjectLogic;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsDetailsFragment;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsLogic;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class displays the details of a given project
 * @author Jay Edwards
 */
public class ProjectDetailsActivity extends AppCompatActivity implements IView{
    Button delete;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetails);
        getSupportActionBar().setTitle("Project Details");
        TextView proj_desc = findViewById(R.id.proj_desc);
        delete = findViewById(R.id.deletebtn);
        TextView proj_name = findViewById(R.id.proj_name);
        TextView vcon_link = findViewById(R.id.vcon_link);
        TextView mng_link = findViewById(R.id.mng_link);
        proj_name.setText(ProjectActivity.listItems.get(ProjectActivity.pos).getName());
        if(ProjectActivity.listItems.get(ProjectActivity.pos).getProject_Description() != null) {
            proj_desc.setText(ProjectActivity.listItems.get(ProjectActivity.pos).getProject_Description());
        }
        if(ProjectActivity.listItems.get(ProjectActivity.pos).getVersionControlLink() != null) {
            vcon_link.setText(ProjectActivity.listItems.get(ProjectActivity.pos).getVersionControlLink());
        }
        if(ProjectActivity.listItems.get(ProjectActivity.pos).getManagementLink() != null) {
            mng_link.setText(ProjectActivity.listItems.get(ProjectActivity.pos).getManagementLink());
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postReqDel();
            }
        });

    }
    public void postReqDel(){
        ProjectActivity.listItems.remove(ProjectActivity.pos);
        final String projJson = gson.toJson(ProjectActivity.listItems);
        ProjectActivity.userFile.setProjects(projJson);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(ProjectActivity.userFile.toJSON());
        }catch (JSONException err){
            Log.d("Error in json post", err.toString());
        }

        ServerRequest serverRequest = new ServerRequest();
        ProjectLogic pl = new ProjectLogic(this, serverRequest);
        pl.updateProject("POST",jsonObject);

    }
    public void backBtn(View view){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }
    public void toEdit(View view){
        Intent intent = new Intent(this, ProjectEdit.class);
        startActivity(intent);
    }


    @Override
    public void showText(int n, String s) throws JSONException {

    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}

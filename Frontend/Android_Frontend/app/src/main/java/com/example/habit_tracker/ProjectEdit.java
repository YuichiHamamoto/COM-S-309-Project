package com.example.habit_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habit_tracker.Logic.Project;
import com.example.habit_tracker.Logic.ProjectLogic;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsDetailsFragment;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsLogic;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

/**
 * This class allows the user to edit an already existing project
 * @author Jay Edwards
 */
public class ProjectEdit extends AppCompatActivity implements IView{
    EditText edit_projname,edit_projdesc,edit_vcon,edit_mng;
    Button submit;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproject);
        getSupportActionBar().setTitle("Edit Project Details");
        edit_projname = findViewById(R.id.editProjName);
        edit_projdesc = findViewById(R.id.editDesc);
        edit_vcon = findViewById(R.id.editVcon);
        edit_mng = findViewById(R.id.editMng);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryToEditProject();
            }
        });

    }
    public void backBtn(View view){
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        startActivity(intent);
    }
    public void tryToEditProject() {
        tryAddProject();
        postReqDel();
        }
    public void tryAddProject() {
        String newProjName;
        String newProjDesc;
        String newVcon;
        String newMng;
        if(edit_projname.getText()== null || !edit_projname.getText().toString().equals("")) {
            newProjName = edit_projname.getText().toString();
        }else {
            newProjName = ProjectActivity.listItems.get(ProjectActivity.pos).getName();
        }
        if(edit_projdesc.getText()== null ||!edit_projdesc.getText().toString().equals("")) {
            newProjDesc = edit_projdesc.getText().toString();
        }else {
            newProjDesc = ProjectActivity.listItems.get(ProjectActivity.pos).getProject_Description();
        }
        if(edit_vcon.getText()== null || !edit_vcon.getText().toString().equals("")) {
            newVcon = edit_vcon.getText().toString();
        }else {
            newVcon = ProjectActivity.listItems.get(ProjectActivity.pos).getVersionControlLink();
        }
        if(edit_mng.getText()== null || !edit_mng.getText().toString().equals("")) {
            newMng = edit_mng.getText().toString();
        }else {
            newMng = ProjectActivity.listItems.get(ProjectActivity.pos).getManagementLink();
        }

            Project projToAdd = new Project(newProjName, ProjectActivity.listItems.get(ProjectActivity.pos).getStartDate(),newVcon,newMng,newProjDesc);
            for(int i = 0; i < ProjectActivity.listItems.size(); i++)
            {
                if(ProjectActivity.listItems.get(i).getName().equals(projToAdd.getName()))
                {
                    ProjectActivity.listItems.remove(i);
                }
            }
            ProjectActivity.listItems.add(projToAdd);
            final String projectJson = gson.toJson(ProjectActivity.listItems);
            ProjectActivity.userFile.setProjects(projectJson);
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject = new JSONObject(ProjectActivity.userFile.toJSON());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            ServerRequest sr = new ServerRequest();
            ProjectLogic logic = new ProjectLogic(this, sr);
            logic.updateProject("POST", jsonObject);
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
    @Override
    public void showText(int n,String s) {

    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}

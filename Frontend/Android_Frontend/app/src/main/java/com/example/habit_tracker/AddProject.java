package com.example.habit_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habit_tracker.Logic.Project;
import com.example.habit_tracker.Logic.ProjectLogic;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
/**
 * This class is the view for when the user will add a project
 * @author Jay Edwards
 */
public class AddProject extends AppCompatActivity implements IView {
    Button btn_submit,btn_back;
    EditText et_projname;
    EditText et_projdesc;
    EditText et_vcon;
    EditText et_mng;
    UserFile userFile;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);
        getSupportActionBar().setTitle("Add a Project");

        btn_submit = findViewById(R.id.submitButton);
        et_projname = findViewById(R.id.editProjName);
        btn_back = findViewById(R.id.backButton);
        et_projdesc = findViewById(R.id.editDesc);
        et_vcon = findViewById(R.id.editVcon);
        et_mng = findViewById(R.id.editMng);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryAddProject();
            }
        });

    }


    /**
     *  This is just a back button the previous page
     *@param view
     */
    public void backBtn(View view){
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }
    public void tryAddProject() {
        String newProjName = et_projname.getText().toString();
        String newProjDesc = et_projdesc.getText().toString();
        String newVcon = et_vcon.getText().toString();
        String newMng = et_mng.getText().toString();
        if(newProjName.length() != 0) {
            java.util.Date d1 = new java.util.Date();
            Date startDate = new java.sql.Date(d1.getTime());
            Project projToAdd = new Project(newProjName, startDate,newVcon,newMng,newProjDesc);
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
        }else {
            Toast.makeText(AddProject.this, "You have to put a name for the project in", Toast.LENGTH_LONG).show();
        }
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

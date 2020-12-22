package com.example.habit_tracker.Logic;
import com.example.habit_tracker.IView;
import com.example.habit_tracker.Logic.IVolleyListener;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.sql.Date;

/**
 *  This is the class that will attempt the server request
 * @author jay edwards
 */
public class ProjectLogic implements IVolleyListener {
    IView r;
    ServerRequest sr;
    Date startdate;
    String projectData = "";
    UserFile use;
    String json = "";
    Gson gson = new Gson();
    /**
     *  This takes in an IView and ServerRequest object to initialize it
     * @param r, sr
     */
    public ProjectLogic(IView r, ServerRequest sr) {
        this.r = r;
        this.sr = sr;
        sr.addVolleyListener(this);
    }

    /**
     * This just gets the project data from a user with a specific username
     * @return project data
     */
    public String ProjectDetails() {
        String url ="http://10.24.226.239:8080/demo/api/files/mimi";
        sr.sendToServer(url, null, "GET", false);
        System.out.println(projectData);
        return projectData;
    }

    /**
     * This does what the above method does except it requires a json and a string to tell it to GET or POST. I only
     * use the POST portion to put user data onto the server
     * @param type
     * @param json
     * @return
     */
    public String updateProject(String type, JSONObject json){
        if (type.equals("GET")){
            String url ="http://10.24.226.239:8080/demo/api/files/"+LoginActivity.username;
            sr.sendToServer(url, null, "GET", false);
        }
        else if(type.equals("POST")){
            String url ="http://10.24.226.239:8080/demo/api/files/newfiles";
            sr.sendToServer(url, json, "POST", true);
        }
        return projectData;
    }
    /**
     * This method checks to see if the submission was successful and changes the project data
     * @param s
     */
    @Override
    public void onSuccess(String s) throws JSONException {
        json = s;
        use =  gson.fromJson(json, UserFile.class);
        System.out.println(s);
        r.showText(0,s);
        this.projectData = s;
    }

    /**
     * don't know how to implement this yet sry.
     * @param s
     */
    @Override
    public void onError(String s) {

    }
}

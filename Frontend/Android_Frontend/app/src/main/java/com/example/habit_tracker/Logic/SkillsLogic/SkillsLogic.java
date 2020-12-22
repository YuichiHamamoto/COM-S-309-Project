package com.example.habit_tracker.Logic.SkillsLogic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.Logic.IVolleyListener;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Handler to make requests to server from Skills View
 */
public class SkillsLogic implements IVolleyListener {

    IServerRequest serverRequest;
    IView r;

    String response = "";
    String json = "";
    Gson gson = new Gson();
    UserFile currFile;

    /**
     * Class constructor
     * @param r
     * @param serverRequest
     */
    public SkillsLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);

    }

    /**
     * Empty Class Contructor
     */
    public SkillsLogic(){

    }

    /**
     * Get or post skills to user files in database
     * @param type GET or POST
     * @param json Json object of UserFiles to post
     * @return Server response
     */
    public String updateSkills(String type, JSONObject json){

        if (type.equals("GET")){
            String url ="http://10.24.226.239:8080/demo/api/files/"+ LoginActivity.username;
            serverRequest.sendToServer(url, null, "GET", false);
        }
        else if(type.equals("POST")){
            String url ="http://10.24.226.239:8080/demo/api/files/newfiles";
            serverRequest.sendToServer(url, json, "POST", true);
        }

        return response;
    }

    /**
     * Set response on successful request
     * @param response
     */
    @Override
    public void onSuccess(String response) throws JSONException {

        json = response;
        currFile =  gson.fromJson(json, UserFile.class);
        r.showText(0,response);
        this.response = response;

    }

    /**
     * Return response
     * @return response
     */
    public String getResponse(){

        return this.response;
    }


    /**
     * Handler for error responses
     * @param errorMessage
     */
    @Override
    public void onError (String errorMessage) {
//        r.toastText(errorMessage);
//        r.showText("Error with request, please try again");
    }






}


package com.example.habit_tracker.Logic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;
import com.example.habit_tracker.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class InitializeUserLogic implements IVolleyListener{
    IView r;
    IServerRequest serverRequest;

    public InitializeUserLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean initializeUser(String username){
        String url = "http://10.24.226.239:8080/demo/api/files/newfiles";
        final JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", username);
            jsonObject.put("projects","[]");
            jsonObject.put("skills", "[]");
            jsonObject.put("habits", "[{\"c\": {\"year\": 2020, \"month\": 10, \"minute\": 46, \"second\": 17, \"hourOfDay\": 23, \"dayOfMonth\": 17}, \"tag\": \"\", \"date\": \"Nov 17, 2020\", \"name\": \"Push Everyday\", \"habitid\": 10, \"favorite\": false, \"username\": \"cow\", \"frequency\": \"Never\"}, {\"c\": {\"year\": 2020, \"month\": 10, \"minute\": 46, \"second\": 50, \"hourOfDay\": 23, \"dayOfMonth\": 17}, \"tag\": \"\", \"date\": \"Nov 17, 2020\", \"name\": \"Update Profile\", \"habitid\": 5, \"favorite\": false, \"username\": \"cow\", \"frequency\": \"Never\"}]");
            jsonObject.put("settings", "{\"notification\": \"on\"}");


        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        serverRequest.sendToServer(url, jsonObject, "POST", true);

        url = "http://10.24.226.239:8080/demo/api/stats/newStat";
        final JSONObject jsonObject2 = new JSONObject();
        try{
            jsonObject2.put("username", SignUpActivity.username);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        serverRequest.sendToServer(url, jsonObject2, "POST", true);


        return true;
    }
    @Override
    public void onSuccess(String s) throws JSONException {

    }

    @Override
    public void onError(String s) {

    }
}

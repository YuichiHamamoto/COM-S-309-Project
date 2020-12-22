package com.example.habit_tracker.Logic.NotificationLogic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.Logic.IVolleyListener;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class SetNotificationSettingLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;
    boolean isSettingSet = false;

    public SetNotificationSettingLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean setNotificationLogic(String file, boolean onoff) throws JSONException {
        String url = "http://10.24.226.239:8080/demo/api/files/newfiles";
        JsonObject jsonObject = new JsonParser().parse(file).getAsJsonObject();
        jsonObject.remove("settings");
        JSONObject object = new JSONObject(jsonObject.toString());

        if(onoff){
            try{
                object.put("settings", "{\"notification\": \"on\"}");

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            try{
                object.put("settings", "{\"notification\": \"off\"}");

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        serverRequest.sendToServer(url, object, "POST", true);
        return isSettingSet;
    }
    @Override
    public void onSuccess(String s) throws JSONException {

    }

    @Override
    public void onError(String s) {

    }
}

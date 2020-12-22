package com.example.habit_tracker.Logic.NotificationLogic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.Logic.IVolleyListener;
import com.example.habit_tracker.Network.IServerRequest;

import org.json.JSONException;

public class CheckNotificationSettingLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;
    boolean isChecked = false;

    /**
     *
     * @param r
     * Given IVeiw
     * @param serverRequest
     * Given serverRequest
     */
    public CheckNotificationSettingLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public boolean checkNotificationSetting(String username){
        String url ="http://10.24.226.239:8080/demo/api/files/"+username;
        serverRequest.sendToServer(url, null, "GET", false);
        return isChecked;
    }
    @Override
    public void onSuccess(String s) throws JSONException {
        r.showText(4,s);
        isChecked = true;
    }

    @Override
    public void onError(String s) throws JSONException {
        r.showText(0,"something went wrong");
    }
}

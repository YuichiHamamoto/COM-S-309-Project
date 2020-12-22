package com.example.habit_tracker.Logic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;

import org.json.JSONException;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class VertifyPasswordLogic implements IVolleyListener{
    IView r;
    IServerRequest serverRequest;
    String password;
    boolean isPasswordVerified;

    /**
     *
     * @param r
     * Given IView
     * @param serverRequest
     * Given serverRequest
     */
    public VertifyPasswordLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * verify password
     * @param password
     * Given password
     * @return
     * Indicates if the password is verified
     */
    public boolean vertifyPassword(String password){
        this.password = password;
        String url ="http://10.24.226.239:8080/demo/api/login/"+ LoginActivity.username;
        serverRequest.sendToServer(url, null, "GET", false);
        return isPasswordVerified;
    }

    /**
     * onSuccess method called in the server request
     * @param response
     * Given response
     */
    @Override
    public void onSuccess(String response) throws JSONException {
        String[] parts = response.split(",");
        String [] partsPass = parts[1].split(":");
        String password = partsPass[1].substring(1,partsPass[1].length()-1);

        if (!this.password.isEmpty()&&this.password.equals(password)) {
            r.showText(0,"1");
            isPasswordVerified = true;

        } else {
            r.showText(0,"2");
        }
    }

    /**
     * onError method called in the server request
     * @param s
     * Given string
     */
    @Override
    public void onError(String s) throws JSONException {
        if(s.equals("")){
            r.showText(0,"Received null");
        }
        else {
            r.showText(0,"Error with request, please try again");
        }
    }
}

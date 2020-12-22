package com.example.habit_tracker.Logic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class SetEmailLogic implements IVolleyListener{
    IView r;
    IServerRequest serverRequest;
    boolean isSetEmail = false;

    /**
     *
     * @param r
     * Given IView
     * @param serverRequest
     * Given serverRequest
     */
    public SetEmailLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * set an email for the user
     * @param email
     * Given email
     * @param emailver
     * Given email to verify the email
     * @param password
     * Given password
     * @return
     */
    public boolean setEmail(String email, String emailver,String password) throws JSONException {
        if(!email.equals(emailver)){
            r.showText(0,"Email doesn't match");
        }
        else{
            String url ="http://10.24.226.239:8080/demo/api/login/addUser";
            final JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("username", LoginActivity.username);
                jsonObject.put("password",password);
                jsonObject.put("email",email);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            serverRequest.sendToServer(url, jsonObject, "POST", true);
            LoginActivity.email = email;
        }
        return isSetEmail;
    }

    /**
     * onSuccess method called in the server request
     * @param s
     * Given String
     */
    @Override
    public void onSuccess(String s) throws JSONException {
        isSetEmail = true;
        r.showText(0,"3");
    }

    /**
     * onError method called in the server request
     * @param s
     * Given String
     */
    @Override
    public void onError(String s) throws JSONException {
        isSetEmail = true;
        r.showText(0,"3");
    }
}

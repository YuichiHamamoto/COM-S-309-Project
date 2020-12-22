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
public class ChangePasswordLogic implements IVolleyListener {
    /**
     * The view calling this logic
     */
    IView r;

    /**
     * The surverRequest called in the class which is calling this logic
     */
    IServerRequest serverRequest;

    /**
     *
     * Indicates if the password is updated
     */
    boolean isPasswordChanged = false;

    /**
     *
     * @param r
     * Given IView
     * @param serverRequest
     * Given serverRequest
     */
    public ChangePasswordLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * Change user's password
     * @param password
     * Given password
     * @param verpassword
     * Given password to verify
     * @return
     * Indicates if password is change
     */
    public boolean changePassword(String password, String verpassword) throws JSONException {
        if(!password.equals(verpassword)){
            r.showText(0,"Password doesn't match");
        }
        else if(password.length()<8){
            r.showText(0,"Password has to have at least 8 words");
        }
        else{
            String url ="http://10.24.226.239:8080/demo/api/login/addUser";
            final JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("username", LoginActivity.username);
                jsonObject.put("password",password);
                jsonObject.put("email", LoginActivity.email);

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            serverRequest.sendToServer(url, jsonObject, "POST", true);
        }
        return isPasswordChanged;
    }

    /**
     * onSuccess method called in the server request
     * @param s
     * Given string
     */
    @Override
    public void onSuccess(String s) throws JSONException {
        isPasswordChanged = true;
        //r.showText(0,"3");
        r.showText(3,s);
    }


    /**
     * onError method called in the server request
     * @param s
     * Given string
     */
    @Override
    public void onError(String s) throws JSONException {
        isPasswordChanged = true;
        //r.showText(0,"3");
        r.showText(3,s);
    }
}

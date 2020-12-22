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
public class LoginLogic implements IVolleyListener{
    IView r;
    IServerRequest serverRequest;
    String password;
    boolean isLogedin = false;

    /**
     *
     * @param r
     * Given IVeiw
     * @param serverRequest
     * Given serverRequest
     */
    public LoginLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * Let user login
     * @param username
     * Given username
     * @param password
     * Given password
     * @return
     * @throws JSONException
     */
    public boolean login(String username,String password) throws JSONException {
        this.password = password;
        String url ="http://10.24.226.239:8080/demo/api/login/"+username;
        serverRequest.sendToServer(url, null, "GET", false);
        return isLogedin;
    }


    /**
     * onSuccess method called in the server request
     * @param response
     * Given response in the serverRequest
     */
    @Override
    public void onSuccess(String response) throws JSONException {
        if(response.equals("null")){
            r.showText(0,"Wrong password or username entered");
        }
        else {
            String[] parts = response.split(",");
            String[] partsPass = parts[1].split(":");
            String password = partsPass[1].substring(1, partsPass[1].length() - 1);

            if (!this.password.isEmpty() && this.password.equals(password)) {
                r.showText(0,"You are logged in!");
                r.switchActivity(0);
                String[] partsEmail = parts[2].split(":");
                LoginActivity.email = partsEmail[1].substring(1, partsEmail[1].length() - 1);
                isLogedin = true;
            } else {
                r.showText(0,"Wrong password or username entered");
            }
        }


    }

    /**
     * onError method called in the server request
     * @param errorMessage
     * Given error message in the server request
     */
    @Override
    public void onError (String errorMessage) throws JSONException {
        r.showText(0,"Error with request, please try again");
    }


}

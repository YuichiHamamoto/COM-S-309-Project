package com.example.habit_tracker.Logic;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.Network.IServerRequest;
import org.json.JSONException;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class StatLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;

    /**
     *
     * @param r
     * Given IView
     * @param serverRequest
     * Given serverRequest
     */
    public StatLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * Call get request for to shows statistic
     * @param username
     */
    public void getStat(String username){
        String url ="http://10.24.226.239:8080/demo/api/stats/"+username;
        serverRequest.sendToServer(url, null, "GET", false);
    }

    /**
     * onSuccess method called in the server request
     * @param s
     * Given String
     */
    @Override
    public void onSuccess(String s) throws JSONException {
        r.showText(0,s);
    }

    /**
     * onError method called in the server request
     * @param s
     * Given string
     */
    @Override
    public void onError(String s) throws JSONException {
        r.showText(0,"Something went wrong");
    }
}

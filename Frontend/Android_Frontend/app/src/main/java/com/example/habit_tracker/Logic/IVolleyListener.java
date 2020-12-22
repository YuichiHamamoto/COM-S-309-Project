package com.example.habit_tracker.Logic;

import org.json.JSONException;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public interface IVolleyListener {
    /**
     * onSuccess method called in the server request
     * @param s
     * Given String
     * @throws JSONException
     */
    public void onSuccess(String s) throws JSONException;

    /**
     * onError method called in the server request
     * @param s
     * Given String
     */
    public void onError(String s) throws JSONException;
}

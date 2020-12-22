package com.example.habit_tracker.Network;

import com.example.habit_tracker.Logic.IVolleyListener;
import org.json.JSONObject;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public interface IServerRequest {
    /**
     *
     * @param url
     * Given URL
     * @param jsonObject
     * Given json object
     * @param methodType
     * Given method type GET/POST
     * @param isJson
     * Indicates if it is Json request.
     */
    public void sendToServer(String url, JSONObject jsonObject, String methodType,Boolean isJson);

    /**
     *
     * @param logic
     * Given logic
     */
    public void addVolleyListener(IVolleyListener logic);
}

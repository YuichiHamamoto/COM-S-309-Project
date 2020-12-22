package com.example.habit_tracker.Network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.habit_tracker.AppController;
import com.example.habit_tracker.Logic.IVolleyListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class ServerRequest implements IServerRequest{
    private String tag_json_obj = "json_obj_req";
    private IVolleyListener l;

    @Override
    public void sendToServer(String url, JSONObject jsonObj, String methodType, Boolean isJson) {

        int method = Request.Method.GET;

        if (methodType.equals("POST")) {
            method = Request.Method.POST;
        }
        if(isJson) {
            JsonObjectRequest jsonRequest = new JsonObjectRequest(method, url, jsonObj,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response != null) {
                                try {
                                    l.onSuccess(response.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(response.toString());
                            } else {
                                try {
                                    l.onSuccess(response.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                l.onError(error.getMessage());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );

            AppController.getInstance().addToRequestQueue(jsonRequest, tag_json_obj);
        }
        else{
            StringRequest stringRequest = new StringRequest(method, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response != null) {
                                try {
                                    l.onSuccess(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(response);
                            } else {
                                try {
                                    l.onError("");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        l.onError(error.getMessage());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
        }
    }

    @Override
    public void addVolleyListener(IVolleyListener logic) {
        l = logic;
    }

}

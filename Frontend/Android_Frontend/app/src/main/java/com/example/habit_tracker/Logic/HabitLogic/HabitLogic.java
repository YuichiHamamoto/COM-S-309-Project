package com.example.habit_tracker.Logic.HabitLogic;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.Logic.IVolleyListener;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Tyler O'Hare
 *
 */
public class HabitLogic implements IVolleyListener {

    IServerRequest serverRequest;
    IView r;

    Button viewItemBtn;
    ListView myListView;
    ArrayAdapter myArrayAdapter;

    String response = "";

    String urlPost ="http://10.24.226.239:8080/demo/api/files/newfiles";


    String json = "";
    Gson gson = new Gson();

    TextView textView;
    ArrayList<Habits> habitList;
    //ArrayList<Habits> habitsArrayList = new ArrayList<>();
    UserFile currFile;

    /**
     *
     * @param r IView to use
     * @param serverRequest serverRequest to use
     *  Create a habit logic object to add new server requests
     */
    public HabitLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);

    }
    public HabitLogic(){

    }

    /**
     *
     * @param type type of request being sent
     * @param json json object being sent
     * @return response of request
     *
     */
    public String updateHabit(String type, JSONObject json){

        if (type.equals("GET")){
            String url ="http://10.24.226.239:8080/demo/api/files/"+ LoginActivity.username;
            serverRequest.sendToServer(url, null, "GET", false);
        }
        else if(type.equals("POST")){
            String url ="http://10.24.226.239:8080/demo/api/files/newfiles";
            serverRequest.sendToServer(url, json, "POST", true);
        }

        return response;
    }
    public String updateHabitm(String type, JSONObject json){
        return null;
    }


    /**
     *
     * @param response Response of request
     * called after a request is sucessful
     */
    @Override
    public void onSuccess(String response) throws JSONException {

        json = response;
        currFile =  gson.fromJson(json, UserFile.class);
        r.showText(0,response);
        this.response = response;

    }

    public String getResponse(){

        return null;
    }



    @Override
    public void onError (String errorMessage) {
//        r.toastText(errorMessage);
//        r.showText("Error with request, please try again");
    }






}
package com.example.habit_tracker.Logic.HabitLogic;

import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.habit_tracker.IView;
import com.example.habit_tracker.Logic.IVolleyListener;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.LoginActivity;
import com.example.habit_tracker.Network.IServerRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Tyler O'Hare
 *
 */
public class HabitStatPostLogic implements IVolleyListener {

    IServerRequest serverRequest;
    IView r;

    Button viewItemBtn;
    ListView myListView;
    ArrayAdapter myArrayAdapter;

    String urlPost ="http://10.24.226.239:8080/demo/api/files/newfiles";


    String json = "";
    Gson gson = new Gson();
    String response = "";

    TextView textView;
    ArrayList<Habits> habitList;
    //ArrayList<Habits> habitsArrayList = new ArrayList<>();
    UserFile currFile;
    int counting = 0;

    /**
     *
     * @param r IView for request
     * @param serverRequest serverRequest to use
     */
    public HabitStatPostLogic(IView r, IServerRequest serverRequest){
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);


    }


    /**
     *
     * @param json Json to update
     *             update the statistic for the given habit
     */
    public boolean updateHabitStat(String type, JSONObject json){
        if(type.equals("GET")){
            String url = "http://coms-309-rp-07.cs.iastate.edu:8080/demo/api/stats/"+ LoginActivity.username;
            serverRequest.sendToServer(url, null, "GET", false);
            return false;
        }
        else{
            String url = "http://coms-309-rp-07.cs.iastate.edu:8080/demo/api/stats/newStat";
            serverRequest.sendToServer(url, json, "POST", true);

            return true;

        }




    }




    @Override
    public void onSuccess(String s) throws JSONException {

        json = s;
//        currFile =  gson.fromJson(json, UserFile.class);


        r.showText(2,json);




        this.response = response;

    }

    @Override
    public void onError(String s) {

    }

    /**
     *
     * @return String of the stat that was updated
     */
    public String updateStat(String stat){



        int num;
        String newStat = "";
        if(stat.equals("null")){
            newStat = "1";

        }
        else{
            num = Integer.parseInt(stat.substring(1,stat.length()-1))+1; // ""1""
            newStat = num+"";
        }

        return newStat;
    }
}
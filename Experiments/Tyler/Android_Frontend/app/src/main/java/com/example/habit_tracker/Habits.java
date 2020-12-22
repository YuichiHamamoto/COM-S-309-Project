package com.example.habit_tracker;
//import com.google.gson.Gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Habits {

    @SerializedName("username")
    private String username = "cow";
    @SerializedName("name")
    private String name = "";
    @SerializedName("habitid")
    private int habitid = -1;
    @SerializedName("tag")
    private String tag;
    @SerializedName("habits")
    private String habitView;




    public Habits(String name,String username, int habitid,String tag,String habitView) {
        this.name = name;
        this.username = username;
        this.habitid = habitid;
        this.tag = tag;
        this.habitView = habitView;
    }
    public Habits(String name) {
        this.name = name;
    }

    public Habits() {

    }

    @Override
    public String toString() {
        return
                "user:" + username  +
                ", habit: " + name  +
                ", habitid=" + habitid +
                ", tag=" + tag
                ;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
//    public String fromJSON() {
//        Gson gson = new Gson();
//        return gson.fromJson(this);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHabitId(int habitId){

        this.habitid = habitId;
    }

    public int getHabitId(){
        return habitid;

    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }
    public void setHabitView(String habitView) {
        this.habitView = habitView;
    }
    public String getHabitView() {
        return habitView;
    }
}


package com.example.habit_tracker.Logic.HabitLogic;
//import com.google.gson.Gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tyler O'Hare
 *
 */
public class Habits {

    @SerializedName("username")
    private String username = "cow";
    @SerializedName("name")
    private String name = "";
    @SerializedName("habitid")
    private int habitid = -1;
    @SerializedName("tag")
    private String tag;
    @SerializedName("favorite")
    private boolean favorite;
    @SerializedName("repeat")
    private String repeat;
    @SerializedName("date")
    private String date;

    @SerializedName("frequency")
    private String frequency;

    private String dueDate;



    private Calendar c;


    /**
     *
     * @param name name of habit
     * @param username username of habit creator
     * @param habitid id of habit
     * @param tag tag of habit
     * @param favorite true is habit is a favorite, false if not
     * @param frequency frequency of the habit
     * Constructor for the habit object
     */
    public Habits(String name,String username, int habitid,String tag,boolean favorite,String frequency) {
        this.name = name;
        this.username = username;
        this.habitid = habitid;
        this.tag = tag;
        this.favorite = favorite; //Boolean.toString(favorite);
        //this.repeat = Boolean.toString(repeat);
        this.date = date;
        this.frequency = frequency;
        //this.habitView = habitView;
        genDueDate(frequency);
        this.c = Calendar.getInstance();


    }
    public Habits(String name) {
        this.name = name;
    }

    public Habits() {

    }

    public Habits(String name, String date) {
        this.name = name;
        this.date = dueDate;
    }

    @Override
    public String toString() {
        return
//                "user:" + username  +
                " habit: " + name  +
//                ", habitid=" + habitid +
                        ", tag=" + tag +
                        ", Fav=" + favorite +
//                        ", Rep=" + repeat +
                        ", freq=" + frequency +
//                        ", Date=" + date +
                        ", Due=" + dueDate


                ;
    }

    /**
     *
     * @return
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
//    public String fromJSON() {
//        Gson gson = new Gson();
//        return gson.fromJson(this);
//    }

    /**
     *
     * @return name of the habit
     * get name of the habit
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name to set habit
     * set name of the habit
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return username of habit creator
     * get username of habit creator
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username username to set
     * set username of habit creator
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param habitId
     *
     */
    public void setHabitId(int habitId){

        this.habitid = habitId;
    }

    /**
     *
     * @return id number of the habit
     * get teh id of the habit
     *
     */
    public int getHabitId(){
        return habitid;

    }

    /**
     *
     * @param tag tag to set the haibt
     * set the tag of given habit
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     *
     * @return tag of given habit
     * set the tag of the given habit
     *
     */
    public String getTag() {
        return tag;
    }

    /**
     *
     * @param favorite true if it is a favorite, false if not
     *                 set habit to be a favorite or not
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     *
     * @return whether or not habit is marked as a favorite
     * get favorite status of habit
     */
    public boolean getFavorite() {
        return favorite;
    }

    /**
     *
     * @param date date to set the selected habit
     *             set the date of the selected habit
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return date of selected habit
     * get the date of the selected habit
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param frequency the frequency of the selected habit ot set
     *                  set the frequency of the selected habit
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     *
     * @return the frequency of the selected habit
     * get the frequency of the selected habit
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     *
     * @param cal Calender associated with the selected habit
     * @param frequency frequency of the habit
     *                  set dudedate for habit
     */
    public void setDueDate(Calendar cal,String frequency) {


        this.dueDate = dueDate;
    }
    public String setDueDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date myDate = sdf.parse(date);


        SimpleDateFormat sdfx = new SimpleDateFormat("EEE MMM dd");

        String toChange = sdfx.format(myDate);
        this.dueDate = toChange;
        return toChange;
    }

    /**
     *
     * @return duedate in string form
     * Get due date of given string
     */
    public String getDueDate() {
        return dueDate;
    }



    /**
     *
     * @param frequency frequency of the habit
     * set an inital duedate for given habit
     */
    public void genDueDate (String frequency){
        Calendar c =  Calendar.getInstance();
        Date today = c.getTime();

        date = DateFormat.getDateInstance().format(today);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd");


        if (frequency.equals("Never")){


        }
        else if (frequency.equals("Every day")){
            c.add(Calendar.DATE,1);
            this.dueDate = sdf.format(c.getTime());

        }
        else if (frequency.equals("Every Week")){
            c.add(Calendar.DATE,7);
            this.dueDate = sdf.format(c.getTime());

        }


    }

    /**
     *
     * @param frequency frequency of habit
     * @param createDate date habit was created
     * @throws ParseException
     * Generate a new duedate for given habit
     */
    public String genNewDueDate (String frequency,String createDate) throws ParseException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd");
        cal.setTime(sdf.parse(createDate));

        String newDate = "";

        if (frequency.equals("Never")){

        }
        else if (frequency.equals("Every day")){
            cal.add(Calendar.DATE,1);
            newDate = sdf.format(cal.getTime());
            this.dueDate = newDate;

        }
        else if (frequency.equals("Every Week")){
            cal.add(Calendar.DATE,7);
            newDate = sdf.format(cal.getTime());
            this.dueDate = newDate;

        }
        return newDate;

    }

}

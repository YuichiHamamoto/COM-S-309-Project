package com.example.habit_tracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habit_tracker.Logic.HabitLogic.HabitHelper;
import com.example.habit_tracker.Logic.HabitLogic.HabitLogic;
import com.example.habit_tracker.Logic.HabitLogic.Habits;
import com.example.habit_tracker.Logic.LoginLogic;
import com.example.habit_tracker.Logic.NotificationLogic.CheckNotificationSettingLogic;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Yuichi Hamamoto
 *
 */



public class homeActivity extends AppCompatActivity implements IView {

    ListView myListView;
    ArrayAdapter myArrayAdapterx;
    String getResponse = "";
    Gson gson = new Gson();
    public static ArrayList<Habits> habitList;
    UserFile currFile;
    TextView welcome;
    String today;
    AlertDialog.Builder dialogBuilder;
     Button new_habit_btn_save,new_habit_btn_cancel,habit_btn_delete;
    AlertDialog dialog;
     EditText newHabitPopup,newHabitTag,dateText;
    String dueDate = "";



    TextView tv_alert;
    Boolean isNotificationOn = false;

    CheckNotificationSettingLogic logic;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("To Do");


        


        if(LoginActivity.flag){
            checkNotification();

            LoginActivity.flag = false;
        }



        welcome = findViewById(R.id.welcomeText);
        myListView = findViewById(R.id.myListViewx);





        getReq();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate((R.menu.habit_menu),menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.habitmenu){

            createNewHabitDialog();
        }
        return super.onOptionsItemSelected(item);
    }


    private void createNewHabitDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View todoPopupView = getLayoutInflater().inflate(R.layout.todo_popup, null);

        newHabitPopup = (EditText) todoPopupView.findViewById(R.id.newHabitPopupx);
        newHabitTag = (EditText) todoPopupView.findViewById(R.id.newHabitTagx);

        new_habit_btn_save = todoPopupView.findViewById(R.id.btn_savex);
        new_habit_btn_cancel = (Button) todoPopupView.findViewById(R.id.btn_cancelx);
//        dateText = findViewById(R.id.ediTextDate);

        //frequency selection dropdown adapter
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(homeActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.freq_dropdown));
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        dialogBuilder.setView(todoPopupView );




        dialog = dialogBuilder.create();
        dialog.show();


        new_habit_btn_save.setOnClickListener(new View.OnClickListener() {
            Habits habits;
            @Override
            public void onClick(View v) {

                // save via post req
                try {


                    postReq(habits);
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                //update our habits list
                HabitHelper habitHelper = new HabitHelper(homeActivity.this);
                ShowAllOnListView(habitHelper);
                //close dialog
                dialog.dismiss();
            }
        });

        new_habit_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cancel and close dialog
                dialog.dismiss();
            }
        });


    }
    private void postReq(Habits habits) throws JSONException, ParseException {

//        dueDate = dateText.getText().toString();



        habits = new Habits(newHabitPopup.getText().toString());
        Toast.makeText(homeActivity.this,"Hello",Toast.LENGTH_SHORT);
        HabitHelper habitHelper = new HabitHelper(homeActivity.this);

        boolean success = habitHelper.addOne(habits);
        ShowAllOnListView(habitHelper);

        //IN order to add a habit i should need to create a new habit object getting name from text.
        dueDate = newHabitTag.getText().toString();
        Habits habitSend = new Habits(newHabitPopup.getText().toString(),LoginActivity.username,habitList.size()+3,"None",false,"Never");
        habitSend.setDueDate(dueDate);

        newHabitPopup.setText("");
        newHabitTag.setText("");
        //add the new habit to the habit array list and update..
        habitList.add(habitSend);
        //now create new json and send post
        //AT this point wed want to recompile everything into the json but rn we're just working with habits and userfile
        final String habitJsonSend = gson.toJson(habitList);

        JsonObject jsonObject = new JsonParser().parse(getResponse).getAsJsonObject();
        //start post request
//        JSONObject jsonObject = new JSONObject();
        jsonObject.remove("habits");
        JSONObject object = new JSONObject(jsonObject.toString());


        try {
//            object.put("username",LoginActivity.username);
            object.put("habits",habitJsonSend);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ServerRequest serverRequest = new ServerRequest();
        HabitLogic habitLogic = new HabitLogic(this,serverRequest);
        habitLogic.updateHabit("POST",object);

    }


    public void getReq(){
        ServerRequest serverRequest = new ServerRequest();
        HabitLogic habitLogic = new HabitLogic(this,serverRequest);
        tryGetReqm(habitLogic);
        habitLogic.updateHabit("GET",null);

    }

    String tryGetReqm(HabitLogic logic){
        return logic.updateHabit("GET",null);


    }

    private void ShowAllOnListView(HabitHelper habitHelper) {

        myArrayAdapterx = new ArrayAdapter<Habits>(homeActivity.this, android.R.layout.simple_list_item_1, habitHelper.getAll(habitList));
        myListView.setAdapter(myArrayAdapterx);
    }


    public String setTodo() {
        Calendar c =  Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        today = sdf.format(c.getTime());
        myArrayAdapterx.getFilter().filter(today);
        welcome.setText("Welcome "+ LoginActivity.username+" here's what's due today:");
        return today;

    }

    ///////////////////////// Notifcation stuff ////////////////////////
    public void checkNotification(){
        ServerRequest serverRequest = new ServerRequest();
        logic = new CheckNotificationSettingLogic(this, serverRequest);
        tryCheckNotification(logic);
    }

    public boolean tryCheckNotification(CheckNotificationSettingLogic logic){
        return logic.checkNotificationSetting(LoginActivity.username);
    }
    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"userChannel")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notification")
                .setContentText("Check your todo list and habit for today!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        manager.notify(100,builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "userChannel";
            String description = "Channel for user notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("userChannel", name,importance);
            channel.setDescription(description);

            NotificationManager notificationMAnager = getSystemService(NotificationManager.class);
            notificationMAnager.createNotificationChannel(channel);
        }

    }




    /**
     * Jump to Stat view
     * @param view
     * Given view
     */
    public void toStat(View view){
        Intent in = new Intent(homeActivity.this,StatisticActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Habit view
     * @param view
     * Given view
     */
    public void toHabit(View view){
        Intent in = new Intent(homeActivity.this,HabitActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Skill view
     * @param view
     * Given view
     */
    public void toSkill(View view){
        Intent in = new Intent(homeActivity.this,SkillActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Project view
     * @param view
     * Given view
     */
    public void toProject(View view){
        Intent in = new Intent(homeActivity.this,ProjectActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Setting view
     * @param view
     * given view
     */
    public void toSetting(View view){
        Intent in = new Intent(homeActivity.this,SettingActivity.class);
        startActivity(in);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void showText(int n,String s) {
        if(n==0){
//            tv_alert.setText(s);
        }
        else if(n==4){
            String [] parts = s.split(",");
            String setting = parts[parts.length-1];
            parts = setting.split(":");
            String onoff = parts[2].substring(3,5);
            if(onoff.equals("on")){
                isNotificationOn = true;
                createNotificationChannel();
                addNotification();
            }

        }


        getResponse = s;
        currFile =  gson.fromJson(getResponse, UserFile.class);

        Type habitArrayType = new TypeToken<ArrayList<Habits>>(){}.getType(); //IMPORTANT: this prevents type erasure of the Habit object arraylist at runtime
        habitList = gson.fromJson(currFile.getHabits(), habitArrayType);
        //start integrated database

        final HabitHelper habitHelper = new HabitHelper(homeActivity.this);

        ShowAllOnListView(habitHelper);


        setTodo();
    }


    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }



}
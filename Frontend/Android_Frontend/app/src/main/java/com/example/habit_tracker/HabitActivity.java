package com.example.habit_tracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.habit_tracker.Logic.HabitLogic.HabitHelper;
import com.example.habit_tracker.Logic.HabitLogic.HabitLogic;
import com.example.habit_tracker.Logic.HabitLogic.HabitStatLogic;
import com.example.habit_tracker.Logic.HabitLogic.HabitStatPostLogic;
import com.example.habit_tracker.Logic.HabitLogic.Habits;
import com.example.habit_tracker.Logic.UserFile;
import com.example.habit_tracker.Network.ServerRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * @author Tyler O'Hare
 * This activity encompases all the functionality of habits and lets the
 * user view, add, edit and delete their habits.
 *
 */
public class HabitActivity extends AppCompatActivity implements IView{

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newHabitPopup,newHabitTag,dateText;
    private SearchView searchBar;
    private Button new_habit_btn_save,new_habit_btn_cancel,habit_btn_delete;
    private Button mon,tue,wed,thu,fri,sat,sun,fav,btn_fav,btn_tag;
    private CheckBox favoriteBox;
    private Spinner spinnerRepeat;
    private String freq;
    private String tagFilter = "Fav=true";
    JSONObject object;



    Button viewItemBtn;
    ListView myListView;
    static ArrayAdapter myArrayAdapter;

    String urlPost ="http://10.24.226.239:8080/demo/api/files/newfiles";


    String json = "";
    Gson gson = new Gson();
    public static ArrayList<Habits> habitList;
    UserFile currFile;
    String getResponse = "";


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        getSupportActionBar().setTitle("Habit");
        searchBar = findViewById(R.id.searchBar);
        myListView = findViewById(R.id.myListView);





        setComplete();
        setSearch();
        setFilters();

        //Get all habits and update the view
        getReq();

    }

    /**
     *
     * @param menu Menu to be used
     * @return Return true on successful inflate
     *
     * Inflates the create options menu when creating a new habit.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate((R.menu.habit_menu),menu);
        return true;
    }

    /**
     *
     * @param item
     * @return Value of selected options item
     * Used to see if an options item is selected when creating new habits
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.habitmenu){

            createNewHabitDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts the process of creating a new habit and adding it to the database of habits
     */
    private void createNewHabitDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View habitPopupView = getLayoutInflater().inflate(R.layout.habit_popup, null);

        newHabitPopup = (EditText) habitPopupView.findViewById(R.id.newHabitPopup);
        newHabitTag = (EditText) habitPopupView.findViewById(R.id.newHabitTag);
        new_habit_btn_save = (Button) habitPopupView.findViewById(R.id.new_habit_btn_save);
        new_habit_btn_cancel = (Button) habitPopupView.findViewById(R.id.new_habit_btn_cancel);

        favoriteBox = (CheckBox) habitPopupView.findViewById(R.id.favoriteBox);
        spinnerRepeat = (Spinner) habitPopupView.findViewById(R.id.spinnerRepeat);
        //frequency selection dropdown adapter
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(HabitActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.freq_dropdown));
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRepeat.setAdapter(dropdownAdapter);

        dialogBuilder.setView(habitPopupView);

        spinnerRepeat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freq = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                HabitHelper habitHelper = new HabitHelper(HabitActivity.this);
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

    /**
     *
     * @param habit Habit to be editted
     * @param position What postion this habit is in habit arraylist
     * creates a pop up dialog that edits selected habit
     */
    private void editHabitDialog(Habits habit, final int position){

        dialogBuilder = new AlertDialog.Builder(this);
        final View habitPopupView = getLayoutInflater().inflate(R.layout.habit_popup_edit, null);

        newHabitPopup = (EditText) habitPopupView.findViewById(R.id.newHabitPopup);
        newHabitTag = (EditText) habitPopupView.findViewById(R.id.newHabitTag);
        new_habit_btn_save = (Button) habitPopupView.findViewById(R.id.new_habit_btn_save);
        new_habit_btn_cancel = (Button) habitPopupView.findViewById(R.id.new_habit_btn_cancel);
        habit_btn_delete = (Button) habitPopupView.findViewById(R.id.new_habit_btn_delete);

        favoriteBox = (CheckBox) habitPopupView.findViewById(R.id.favoriteBox);
        spinnerRepeat = (Spinner) habitPopupView.findViewById(R.id.spinnerRepeat);
        //frequency selection dropdown adapter
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<String>(HabitActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.freq_dropdown));
        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRepeat.setAdapter(dropdownAdapter);

        dialogBuilder.setView(habitPopupView);
        //PRESET HABIT VALUES
        newHabitPopup.setText(habit.getName());
        newHabitTag.setText(habit.getTag());
        favoriteBox.setChecked(habit.getFavorite());
        //TODO:special case
        //spinnerRepeat.set


        spinnerRepeat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freq = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dialog = dialogBuilder.create();
        dialog.show();


        new_habit_btn_save.setOnClickListener(new View.OnClickListener() {
            Habits habits;
            @Override
            public void onClick(View v) {
                //remove existing habit and replace it
                habitList.remove(position);
                // save via post req
                try {
                    postReq(habits);
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                //update our habits list
                HabitHelper habitHelper = new HabitHelper(HabitActivity.this);
                List<Habits> all = habitHelper.getAll(habitList);
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
        habit_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                habitList.remove(position);
                HabitHelper habitHelper = new HabitHelper(HabitActivity.this);
                List<Habits> all = habitHelper.getAll(habitList);
                ShowAllOnListView(habitHelper);
                try {
                    postReqDel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

    }

    /**
     *
     * @param habit Habit to be completed
     * @param position What postion this habit is in habit arraylist
     * creates a pop up dialog that completes selected habit
     */
    private void completeHabitDialog(final Habits habit, final int position){

        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Complete this Habit?");
        dialogBuilder.setMessage("Complete this Habit?");

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });
        dialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {

                JSONObject JSONObjectStat = new JSONObject();;
                ServerRequest serverRequest = new ServerRequest();
                HabitStatLogic habitStatLogic = new HabitStatLogic(HabitActivity.this,serverRequest);
                habitStatLogic.updateHabitStat("GET",JSONObjectStat);

//                try {
//                    JSONObjectStat.put("username",LoginActivity.username);
//                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
//                    LocalDateTime now = LocalDateTime.now();
//                    String mytime = "s" + dtf.format(now);
//                    JSONObjectStat.put(mytime,2);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //here

                //hide the habit -- edit the stat to have a new duedate based on last due date OR Delete the habit if it has no repeat
                if (habitList.get(habitList.indexOf(habit)).getFrequency().equals("Never")){
                    //delete the habit
                    habitList.remove(habitList.indexOf(habit));
                    HabitHelper habitHelper = new HabitHelper(HabitActivity.this);
                    List<Habits> all = habitHelper.getAll(habitList);
                    ShowAllOnListView(habitHelper);
                }
                else{
                    try {
                        habitList.get(habitList.indexOf(habit)).genNewDueDate(habitList.get(position).getFrequency(),habitList.get(position).getDueDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //update our habits list
                    HabitHelper habitHelper = new HabitHelper(HabitActivity.this);
                    List<Habits> all = habitHelper.getAll(habitList);
                    ShowAllOnListView(habitHelper);

                }

            }
        });
        dialogBuilder.show();

    }

    /**
     * Allows user to create and set custom tag and favorite filter\
     */
    private void editFavoriteFilterDialog(){



        dialogBuilder = new AlertDialog.Builder(this);
        View habitPopupFavView = getLayoutInflater().inflate(R.layout.habit_popup_fav, null);

        btn_fav = (Button) habitPopupFavView.findViewById(R.id.btn_fav);
        btn_tag = (Button) habitPopupFavView.findViewById(R.id.btn_tag);
        newHabitTag = (EditText) habitPopupFavView.findViewById(R.id.HabitTag);





        dialogBuilder.setView(habitPopupFavView);



        btn_fav.setOnClickListener(new View.OnClickListener() {
            Habits habits;
            @Override
            public void onClick(View v) {

                tagFilter = "Fav=True";

                //close dialog
                dialog.dismiss();
            }
        });

        btn_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tagFilter = "tag="+ newHabitTag.getText().toString();

                //cancel and close dialog
                dialog.dismiss();
            }
        });


        dialog = dialogBuilder.create();
        dialog.show();


    }

    /**
     *
     * @param habitHelper
     * Update the activity to show latest version of habit list
     */
    private void ShowAllOnListView(HabitHelper habitHelper) {

        myArrayAdapter = new ArrayAdapter<Habits>(HabitActivity.this, android.R.layout.simple_list_item_1, habitHelper.getAll(habitList));
        myListView.setAdapter(myArrayAdapter);
    }

    /**
     * perform a get request for haibts
     */
    public void getReq(){
        ServerRequest serverRequest = new ServerRequest();
        HabitLogic habitLogic = new HabitLogic(this,serverRequest);
        tryGetReqm(habitLogic);
        habitLogic.updateHabit("GET",null);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public JSONObject postReqStat(String s) throws JSONException {

        ServerRequest serverRequest = new ServerRequest();
        HabitStatPostLogic hsl = new HabitStatPostLogic(HabitActivity.this,serverRequest);
        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDateTime now = LocalDateTime.now();
        String mytime = "s" + dtf.format(now);
        String value = jsonObject.get(mytime).toString();
        jsonObject.remove(mytime);
        JSONObject object = new JSONObject(jsonObject.toString());
        object.put(mytime,hsl.updateStat(value));



        hsl.updateHabitStat("POST",object);


        return object;
    }


    String tryGetReqm(HabitLogic logic){
        return logic.updateHabit("GET",null);


    }


    /**
     *
     * @param habits habit to post
     * Create a post request to add new habits
     */
    private void postReq(Habits habits) throws JSONException, ParseException {


        habits = new Habits(newHabitPopup.getText().toString());
        Toast.makeText(HabitActivity.this,"Hello",Toast.LENGTH_SHORT);
        HabitHelper habitHelper = new HabitHelper(HabitActivity.this);

        boolean success = habitHelper.addOne(habits);
        ShowAllOnListView(habitHelper);

        //IN order to add a habit i should need to create a new habit object getting name from text.
        Habits habitSend = new Habits(newHabitPopup.getText().toString(),LoginActivity.username,habitList.size()+3,newHabitTag.getText().toString(),favoriteBox.isChecked(),freq);

        newHabitPopup.setText("");
        newHabitTag.setText("");
        //add the new habit to the habit array list and update..
        habitList.add(habitSend);
        //now create new json and send post
        //AT this point wed want to recompile everything into the json but rn we're just working with habits and userfile



        final String habitJsonSend = gson.toJson(habitList);
        currFile.setHabits(habitList.toString());
        final String currFileJsonSend = gson.toJson(currFile);
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
    /**
     *
     * Create a post request to delete new habits
     */
    private void postReqDel() throws JSONException {
        HabitHelper habitHelper = new HabitHelper(HabitActivity.this);
        ShowAllOnListView(habitHelper);

        newHabitPopup.setText("");
        newHabitTag.setText("");
        //add the new habit to the habit array list and update..

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void showText(int n,String s) throws JSONException {





        if(n == 0){
            getResponse = s;
            currFile =  gson.fromJson(getResponse,UserFile.class);
            Type habitArrayType = new TypeToken<ArrayList<Habits>>(){}.getType(); //IMPORTANT: this prevents type erasure of the Habit object arraylist at runtime
            habitList = gson.fromJson(currFile.getHabits(), habitArrayType);
            //start integrated database

            final HabitHelper habitHelper = new HabitHelper(HabitActivity.this);

            ShowAllOnListView(habitHelper);
        }
        else if(n == 1){
            JSONObject myobject = postReqStat(s);

        }
        else{





        }



    }

    /**
     * Configures the ability to complete habits properly
     */
    public void setComplete(){

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemNum = position;
                Habits toComplete = (Habits) parent.getItemAtPosition(position);
                Toast.makeText(HabitActivity.this,"You click - "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();

                completeHabitDialog(toComplete,position);

            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemNum = position;
                Habits toEdit = (Habits) parent.getItemAtPosition(position);
                editHabitDialog(toEdit,position);

                return true;
            }
        });
    }

    /**
     * configures the search bar to work properly
     */
    public void setSearch() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                HabitActivity.this.myArrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                HabitActivity.this.myArrayAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    /**
     * Sets up filters to be used on current habit list being used
     */
    public void setFilters(){
        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sun = findViewById(R.id.sun);
        fav =findViewById(R.id.fav);


        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Mon");

            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Tue");

            }
        });

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Wed");

            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Thu");

            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Fri");

            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Sat");

            }
        });

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitActivity.this.myArrayAdapter.getFilter().filter("Due=Sun");


            }
        });

        fav.setOnLongClickListener(new Button.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //open dialog to configure the fav button
                editFavoriteFilterDialog();
                //Option to use either as fav button or tag button
                HabitActivity.this.myArrayAdapter.getFilter().filter(tagFilter);

                return false;
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HabitActivity.this.myArrayAdapter.getFilter().filter(tagFilter);


            }
        });


    }


    @Override
    public void toastText(String s) {

    }



    @Override
    public void switchActivity(int n) {

    }


    public void toStat(View view){
        Intent in = new Intent(HabitActivity.this,StatisticActivity.class);
        startActivity(in);
    }
    public void toToDo(View view){
        Intent in = new Intent(HabitActivity.this,homeActivity.class);
        startActivity(in);
    }
    public void toSkill(View view){
        Intent in = new Intent(HabitActivity.this,SkillActivity.class);
        startActivity(in);
    }
    public void toProject(View view){
        Intent in = new Intent(HabitActivity.this,ProjectActivity.class);
        startActivity(in);
    }
    public void toSetting(View view){
        Intent in = new Intent(HabitActivity.this,SettingActivity.class);
        startActivity(in);
    }

}
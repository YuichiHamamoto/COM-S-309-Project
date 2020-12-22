package com.example.habit_tracker;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: Make a post request class that is reusable for the delete function
public class MainActivity extends AppCompatActivity {

    Button viewItemBtn;
    EditText itemText;
    ListView myListView;
    Button add;
    ArrayAdapter myArrayAdapter;

    String urlPost ="http://coms-309-rp-07.cs.iastate.edu:8080/demo/api/files/newfiles";


    String json = "";
    Gson gson = new Gson();

    TextView textView;
    ArrayList<Habits> habitList;
    ArrayList<Habits>habitsArrayList = new ArrayList<>();
    UserFile currFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReq();


    }
    private void ShowAllOnListView(HabitHelper habitHelper) {
        myArrayAdapter = new ArrayAdapter<Habits>(MainActivity.this, android.R.layout.simple_list_item_1, habitHelper.getAll(habitList));
        myListView.setAdapter(myArrayAdapter);
    }

    private void getReq(){

        textView = findViewById(R.id.text);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://coms-309-rp-07.cs.iastate.edu:8080/demo/api/files/cow";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //within the response we need to populate the whatever the heck.
                        textView.setText("Response is: "+ response);
                        json = response;
                        currFile =  gson.fromJson(json,UserFile.class);
                        //if we have a list with 2 items this will work...
                        //so we have 2 null habits as placeholders than are unaccessable by default. on creation of user file
                        habitList = gson.fromJson(currFile.getHabits(), ArrayList.class);


                        textView.setText(currFile.getHabits());



                        //start integrated database
                        viewItemBtn = findViewById(R.id.addItemBtn);
                        itemText = findViewById(R.id.itemText);
                        add = findViewById(R.id.add);
                        myListView = findViewById(R.id.myListView);

                        final HabitHelper habitHelper = new HabitHelper(MainActivity.this);


                        ShowAllOnListView(habitHelper);

                        viewItemBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                HabitHelper habitHelper = new HabitHelper(MainActivity.this);
                                List<Habits> all = habitHelper.getAll(habitList);

                                ShowAllOnListView(habitHelper);

                            }
                        });

                        add.setOnClickListener(new View.OnClickListener() {
                            Habits habits;
                            @Override
                            public void onClick(View view) {
                                postReq(habits);
                            }
                        });


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                textView.setText(error.getMessage());

            }

        });


        queue.add(stringRequest);



    }


    private void postReq(Habits habits){
        habits = new Habits(itemText.getText().toString());
        Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT);
        HabitHelper habitHelper = new HabitHelper(MainActivity.this);

        boolean success = habitHelper.addOne(habits);
        ShowAllOnListView(habitHelper);

        Toast.makeText(MainActivity.this,"Success "+ success,Toast.LENGTH_SHORT).show();

        //TODO: need to send a post on add with what is being added
        //IN order to add a habit i should need to create a new habit object getting name from text.
        Habits habitSend = new Habits(itemText.getText().toString());
        itemText.setText("");
        //add the new habit to the habit array list and update..
        habitList.add(habitSend);
        //now create new json and send post
        //AT this point wed want to recompile everything into the json but rn we're just working with habits and userfile
        final String habitJsonSend = gson.toJson(habitList);

        //test json to send
        final String testPost = "{\"username\":\"cow\",\"projects\":\"{\\\"name\\\": \\\"myPythonProject\\\", \\\"startDate\\\": \\\"Sep 14, 2020 4:50:01 PM\\\"}\",\"skills\":\"{\\\"groupTag\\\": \\\"languages\\\", \\\"skillTag\\\": \\\"python\\\", \\\"resources\\\": [{\\\"link\\\": \\\"codingbat.com\\\", \\\"name\\\": \\\"codepython\\\"}]}\",\"habits\":\"{\\\"name\\\": \\\"die twice a day\\\"}\",\"settings\":\"{\\\"theme\\\": \\\"darktheme\\\"}\"}";

        textView.setText(habitJsonSend);
        //start post request
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username","cow");
            jsonObject.put("habits",habitJsonSend);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, urlPost,jsonObject,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){


                    }
                }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                //send error or something
                textView.setText(error.getMessage());

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String, String>();
                params.put("username","cow");

                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        // then send post request with the new habit..

        requestQueue.add(jsonObjReq);
    }



}
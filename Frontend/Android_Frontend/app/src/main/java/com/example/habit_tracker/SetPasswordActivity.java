package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class SetPasswordActivity extends AppCompatActivity {
    Button btn_update,btn_signup;
    TextView tv_user, tv_password, tv_passVer, tv_alert, tv_sugg;
    EditText et_user,et_password,et_passVer;
    String username, password,passVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        getSupportActionBar().setTitle("Set Password");

        btn_update = findViewById(R.id.buttonUpPass);
        btn_signup = findViewById(R.id.buttonSignUp);

        tv_user = findViewById(R.id.textViewUsername);
        tv_password = findViewById(R.id.textViewPassword);
        tv_passVer = findViewById(R.id.textViewPasswordVer);
        tv_alert = findViewById(R.id.textViewAlert);
        tv_sugg = findViewById(R.id.textViewSugg);

        et_user = findViewById(R.id.editTextUsername);
        et_password = findViewById(R.id.editTextPassword);
        et_passVer = findViewById(R.id.editTextPasswordVer);
    }

    /**
     * Jump to Sign Up view
     * @param view
     */
    public void goToSignUp(View view){
        Intent in = new Intent(SetPasswordActivity.this, SignUpActivity.class);
        startActivity(in);
    }

    /**
     * Validate the format of password
     * @param str
     * Given string
     * @return
     * Indicates if it is valid
     */
    public boolean isValid(String str){
        if(str.length()<8){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Update password
     * @param View
     * Given view
     */
    public void updatePass(View View){
        username = et_user.getText().toString();
        password = et_password.getText().toString();
        passVer = et_passVer.getText().toString();

        //Create JsonObject with given information
        final JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username",username);
            jsonObject.put("password",password);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //Check if given username is null
        if(username.equals("")){
            tv_alert.setText("Fill out username");
        }
        //Check if given password is null
        else if(password.equals("")||passVer.equals("")){
            tv_alert.setText("Fill out password");
        }
        //Check if the password match
        else if (password.equals(passVer)==false){
            tv_alert.setText("The password doesn't match");
        }
        else if(!isValid(password)){
            tv_alert.setText("The password has to be at least 8 characters");
        }
        else{
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="http://10.24.226.239:8080/demo/api/login/";
            url += username;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {

                        //This means that the username is taken.
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("null")) {
                                tv_alert.setText(username + " doesn't exists");
                            }
                            else{
                                String url ="http://10.24.226.239:8080/demo/api/login/addUser";

                                RequestQueue requestQueue=Volley.newRequestQueue(SetPasswordActivity.this);
                                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url,jsonObject,
                                        new Response.Listener<JSONObject>(){
                                            @Override
                                            public void onResponse(JSONObject response){



                                            }}, new Response.ErrorListener()

                                {
                                    @Override
                                    public void onErrorResponse (VolleyError error){
                                        //send error or something
                                        tv_alert.setText(error.getMessage());

                                    }
                                }){
                                    @Override
                                    protected Map<String,String> getParams(){
                                        Map<String,String> params=new HashMap<String, String>();
                                        params.put("username",username);
                                        params.put("password",password);
                                        return params;
                                    }
                                };

                                // then send post request with the new habit..


                                requestQueue.add(jsonObjReq);

                                Intent in = new Intent(SetPasswordActivity.this, LoginActivity.class);
                                startActivity(in);
                            }
                        }
                    }, new Response.ErrorListener() {

                //This means that go ahead to post
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });

            queue.add(stringRequest);
        }
    }

}
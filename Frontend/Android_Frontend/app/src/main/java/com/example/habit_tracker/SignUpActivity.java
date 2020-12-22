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
import com.example.habit_tracker.Logic.InitializeUserLogic;
import com.example.habit_tracker.Logic.SignUpLogic;
import com.example.habit_tracker.Network.ServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class SignUpActivity extends AppCompatActivity implements IView {

    Button btn_signup, btn_login;
    EditText et_user, et_password, et_passVer,et_email;
    TextView tv_user,tv_password, tv_passVer, tv_alert;
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");

        btn_login = findViewById(R.id.buttonLogin);
        btn_signup = findViewById(R.id.buttonSignUp);

        et_user = findViewById(R.id.editTextUsername);
        et_password = findViewById(R.id.editTextPassword1);
        et_passVer = findViewById(R.id.editTextPassword2);
        et_email = findViewById(R.id.editTextEmailAddress);

        tv_user = findViewById(R.id.textViewUsername);
        tv_password = findViewById(R.id.textViewPassword);
        tv_passVer = findViewById(R.id.textViewPasswordVer);
        tv_alert = findViewById(R.id.textViewAlert);
    }

    /**
     * Jump back to Login view
     * @param view
     * Given view
     */
    public void backToLogin(View view){
        Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(in);
    }

    /**
     * Let user sign up for a new account
     * @param View
     * Given view
     */
    public void signUp(View View) throws JSONException {
        ServerRequest serverRequest = new ServerRequest();
        SignUpLogic logic = new SignUpLogic(this, serverRequest);

        String username = et_user.getText().toString();
        String password = et_password.getText().toString();
        String passwordVer = et_passVer.getText().toString();
        String email = et_email.getText().toString();

        trySignUp(username,password,passwordVer,email,logic);
    }

    /**
     * Support method of signUp()
     * @param username
     * Given username
     * @param password
     * Given password
     * @param passwordVer
     * Given password to verify the password
     * @param email
     * Given email
     * @param logic
     * Given logic
     * @return
     * Indicates is the user successfully signed up
     */

    public boolean trySignUp(String username, String password, String passwordVer, String email, SignUpLogic logic)throws JSONException{
        return  logic.signup(username,password,passwordVer,email);

    }

    /**
     * Validate the password format
     * @param str
     * Given string
     * @return
     * Indicate if the password is valid or not
     */
    public boolean isValid(String str){
        if(str.length() <8){
            return false;
        }
        return true;
    }

    public void InitializeUser(){
        ServerRequest serverRequest = new ServerRequest();
        InitializeUserLogic logic = new InitializeUserLogic(this,serverRequest);
        tryInitializeUser(this.username,logic);
    }

    public boolean tryInitializeUser(String username,InitializeUserLogic logic){
        return logic.initializeUser(username);
    }


    @Override
    public void showText(int n,String s) {
        if(n==1){
            this.username =s;
        }
        tv_alert.setText(s);
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {
        InitializeUser();
        Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(in);
    }
}
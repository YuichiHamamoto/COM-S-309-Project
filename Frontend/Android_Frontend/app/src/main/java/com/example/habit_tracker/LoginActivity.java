package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.habit_tracker.Logic.LoginLogic;
import com.example.habit_tracker.Network.ServerRequest;

import org.json.JSONException;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class LoginActivity extends AppCompatActivity implements IView{

    public static String username = "";
    public static String email = "";
    LoginLogic logic;
    public static Boolean flag = true;

    Button btn_login, btn_singup, btn_password;
    EditText et_username, et_password;
    TextView tv_username, tv_password,tv_alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getSupportActionBar().setTitle("Login");

        btn_login = findViewById(R.id.buttonLogin);
        btn_singup = findViewById(R.id.buttonSingUp);
        btn_password = findViewById(R.id.buttonSetPassword);
        et_username = findViewById(R.id.editTextUserName);
        et_password = findViewById(R.id.editTextTextPassword);
        tv_username = findViewById(R.id.TextViewUsername);
        tv_password = findViewById(R.id.TextViewPassword);
        tv_alert = findViewById(R.id.textViewAlert);




    }

    /**
     * Let user login
     * @param view
     * Given view
     * @throws JSONException
     * @throws InterruptedException
     */
    public void login(View view) throws JSONException, InterruptedException {

        username = et_username.getText().toString();
        String password = et_password.getText().toString();
        ServerRequest serverRequest = new ServerRequest();
        logic = new LoginLogic(this, serverRequest);
        tryLogin(username,password,logic);


    }

    /**
     * Support methof of login()
     * @param username
     * Given username
     * @param password
     * Given password
     * @param logic
     * Given logic
     * @return
     * Indicated if the user successfully logged in
     * @throws JSONException
     */
    public boolean tryLogin(String username, String password, LoginLogic logic) throws JSONException {
        return logic.login(username,password);
    }

    @Override
    public void switchActivity(int n){
        Intent in = new Intent(LoginActivity.this, homeActivity.class);
        startActivity(in);

    }



    /**
     * Jump to Sign Up view
     * @param view
     */
    public void moveToSignup(View view){
        Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Set Password view
     * @param view
     */
    public void moveToSetPassword(View view){
        Intent in = new Intent(LoginActivity.this, SetPasswordActivity.class);
        startActivity(in);
    }
    @Override
    public void showText(int n,String s) {
        tv_alert.setText(s);
        tv_alert.setVisibility(View.VISIBLE);
    }

    @Override
    public void toastText(String s) {
    }



}
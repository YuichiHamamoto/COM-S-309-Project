package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.habit_tracker.Logic.ChangePasswordLogic;
import com.example.habit_tracker.Logic.SetEmailLogic;
import com.example.habit_tracker.Logic.VertifyPasswordLogic;
import com.example.habit_tracker.Network.ServerRequest;

import org.json.JSONException;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class AccountActivity extends AppCompatActivity implements IView {
    TextView tv_alert,tv_message;
    EditText et_email,et_emailVer,et_password;
    Button bt_ver,bt_apply;
    boolean isPasswordVerified = false;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Setting");

        tv_alert = findViewById(R.id.textViewAlert);
        tv_message = findViewById(R.id.textViewMessage);
        et_email = findViewById(R.id.editTextEmailAddress);
        et_emailVer = findViewById(R.id.editTextEmailAddressVer);
        et_password = findViewById(R.id.editTextPassword);
        bt_ver = findViewById(R.id.buttonVerify);
        bt_apply = findViewById(R.id.buttonApply);

    }

    /**
     * On click method that verify the user's password
     * @param view
     */
    public void verifyPassword(View view){
        ServerRequest serverRequest = new ServerRequest();
        VertifyPasswordLogic logic = new VertifyPasswordLogic(this, serverRequest);

        password = et_password.getText().toString();
        tryVerifyPassword(password,logic);
    }

    /**
     * Support method of verifyPassword()
     * @param password
     * Given password
     * @param logic
     * Given logic
     * @return
     * Indicates if password is verified
     */
    public boolean tryVerifyPassword(String password, VertifyPasswordLogic logic){
        return logic.vertifyPassword(password);
    }

    /**
     * Set user's email address if their password is verified
     * @param view
     * Given view
     */
    public void setEmail(View view) throws JSONException {
        if(isPasswordVerified){
            ServerRequest serverRequest = new ServerRequest();
            SetEmailLogic logic2 = new SetEmailLogic(this, serverRequest);
            String newEmail = et_email.getText().toString();
            String verEmail = et_emailVer.getText().toString();
            trySetEmail(newEmail,verEmail,password,logic2);
        }
    }

    /**
     * Support method of setEmail()
     * @param newEmail
     * Given new email
     * @param verEmail
     * Given email to verify the email
     * @param password
     * Given password
     * @param logic
     * Given logic
     * @return
     * Indicates is the email is set
     */
    public boolean trySetEmail(String newEmail, String verEmail, String password, SetEmailLogic logic) throws JSONException {
        return logic.setEmail(newEmail,verEmail,password);
    }

    @Override
    public void showText(int n,String s) {
        if(s.equals("1")){
            isPasswordVerified=true;
            tv_alert.setText("Success");
        }
        else if(s.equals("2")){
            tv_alert.setText("Wrong password Entered");
        }
        else if(s.equals("3")) {
            isPasswordVerified = false;
            tv_alert.setText("");
            tv_message.setText("Successfully Updated Your Email Address");
        }
        else{
            tv_message.setText(s);
            tv_message.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}
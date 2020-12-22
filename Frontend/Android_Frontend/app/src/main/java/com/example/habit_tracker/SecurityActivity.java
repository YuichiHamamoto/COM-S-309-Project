package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.habit_tracker.Logic.ChangePasswordLogic;
import com.example.habit_tracker.Logic.NotificationLogic.CheckNotificationSettingLogic;
import com.example.habit_tracker.Logic.NotificationLogic.SetNotificationSettingLogic;
import com.example.habit_tracker.Logic.VertifyPasswordLogic;
import com.example.habit_tracker.Network.ServerRequest;

import org.json.JSONException;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class SecurityActivity extends AppCompatActivity implements IView{

    EditText et_curPassword,et_newPassword,et_verPassword;
    Button bt_update;
    TextView tv_alert,tv_ver;
    Boolean isPasswordCorrect = false;
    SwitchCompat switchCompat;
    CheckNotificationSettingLogic checkLogic;
    SetNotificationSettingLogic setLogic;
    Boolean isNotificationOn = false;
    String ufile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        getSupportActionBar().setTitle("Security");
        checkNotification();

        bt_update = findViewById(R.id.button);
        et_curPassword = findViewById(R.id.editTextCurPassword);
        et_newPassword = findViewById(R.id.editTextNewPassword);
        et_verPassword = findViewById(R.id.editTextVerPassword);
        tv_alert = findViewById(R.id.textViewAlert);
        tv_ver = findViewById(R.id.textViewVer);
        switchCompat = findViewById(R.id.switch1);


    }

    public void checkNotification(){
        ServerRequest serverRequest = new ServerRequest();
        checkLogic = new CheckNotificationSettingLogic(this, serverRequest);
        tryCheckNotification(LoginActivity.username,checkLogic);
    }

    public boolean tryCheckNotification(String username,CheckNotificationSettingLogic logic){
        return logic.checkNotificationSetting(username);
    }

    public void setNotification(boolean onoff) throws JSONException {
        ServerRequest serverRequest = new ServerRequest();
        setLogic = new SetNotificationSettingLogic(this,serverRequest);
        trySetNotification(setLogic,onoff);
    }

    public boolean trySetNotification(SetNotificationSettingLogic logic,boolean onoff) throws JSONException {
        return logic.setNotificationLogic(ufile,onoff);
    }
    public void switchCheck(View view) throws JSONException {
        if( switchCompat.isChecked()){
            setNotification(true);
        }
        else{
            setNotification(false);
        }

    }

    /**
     * Check user's password
     * @param view
     * Given view
     */
    public void checkPassword(View view){
        ServerRequest serverRequest = new ServerRequest();
        VertifyPasswordLogic logic = new VertifyPasswordLogic(this, serverRequest);

        String curPassword = et_curPassword.getText().toString();
        tryCheckPassword(curPassword,logic);
    }

    /**
     *  Support method of checkPassword()
     * @param password
     * Given password
     * @param logic
     * Given logic
     * @return
     * Indicates if the password is successfully checked
     */
    public boolean tryCheckPassword(String password,VertifyPasswordLogic logic){
        return logic.vertifyPassword(password);
    }

    /**
     * Update user's password
     * @param view
     * Given view
     */
    public void updatePassword(View view) throws JSONException {
        if(isPasswordCorrect){
            ServerRequest serverRequest = new ServerRequest();
            ChangePasswordLogic logic2 = new ChangePasswordLogic(this, serverRequest);
            String newPassword = et_newPassword.getText().toString();
            String verPassword = et_verPassword.getText().toString();
            tryUpdatePassword(newPassword,verPassword,logic2);
        }
    }

    /**
     * Support method of updatePassword()
     * @param password
     * Given password
     * @param verPassword
     * Given password to verify the password
     * @param logic
     * Given logic
     * @return
     * Indicates if the password is successfully updated
     */
    public boolean tryUpdatePassword(String password,String verPassword, ChangePasswordLogic logic) throws JSONException {
        return logic.changePassword(password,verPassword);
    }

    @Override
    public void showText(int n,String s) {

        if(n==0){
            tv_alert.setText(s);
            tv_alert.setVisibility(View.VISIBLE);
        }
       else if(n==1){
            isPasswordCorrect=true;
            tv_ver.setText("Success");
        }
        else if(n==2){
            tv_ver.setText("Wrong Password Entered");
        }
        else if(n==3) {
            isPasswordCorrect = false;
            tv_ver.setText("");
            tv_alert.setText("Successfully Updated Your Password");
        }
        else if(n==4){
            ufile = s;
            String [] parts = s.split(",");
            String setting = parts[parts.length-1];
            parts = setting.split(":");
            String onoff = parts[2].substring(3,5);
            if(onoff.equals("on")){
                isNotificationOn = true;
                switchCompat.setChecked(isNotificationOn);
            }
        }
    }

    @Override
    public void toastText(String s) {

    }

    @Override
    public void switchActivity(int n) {

    }
}
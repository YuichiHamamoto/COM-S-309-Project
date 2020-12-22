package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class SettingActivity extends AppCompatActivity {

    Button bt_notif,bt_secu, bt_acco,bt_conta, bt_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setTitle("Setting");

        bt_notif = findViewById(R.id.buttonNotif);
        bt_secu = findViewById(R.id.buttonSecu);
        bt_acco = findViewById(R.id.buttonAcco);
        bt_conta = findViewById(R.id.buttonConta);
        bt_about = findViewById(R.id.buttonAbout);



    }

    /**
     * Jump to Security view
     * @param veiw
     * Given view
     */
    public void toSecurity(View veiw){
        Intent in = new Intent(SettingActivity.this,SecurityActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Notification view
     * @param view
     * Given view
     */
    public void toNotification(View view){
        Intent in = new Intent(SettingActivity.this,NotificationActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Account view
     * @param view
     */
    public void toAccount(View view){
        Intent in = new Intent(SettingActivity.this,AccountActivity.class);
        startActivity(in);
    }

    /**
     * Jump to to-do view
     * @param view
     */
    public void toToDo(View view){
        Intent in = new Intent(SettingActivity.this,homeActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Habit view
     * @param view
     */
    public void toHabit(View view){
        Intent in = new Intent(SettingActivity.this,HabitActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Stat view
     * @param view
     */
    public void toStat(View view){
        Intent in = new Intent(SettingActivity.this,StatisticActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Project View
     * @param view
     */
    public void toProject(View view){
        Intent in = new Intent(SettingActivity.this,ProjectActivity.class);
        startActivity(in);
    }

    /**
     * Jump to Skill view
     * @param view
     */
    public void toSkill(View view){
        Intent in = new Intent(SettingActivity.this,SkillActivity.class);
        startActivity(in);
    }
}
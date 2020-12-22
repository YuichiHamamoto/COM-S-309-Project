package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setTitle("Notification");
    }
}
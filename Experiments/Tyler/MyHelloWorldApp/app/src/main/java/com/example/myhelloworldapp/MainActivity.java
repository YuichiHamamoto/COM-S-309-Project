package com.example.myhelloworldapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myhelloworldapp.R;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView thingToSearch = findViewById(R.id.searchBar);
        thingToSearch.setHint("Put your name here");

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setText("Good Job!");

    }


    @SuppressLint("SetTextI18n")
    public void onClickGoogle (View view){

        TextView thingToSearch = findViewById(R.id.searchBar);
        TextView changingText = findViewById(R.id.changingText);
        Button searchButton = findViewById(R.id.searchButton);

        changingText.setText("Hello " + thingToSearch.getText().toString());
    }
}



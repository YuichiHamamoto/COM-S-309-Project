package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void onButtonClick(View view){

        TextView txtHello = findViewById(R.id.Hello);

        EditText editTextName = findViewById(R.id.editTextTextPersonName);
        editTextName.getText().toString();

        txtHello.setText("Hi, "+ editTextName.getText().toString());
    }
}
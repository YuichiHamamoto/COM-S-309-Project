package com.example.resistertest;

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

    public void onButtonClickResister(View view){
        TextView buttonResister = findViewById(R.id.button);
        TextView firstName = findViewById(R.id.textViewFirstName);
        TextView lastName = findViewById(R.id.textViewLastName);
        TextView email = findViewById(R.id.textViewEmail);

        EditText editFistName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editTextLastName);
        EditText editEmail = findViewById(R.id.editTextEmailAddress);

        buttonResister.setText("Resistered");
        firstName.setText(editFistName.getText().toString());
        lastName.setText(editLastName.getText().toString());
        email.setText(editEmail.getText().toString());
    }

}
package com.example.bundlelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,password; //emil and password
    Button loginButton; //loginButton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         name = findViewById(R.id.editTextPersonName);
         password = findViewById(R.id.editTextNumber);
         loginButton = findViewById(R.id.buttonlogin);
    }

    public void movePage1(View view){
        String stEmail = name.getText().toString();
        String stPassword = password.getText().toString();
        if(stEmail.equals("yuichi")&&stPassword.equals("0000")){
            Intent in = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(in);
        }
        else if(stEmail.equals("")||stPassword.equals("")){
            Toast.makeText(getBaseContext(),"Enter Email and Password",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(getBaseContext(),"Wrong Email and Password Are Entered",Toast.LENGTH_SHORT);
        }
    }
}
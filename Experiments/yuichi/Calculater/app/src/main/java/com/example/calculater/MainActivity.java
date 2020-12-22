package com.example.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText num1,num2;
    Button add, sub, pro, div;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.textView);
        num1 = findViewById(R.id.editTextOp1);
        num2 = findViewById(R.id.editTextOp2);
        add = findViewById(R.id.button);
        sub = findViewById(R.id.button2);
        pro = findViewById(R.id.button4);
        div = findViewById(R.id.button5);
    }

    public void addclick(View view){
       int op1 = Integer.parseInt(num1.getText().toString());
       int op2 = Integer.parseInt(num2.getText().toString());
       result.setText(String.valueOf(op1 + op2));
    }

    public void subclick(View view){
        int op1 = Integer.parseInt(num1.getText().toString());
        int op2 = Integer.parseInt(num2.getText().toString());
        result.setText(String.valueOf(op1 - op2));
    }

    public void proclick(View view){
        int op1 = Integer.parseInt(num1.getText().toString());
        int op2 = Integer.parseInt(num2.getText().toString());
        result.setText(String.valueOf(op1 * op2));
    }

    public void divclick(View view){
        int op1 = Integer.parseInt(num1.getText().toString());
        int op2 = Integer.parseInt(num2.getText().toString());
        result.setText(String.valueOf(op1 / op2));
    }

}
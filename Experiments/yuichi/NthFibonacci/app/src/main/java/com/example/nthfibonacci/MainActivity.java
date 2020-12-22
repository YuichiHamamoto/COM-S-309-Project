package com.example.nthfibonacci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView num;
    TextView nth;
    Button button;
    public int a;
     public int b;
     public int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.bottom);
        num = findViewById(R.id.textView);
        nth = findViewById(R.id.textView2);
        a =1;
        b=1;
        count =2;

    }

    public void click(View view){

        num.setText(String.valueOf(a + b));
        String st = count + "th";
        nth.setText(st);
        count+=1;
        int temp = b;
        b = a+b;
        a = temp;
    }
    public void reset(View view){
        a =1;
        b =1;
        count=2;
        num.setText("1");
        nth.setText("1th");
    }
}
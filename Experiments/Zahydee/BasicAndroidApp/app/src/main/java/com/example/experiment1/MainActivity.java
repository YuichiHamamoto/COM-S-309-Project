package com.example.experiment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayName();
    }
    public void displayName()
    {
        TextView tv = (TextView)findViewById(R.id.textView2);
        String text = getString(R.string.text_welcome, name);
        tv.setText(text);
    }

    public void setName(View v)
    {
        EditText et = (EditText)findViewById(R.id.PersonName);
        name = et.getText().toString();
        displayName();
    }
}
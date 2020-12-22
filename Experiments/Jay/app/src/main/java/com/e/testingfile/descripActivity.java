package com.e.testingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class descripActivity extends AppCompatActivity {
    String[] objdesc;
    String[] obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip);
        Resources r = getResources();
        objdesc = r.getStringArray(R.array.objdesc);
        obj = r.getStringArray(R.array.obj);
        itemAdap ia = new itemAdap(this, obj);
        TextView textview2 = (TextView) findViewById(R.id.textview2);
    }
}
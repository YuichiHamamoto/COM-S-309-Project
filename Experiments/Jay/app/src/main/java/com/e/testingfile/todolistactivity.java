package com.e.testingfile;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class todolistactivity extends AppCompatActivity {
    ListView myListView;
    String[] obj;
    String[] objdesc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);
        Resources r = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        obj = r.getStringArray(R.array.obj);
        itemAdap ia = new itemAdap(this, obj);
        myListView.setAdapter(ia);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showdescripActivity = new Intent(todolistactivity.this, descripActivity.class);
                startActivity(showdescripActivity);
            }
        });

    }
}

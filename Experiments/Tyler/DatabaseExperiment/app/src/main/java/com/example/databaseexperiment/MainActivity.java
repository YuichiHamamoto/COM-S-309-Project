package com.example.databaseexperiment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button viewItemBtn;
    EditText itemText;
    ListView myListView;
    Button add;
    ArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewItemBtn = findViewById(R.id.addItemBtn);
        itemText = findViewById(R.id.itemText);
        add = findViewById(R.id.add);
        myListView = findViewById(R.id.myListView);

        Listhelper listhelper = new Listhelper(MainActivity.this);


        ShowAllOnListView(listhelper);

        viewItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listhelper listhelper = new Listhelper(MainActivity.this);
                List<ListModel> all = listhelper.getAll();

                ShowAllOnListView(listhelper);

                //Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            ListModel listModel;
            @Override
            public void onClick(View view) {
                        listModel = new ListModel(itemText.getText().toString());
                Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT);
                Listhelper listhelper = new Listhelper(MainActivity.this);

                boolean success = listhelper.addOne(listModel);
                ShowAllOnListView(listhelper);

                Toast.makeText(MainActivity.this,"Success "+ success,Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void ShowAllOnListView(Listhelper listhelper) {
        myArrayAdapter = new ArrayAdapter<ListModel>(MainActivity.this, android.R.layout.simple_list_item_1, listhelper.getAll());
        myListView.setAdapter(myArrayAdapter);
    }
}
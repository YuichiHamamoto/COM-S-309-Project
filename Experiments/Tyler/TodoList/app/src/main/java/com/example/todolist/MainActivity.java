package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    todoAdapter mAdapter;
    private int mAmount = 0;
    private SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoHelper myHelper = new todoHelper(this);
        mDatabase = myHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =new todoAdapter(this,getAllItem());
        recyclerView.setAdapter(mAdapter);

        mEditTextName = findViewById(R.id.itemText);
        mTextViewAmount = findViewById(R.id.counter);

        Button buttonIncrease = findViewById(R.id.add);
        Button buttonDecrease = findViewById(R.id.subtract);
        Button buttonAddItem = findViewById(R.id.addItemBtn);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();
            }
        });
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });




        }
    private void increase(){
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));

    }

    private void decrease(){
        if (mAmount > 0){
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));

        }
    }
    private void addItem(){
        if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0){
            return;
        }
        String name = mEditTextName.getText().toString();
        ContentValues cv  = new ContentValues();
        cv.put(todoContract.todoEntry.COLUMN_NAME,name);
        cv.put(todoContract.todoEntry.COLUMN_AMOUNT,mAmount);

        mDatabase.insert(todoContract.todoEntry.TABLE_NAME, null,cv);
        mAdapter.swapCursor(getAllItem());
        mEditTextName.getText().clear();




    }

    private Cursor getAllItem(){
        return mDatabase.query(
                todoContract.todoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                todoContract.todoEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }


    }

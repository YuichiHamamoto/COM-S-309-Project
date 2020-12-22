package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.todolist.todoContract;
public class todoHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todolist.db";
    public static final int DATABASE_VERSION = 1;

    public todoHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TODOLIST_TABLE = "CREATE TABLE " +
                todoContract.todoEntry.TABLE_NAME + " (" +
                todoContract.todoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                todoContract.todoEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                todoContract.todoEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                todoContract.todoEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_TODOLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + todoContract.todoEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

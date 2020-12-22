package com.example.databaseexperiment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Listhelper extends SQLiteOpenHelper {


    public static final String LIST_TABLE = "LIST_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";

    public Listhelper(@Nullable Context context) {
        super(context, "list.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatemnt = "CREATE TABLE " + LIST_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME + ")";

        db.execSQL(createTableStatemnt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(ListModel listModel){
SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME,listModel.getName());
        long insert = db.insert(LIST_TABLE,null,cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;

        }

    }

    public boolean deleteOne(ListModel listModel){

        // TODO: 9/3/2020
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString ="DELETE FROM " + LIST_TABLE + " WHERE " + COLUMN_ITEM_NAME + " = " + listModel.getName();
        Cursor cursor = db.rawQuery(queryString,null);

       return false;
    }

    public List<ListModel> getAll(){
        List<ListModel> returnlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + LIST_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int customerID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                ListModel newModel = new ListModel(itemName);
                returnlist.add(newModel);

            }while (cursor.moveToNext());
        }
        else{
            //do not add anything
        }
        cursor.close();
        db.close();

        return returnlist;
    }
}

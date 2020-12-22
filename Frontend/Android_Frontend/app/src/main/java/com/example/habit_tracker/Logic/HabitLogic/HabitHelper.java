package com.example.habit_tracker.Logic.HabitLogic;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tyler O'Hare
 *
 */
public class HabitHelper extends SQLiteOpenHelper {


    public static final String LIST_TABLE = "LIST_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    List<Habits> returnlist = new ArrayList<Habits>();

    public HabitHelper(@Nullable Context context) {
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

    /**
     *
     * @param habits Habit to add to database
     * @return whether add was successful or not
     */
    public boolean addOne(Habits habits){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME,habits.getName());
        long insert = db.insert(LIST_TABLE,null,cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;

        }

    }
    //Delete from database? can probably just have deletion on sync as well
    public boolean deleteOne(Habits habits){

        // TODO:
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString ="DELETE FROM " + LIST_TABLE + " WHERE " + COLUMN_ITEM_NAME + " = " + habits.getName();
        Cursor cursor = db.rawQuery(queryString,null);

        return false;
    }

    /**
     *
     * @param remoteHabits List of habits stored remotely in server
     * @return list of all habits in arraylist form
     */
    public List<Habits> getAll(List<Habits> remoteHabits){
        List<Habits> returnlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + LIST_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        //Adds all the current remote db habits to the view.
        //TODO: I dont think its synced to local db?
//        for (int i = 0;i < remoteHabits.length;i++){
////            remoteHabits[i].setHabitId(i);
//            returnlist.add(remoteHabits[i]);
//
//        }

        returnlist.addAll(remoteHabits);

        if (cursor.moveToFirst()){
            do {


                // Habits newModel = new Habits(); //idk yet

                //this is where we add what we want to make a list of!
                //returnlist.add(newModel);

            }while (cursor.moveToNext());
        }
        else{
            //do not add anything
        }
        cursor.close();
        db.close();

        return returnlist;
    }

//TODO: offline compatibility LATER
//    public List<Habits> getOffline(){
////        List<Habits> returnlist = new ArrayList<>();
//        String queryString = "SELECT * FROM " + LIST_TABLE;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(queryString,null);
//
//        if (cursor.moveToFirst()){
//            do {
//                int customerID = cursor.getInt(0);
//                String itemName = cursor.getString(1);
//                //Habits[] newModel = new Habits(); //idk
//
//                //this is where we add what we want to make a list of!
//                //returnlist.add(newModel);
//
//            }while (cursor.moveToNext());
//        }
//        else{
//            //do not add anything
//        }
//        cursor.close();
//        db.close();
//
//        return returnlist;
//    }


}
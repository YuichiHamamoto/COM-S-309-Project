package com.example.todolist;

import android.provider.BaseColumns;

public class todoContract {

    private todoContract(){}

public static final class todoEntry implements BaseColumns{
    public static final String TABLE_NAME = "todo_list";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIMESTAMP = "timestamp";


}

}

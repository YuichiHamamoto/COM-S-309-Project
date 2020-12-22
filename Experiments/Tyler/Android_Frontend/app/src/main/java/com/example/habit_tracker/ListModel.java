package com.example.habit_tracker;
/**
 This class will hold values of the Habits
 **/
public class ListModel {
    private int value;
    private String name;

    public ListModel(String name){

        this.name = name;

    }

    @Override
    public String toString() {
        return "ListModel{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name){
        this.name = name;


    }


    public String getName(){
        return this.name;

    }


}
package com.spring.backend.app;

import com.google.gson.Gson;

public class Habits {
    public Habits() {}
    public Habits(String name) {
        this.name = name;
    }

    private String name;


    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

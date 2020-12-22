package com.spring.backend.app;

import com.google.gson.Gson;

public class Settings {
    public Settings() {}
    public Settings(String theme) {
        this.theme = theme;
    }
    private String theme;


    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}

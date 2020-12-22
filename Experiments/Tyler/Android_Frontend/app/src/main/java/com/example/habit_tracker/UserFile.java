package com.example.habit_tracker;
import com.google.gson.Gson;
public class UserFile {

    public UserFile(String username, Habits habits) {
        this.username = username;
        //this.projects = projects.toJSON();
        //this.skills = skills.toJSON();
        //this.settings = settings.toJSON();
        this.habits = habits.toJSON();
    }

    public UserFile() {  }

    private String username;
    private String projects;
    private String skills;
    private String habits;

    private String settings;

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

        public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

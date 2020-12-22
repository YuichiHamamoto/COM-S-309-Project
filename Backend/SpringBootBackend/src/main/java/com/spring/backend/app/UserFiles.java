package com.spring.backend.app;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;


@Entity(name="user_files")
public class UserFiles {

    public UserFiles(String username, Projects projects, Skills skills, Settings settings, Habits habits) {
        this.username = username;
        this.projects = projects.toJSON();
        this.skills = skills.toJSON();
        this.settings = settings.toJSON();
        this.habits = habits.toJSON();
    }

    public UserFiles() {  }

    @Id
    @Column
    private String username;

    @Column
    private String projects;

    @Column
    private String skills;

    @Column
    private String habits;

    @Column
    private String settings;

    @ApiModelProperty(value = "string username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty(value = "jsonified Projects Object")
    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    @ApiModelProperty(value = "jsonified Skills Object")
    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @ApiModelProperty(value = "jsonified Habits Object")
    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    @ApiModelProperty(value = "jsonified Settings Object")
    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }
}

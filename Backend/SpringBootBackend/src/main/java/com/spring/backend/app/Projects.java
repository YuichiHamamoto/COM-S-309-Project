package com.spring.backend.app;

import com.google.gson.Gson;

import java.util.Date;

public class Projects {
    public Projects() {};
    public Projects(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    private String name;
    private Date startDate;
    private String VersionControlLink;
    private String[] Skills;
    private String ManagementLink;
    private String Project_Description;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getVersionControlLink() { return VersionControlLink;}

    public void setVersionControlLink(String VersionControlLink) {
        this.VersionControlLink = VersionControlLink;
    }

    public String[] getSkills() {
        return Skills;
    }

    public void setSkills(String[] Skills) {
         this.Skills = Skills.clone();
    }
    public String getManagementLink() {
        return ManagementLink;
    }
    public void setManagementLink(String ManagementLink) {
        this.ManagementLink = ManagementLink;
    }
    public String getProject_Description() {
        return Project_Description;
    }
    public void setProject_Description(String Project_Description) {
        this.Project_Description = Project_Description;
    }


    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

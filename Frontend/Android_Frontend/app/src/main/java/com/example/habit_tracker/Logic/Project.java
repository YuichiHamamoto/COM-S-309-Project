package com.example.habit_tracker.Logic;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.sql.Date;

/**
 *
 * @author Jay Edwards
 */
public class Project {
    @SerializedName("name")
    private String name = "";
    @SerializedName("startDate")
    private Date startDate;
    @SerializedName("VersionControlLink")
    private String VersionControlLink;
    @SerializedName("Skills")
    private String[] Skills;
    @SerializedName("ManagementLink")
    private String ManagementLink;
    @SerializedName("Project_Description")
    private String Project_Description;
public Project(String name, Date startDate) {
    this.name = name;
    this.startDate = startDate;
    //java.util.Date d1 = new java.util.Date();
    //startDate = new java.sql.Date(d1.getTime());
}
public Project(String name, Date startDate, String VersionControlLink, String ManagementLink, String Project_Description) {
    this.name = name;
    this.startDate = startDate;
    this.ManagementLink = ManagementLink;
    this.VersionControlLink = VersionControlLink;
    this.Project_Description = Project_Description;
}
public Project(String name) {
    this.name = name;
}
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
    public void setSkills(String[] Skills) {
        this.Skills = Skills;
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
    public String getVersionControlLink() {
        return VersionControlLink;
    }
    public void setVersionControlLink(String VersionControlLink) {
        this.VersionControlLink = VersionControlLink;
    }
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public String toString() {
        return name  + ", Start Date =" + startDate;
    }


    public String[] getSkills() {
        return Skills;
    }
}

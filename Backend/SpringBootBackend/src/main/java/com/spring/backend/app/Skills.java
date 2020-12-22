package com.spring.backend.app;


import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class Skills {
    private String skillTag;
    private String groupTag;
    private LearningResources[] resources;

    static class LearningResources {
        public String name;
        public String link;

        public LearningResources(String name, String link)
        {
            this.name = name;
            this.link = link;
        }
    }

    public Skills(){

    }

    public Skills(String skillTag, String groupTag, LearningResources[] resources) {
        super();
        this.skillTag = skillTag;
        this.groupTag = groupTag;
        this.resources = resources.clone();
    }


    public String getSkillTag() {
        return this.skillTag;
    }

    public void setSkillTag(String tag) {
        this.skillTag = tag;
    }

    public String getGroupTag() {
        return this.groupTag;
    }

    public void setGroupTag(String tag) {
        this.groupTag = tag;
    }

    public LearningResources[] getResources() {
        return resources;
    }

    public void setResources(LearningResources[] resources) {
        this.resources = resources.clone();
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


//getter and setter methods
}

package com.example.habit_tracker.Logic.SkillsLogic;

import com.google.gson.Gson;

/**
 * Skills Object Class
 */
public class SkillsModel {
    /**
     * The skill tag that will represent this skill.
     */
    private String skillTag;
    /**
     * The group that this skill belongs to.
     */
    private String groupTag;
    /**
     * List of learning Resources associated with this skill.
     */
    private LearningResources[] resources;
    /**
     * List of skills.
     */
    static SkillsModel[] skills_list;

    /**
     * Learning Resources class
     */
    public static class LearningResources {
        public String name;
        public String link;

        /**
         * Constructor for Learning Resources class
         * @param name - name of the resource
         * @param link - url to resource webpage
         */
        public LearningResources(String name, String link)
        {
            this.name = name;
            this.link = link;
        }
    }

    /**
     * Empty Constructor for a Skill.
     */
    public SkillsModel(){

    }

    /**
     * Constructor for Skills with full parameterss
     * @param skillTag  Tag to represent this skill
     * @param groupTag Group this skill belongs to
     * @param resources List of resources associated with this skill
     */
    public SkillsModel(String skillTag, String groupTag, LearningResources[] resources) {
        super();
        this.skillTag = skillTag;
        this.groupTag = groupTag;
        this.resources = resources.clone();
    }

    /**
     * Constructor for Skills with tag and group
     * @param skillTag Tag to represent this skill
     * @param groupTag  Group this skill belongs to
     */
    public SkillsModel(String skillTag, String groupTag) {
        super();
        this.skillTag = skillTag;
        this.groupTag = groupTag;
    }

    /**
     * Get skill tag
     * @return skill tag
     */
    public String getSkillTag() {
        return this.skillTag;
    }

    /**
     * Set skill tag
     * @param tag tag name to set
     */
    public void setSkillTag(String tag) {
        this.skillTag = tag;
    }

    /**
     * Get group tag
     * @return group tag
     */
    public String getGroupTag() {
        return this.groupTag;
    }

    /**
     * Set Group Tag
     * @param tag group tag to set
     */
    public void setGroupTag(String tag) {
        this.groupTag = tag;
    }

    /**
     * Return List of learning resources
     * @return list of LearningResources
     */
    public LearningResources[] getResources() {
        return resources;
    }

    /**
     * Set List of learning resources
     * @param resources list of learning resources
     */
    public void setResources(LearningResources[] resources) {
        this.resources = resources.clone();
    }

    /**
     * Convert to json string
     * @return jsonified Skills Object
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(skills_list);
    }
}

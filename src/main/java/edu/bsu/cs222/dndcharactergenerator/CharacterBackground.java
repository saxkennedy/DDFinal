package edu.bsu.cs222.dndcharactergenerator;

public class CharacterBackground {
    private int index;
    private String description;
    private String feature;
    private String viewName;

    public String getBgSkill1() {
        return bgSkill1;
    }

    public void setBgSkill1(String bgSkill1) {
        this.bgSkill1 = bgSkill1;
    }

    public String getBgSkill2() {
        return bgSkill2;
    }

    public void getBgSkill2(String getBgSkill2) {
        this.bgSkill2 = bgSkill2;
    }

    private String bgSkill1;
    private String bgSkill2;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}

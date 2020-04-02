package edu.bsu.cs222.dndcharactergenerator;

public enum Skill {
    ACROBATICS(true,"Acrobatics",AbilityScoreModifier.DEX_MOD,"Acrobatics","Check Box 23"),
    ANIMALHANDLING(true,"Animal Handling",AbilityScoreModifier.WIS_MOD,"Animal","Check Box 24"),
    ARCANA(false,"Arcana",AbilityScoreModifier.INT_MOD,"Arcana","Check Box 25"),
    ATHLETICS(true,"Athletics",AbilityScoreModifier.STR_MOD,"Athletics","Check Box 26"),
    DECEPTION(false,"Deception",AbilityScoreModifier.CHA_MOD,"Deception ","Check Box 27"),
    HISTORY(true,"History",AbilityScoreModifier.INT_MOD,"History ","Check Box 28"),
    INSIGHT(true,"Insight",AbilityScoreModifier.WIS_MOD,"Insight","Check Box 29"),
    INTIMIDATION(true,"Intimidation",AbilityScoreModifier.CHA_MOD,"Intimidation","Check Box 30"),
    INVESTIGATION(false,"Investigation",AbilityScoreModifier.INT_MOD,"Investigation ","Check Box 31"),
    MEDICINE(false,"Medicine",AbilityScoreModifier.WIS_MOD,"Medicine","Check Box 32"),
    NATURE(false,"Nature",AbilityScoreModifier.INT_MOD,"Nature","Check Box 33"),
    PERCEPTION(true,"Perception",AbilityScoreModifier.WIS_MOD,"Perception ","Check Box 34"),
    PERFORMANCE(false,"Performance",AbilityScoreModifier.CHA_MOD,"Performance","Check Box 35"),
    PERSUASION(false,"Persuasion",AbilityScoreModifier.CHA_MOD,"Persuasion","Check Box 36"),
    RELIGION(false,"Religion",AbilityScoreModifier.INT_MOD,"Religion","Check Box 37"),
    SLEIGHTOFHAND(false, "Sleight of Hand",AbilityScoreModifier.DEX_MOD,"SleightofHand","Check Box 38"),
    STEALTH(false,"Stealth",AbilityScoreModifier.DEX_MOD,"Stealth ","Check Box 39"),
    SURVIVAL(true,"Survival",AbilityScoreModifier.WIS_MOD,"Survival","Check Box 40");


    public final String viewName;
    public final boolean isFighterOption;
    public final AbilityScoreModifier abilityScoreModifier;
    public final String pdfStringName;
    public final String checkBoxIdentifier;

    Skill(boolean isFighterOption, String viewName, AbilityScoreModifier abilityScoreModifier, String pdfStringName, String checkBoxIdentifier){
        this.abilityScoreModifier =abilityScoreModifier;
        this.viewName=viewName;
        this.isFighterOption=isFighterOption;
        this.pdfStringName = pdfStringName;
        this.checkBoxIdentifier =checkBoxIdentifier;
    }
    private void putModifier(){
    }

}

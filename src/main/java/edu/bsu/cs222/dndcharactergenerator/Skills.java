package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public enum Skills{
    ACROBATICS(true,"Acrobatics",AbilityScoreModifier.DEX_MOD),
    ANIMALHANDLING(true,"Animal Handling",AbilityScoreModifier.WIS_MOD),
    ARCANA(false,"Arcana",AbilityScoreModifier.INT_MOD),
    ATHLETICS(true,"Athletics",AbilityScoreModifier.STR_MOD),
    DECEPTION(false,"Deception",AbilityScoreModifier.CHA_MOD),
    HISTORY(true,"History",AbilityScoreModifier.INT_MOD),
    INSIGHT(true,"Insight",AbilityScoreModifier.WIS_MOD),
    INTIMIDATION(true,"Intimidation",AbilityScoreModifier.CHA_MOD),
    INVESTIGATION(false,"Investigation",AbilityScoreModifier.INT_MOD),
    MEDICINE(false,"Medicine",AbilityScoreModifier.WIS_MOD),
    NATURE(false,"Nature",AbilityScoreModifier.INT_MOD),
    PERCEPTION(true,"Perception",AbilityScoreModifier.WIS_MOD),
    PERFORMANCE(false,"Performance",AbilityScoreModifier.CHA_MOD),
    PERSUASION(false,"Persuasion",AbilityScoreModifier.CHA_MOD),
    RELIGION(false,"Religion",AbilityScoreModifier.INT_MOD),
    SLEIGHTOFHAND(false, "Sleight of Hand",AbilityScoreModifier.DEX_MOD),
    STEALTH(false,"Stealth",AbilityScoreModifier.DEX_MOD),
    SURVIVAL(true,"Survival",AbilityScoreModifier.WIS_MOD);


    public final String viewName;
    public final boolean isFighterOption;
    public final AbilityScoreModifier abilityScoreModifier;

    Skills(boolean isFighterOption,String viewName, AbilityScoreModifier abilityScoreModifier){
        this.abilityScoreModifier =abilityScoreModifier;
        this.viewName=viewName;
        this.isFighterOption=isFighterOption;
    }
    private void putModifier(){
    }

}

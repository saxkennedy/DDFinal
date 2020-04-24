package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class Character {
    private String name;
    private String style;
    private Race race;
    private Subrace subrace;
    private Map<CharacterAttribute, Integer> attributeMap = new HashMap<>();

    Integer[] statNumbers = new Integer[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
    Skill backgroundSkill1;
    Skill backgroundSkill2;
    Skill fighterSkill1;
    Skill fighterSkill2;

    int proficiencyViaFighterLevel = 2;

    public Map<CharacterAttribute, Integer> getCharacterAttributes() {
        return attributeMap;
    }

    public Map<Skill, Integer> skillMap = new HashMap<>();
    public Map<Skill, Integer> selectedSkillsMap = new HashMap<>();

    public CharacterBackground chosenBackground = null;

    Character() {
        populateAttributesWithZero();
    }

    public void buildfinalCharacterStats() {
        this.addRaceValues();
        this.addSubraceValues();
    }

    private void addRaceValues() {
        for (Map.Entry<AbilityScore, Integer> entry : this.race.getAbilityScoreChanges().entrySet()) {
            AbilityScore score = entry.getKey();
            int originalValue = entry.getValue();
            int newValue = attributeMap.get(score) + originalValue;
            attributeMap.put(score, newValue);
            setAbilityScoreModifier(score, newValue);
        }
    }

    private void addSubraceValues() {
        for (Map.Entry<AbilityScore, Integer> entry : this.subrace.getAbilityScoreChanges().entrySet()) {
            AbilityScore score = entry.getKey();
            int originalValue = entry.getValue();
            int newValue = attributeMap.get(score) + originalValue;
            attributeMap.put(score, newValue);
            setAbilityScoreModifier(score, newValue);
        }
    }

    public void setAbilityScore(AbilityScore abilityScore, int value) {
        attributeMap.put(abilityScore, value);
        setAbilityScoreModifier(abilityScore, value);
    }


    private void setAbilityScoreModifier(AbilityScore abilityScore, int mainAbilityScoreValue) {
        AbilityScoreModifier modifier = abilityScore.modifier;
        int modifierValue = modifierCalculation(mainAbilityScoreValue);
        attributeMap.put(modifier, modifierValue);
        runModifierTasks(modifier, modifierValue);
    }

    private void runModifierTasks(AbilityScoreModifier modifier, int modifierValue) {
        setSkills(modifier, modifierValue);
        switch (modifier) {
            case CON_MOD:
                updateHpValues(modifierValue);
                break;
            case DEX_MOD:
                updateAC(modifierValue);
                break;
        }
    }

    public void setSkills(AbilityScoreModifier modifier, int modifierValue) {
        for (Skill skill : Skill.values()) {
            if (skill.abilityScoreModifier.equals(modifier)) {
                skillMap.put(skill, modifierValue);
            }
        }
    }

    private void updateAC(int modifierValue) {
        attributeMap.put(VitalityModifier.ARMOR_CLASS, modifierValue + 10);
    }

    private void updateHpValues(int conModValue) {
        int lvl1Value = conModValue + 10;
        attributeMap.put(VitalityModifier.MAX_HP, lvl1Value);
        attributeMap.put(VitalityModifier.CURRENT_HP, lvl1Value);
        attributeMap.put(VitalityModifier.TOTAL_HP, lvl1Value);
    }

    private void populateAttributesWithZero() {
        for (CharacterAttribute abilityScore : AbilityScore.values()) {
            attributeMap.put(abilityScore, 0);
        }
        for (CharacterAttribute abilityScoreModifier : AbilityScoreModifier.values()) {
            attributeMap.put(abilityScoreModifier, 0);
        }
        for (CharacterAttribute vitalityModifier : VitalityModifier.values()) {
            attributeMap.put(vitalityModifier, 0);
        }
    }

    private int modifierCalculation(int mainStatValue) {
        int minusTen = mainStatValue - 10;
        float divideInHalf = (float) (minusTen / 2);
        return (int) Math.floor(divideInHalf);
    }

    public int getAttribute(CharacterAttribute specifier) {
        return attributeMap.get(specifier);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
        if (this.race.subraces == null) this.subrace = Subrace.NA;
    }

    public Subrace getSubrace() {
        return this.subrace;
    }

    public void setSubrace(Subrace subrace) {
        this.subrace = subrace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    int counter;
    public void skillAllocate(Skill skill) {
        if (counter == 0 || (counter > 1 && counter % 2 == 0)) {
            fighterSkill1 = skill;
        } else {
            fighterSkill2 = skill;
        }
        counter++;
    }

    public void setProficiencySkillMap(){
        selectedSkillsMap.clear();
        selectedSkillsMap.put(backgroundSkill1, 2);
        selectedSkillsMap.put(backgroundSkill2, 2);
        selectedSkillsMap.put(fighterSkill1, 2);
        selectedSkillsMap.put(fighterSkill2, 2);
}
}
package edu.bsu.cs222.dndcharactergenerator;

import com.sun.tools.javac.comp.Todo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Character {
    private String name;
    private String style;
    private Race race;
    private Subrace subrace;
    private Map<CharacterAttribute, Integer> attributeMap = new HashMap<>();

    Integer[] statNumbers = new Integer[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
    String[] raceNames = new String[]{"Half-Orc: +2 STR, +1 CON", "Dragonborn: +2 STR, +1 CHA", "Dwarf: +2 CON", "Elf: +2 DEX", "Gnome: +2 INT", "Half-Elf: +2 CHA", "Halfling: +2 DEX", "Human: +1 TO ALL STATS", "Tiefling: +2 CHA, +1 INT"};
    String[] styles = new String[]{"Archery", "Defense", "Dueling", "Great Weapon Fighting", "Protection", "Two-Weapon Fighting",};
    Skill backgroundSkill1;
    Skill backgroundSkill2;
    Skill fighterSkill1;
    Skill fighterSkill2;
    int checkCounter = 0;
    int proficiencyViaFighterLevel = 2;

    public Map<CharacterAttribute, Integer> getCharacterAttributes() {
        return attributeMap;
    }
    public Map<Skill, Integer> skillMap = new HashMap<>();
    public Map<Skill, Integer> selectedSkillsMap = new HashMap<>();
    public CharacterBackground[] characterBackgroundArray;

    public CharacterBackground chosenBackground=null;

    public CharacterBackground[] generateBackgroundArray(){
        BackgroundParser parser = new BackgroundParser();
        try {
            characterBackgroundArray =parser.setBackgroundFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return characterBackgroundArray;

    }
    Character(){populateAttributesWithZero();}
    public void buildfinalCharacterStats(){
        this.addRaceValues();
        this.addSubraceValues();
    }

    private void addRaceValues(){
        for(Map.Entry<AbilityScore,Integer> entry : this.race.getAbilityScoreChanges().entrySet()) {
            AbilityScore score = entry.getKey();
            int originalValue = entry.getValue();
            int newValue = attributeMap.get(score) + originalValue;
            attributeMap.put(score,newValue);
            setAbilityScoreModifier(score,newValue);
        }
    }

    private void addSubraceValues(){
        for(Map.Entry<AbilityScore,Integer> entry : this.subrace.getAbilityScoreChanges().entrySet()) {
            AbilityScore score = entry.getKey();
            int originalValue = entry.getValue();
            int newValue = attributeMap.get(score) + originalValue;
            attributeMap.put(score,newValue);
            setAbilityScoreModifier(score,newValue);
        }
    }

    public void setAbilityScore(AbilityScore abilityScore, int value) {
        attributeMap.put(abilityScore,value);
        setAbilityScoreModifier(abilityScore,value);
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

    public void checkBoxFlipper(Skill skill) {
        if (checkCounter == 0 || (checkCounter > 1 && checkCounter % 2 == 0)) {
            fighterSkill1 = skill;
        } else {
            fighterSkill2 = skill;
        }
        checkCounter++;
    }

    public void setProficiencySkillsMap() {
        selectedSkillsMap.clear();
        selectedSkillsMap.put(backgroundSkill1, 2);
        selectedSkillsMap.put(backgroundSkill2, 2);
        selectedSkillsMap.put(fighterSkill1, 2);
        selectedSkillsMap.put(fighterSkill2, 2);
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
        if(this.race.subraces ==null) this.subrace = Subrace.NA;
    }
    public Subrace getSubrace(){return this.subrace;}
    public void setSubrace(Subrace subrace) { this.subrace = subrace; }

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

    public String getStyleDescription(String style) {
        String styleDescription;
        switch (style) {
            case "Archery":
                styleDescription = "+2 bonus to attack rolls made with ranged weapons";
                break;
            case "Defense":
                styleDescription = "+1 bonus to AC while wearing armor";
                break;
            case "Dueling":
                styleDescription = "+2 bonus to damage rolls when holding a single weapon";
                break;
            case "Great Weapon Fighting":
                styleDescription = "You may re-roll the damage die while holding a two-handed weapon if you rolled a 1 or a 2";
                break;
            case "Protection":
                styleDescription = "When wielding a shield, you may impose a disadvantage on an enemy\n creature's attack roll when it attacks\na target" +
                        " other than you that is both within 5 feet of you and in your sight";
                break;
            case "Two-Weapon Fighting":
                styleDescription = "When you engage in two weapon fighting,\n you can add you ability modifier to the damage of the second attack";
                break;
            default:
                styleDescription = "No style found";
                break;
        }
        return styleDescription;
    }

    public void selectFighterProficiency(VBox innerProficiencyVbox, CharacterBackground background) {
        final int maxBoxCount = 2;
        CheckBox[] fighterBoxes = new CheckBox[Skill.values().length];
        ChangeListener<Boolean> listener0 = new ChangeListener<Boolean>() {
            private int listenedCount = 0;

            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    listenedCount++;
                    if (listenedCount == maxBoxCount) {
                        for (CheckBox selBox : fighterBoxes) {
                            if (!selBox.isSelected()) {
                                selBox.setDisable(true);
                            }
                        }
                    }
                } else {
                    if (listenedCount == maxBoxCount) {
                        for (CheckBox selBox : fighterBoxes) {
                            selBox.setDisable(false);
                        }
                    }
                    listenedCount--;
                }
            }
        };
        int counter = 0;

        for (Skill skill : Skill.values()) {
            CheckBox selbox = new CheckBox(skill.viewName);
            selbox.selectedProperty().addListener(listener0);
            if ((skill.isFighterOption) && (!skill.equals(background.getBgSkill1()) && (!skill.equals(background.getGetBgSkill2())))) {
                innerProficiencyVbox.getChildren().add(selbox);
                selbox.setOnAction(actionEvent -> {
                    if (selbox.isSelected()) {
                        checkBoxFlipper(skill);
                    }
                    setProficiencySkillsMap();
                });
            }
            fighterBoxes[counter] = selbox;
            counter++;
        }
    }
}
package edu.bsu.cs222.dndcharactergenerator;

import java.util.ArrayList;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private CharacterStats characterStats = new CharacterStats();

    String[] statNames = new String[]{"Strength","Dexterity","Constitution","Intelligence","Wisdom","Charisma"};
    Integer[] statNumbers = new Integer[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
    String [] raceNames = new String[]{"Half-Orc: +2 STR, +1 CON","Dragonborn: +2 STR, +1 CHA","Dwarf: +2 CON","Elf: +2 DEX","Gnome: +2 INT","Half-Elf: +2 CHA","Halfling: +2 DEX","Human: +1 TO ALL STATS","Tiefling: +2 CHA, +1 INT"};
    String [] styles = new String[]{"Archery","Defense","Dueling","Great Weapon Fighting","Protection","Two-Weapon Fighting",};
    String [] styleTexts = new String[]{"+2 bonus to attack rolls made with ranged weapons","+1 bonus to AC while wearing armor","+2 bonus to damage rolls when holding a single weapon","You may re-roll the damage die while holding a two-handed weapon if you rolled a 1 or a 2","When wielding a shield, you may impose a disadvantage on an enemy\n creature's attack roll when it attacks\na target other than you that is both within 5 feet of you and in your sight", "When you engage in two weapon fighting,\n you can add you ability modifier to the damage of the second attack","No style found"};
    public int getCharacterAttribute(CharacterAttribute attribute) {
        return characterStats.getAttribute(attribute);
    }

    public void setCharacterAttribute(CharacterAttribute attribute, int value) {
        if (attribute instanceof AbilityScore) {
            AbilityScore abilityScore = (AbilityScore) attribute;
            characterStats.setAttribute(abilityScore, value);
            AbilityScoreModifier modifier = AbilityScore.getAbilityScoreModifier(abilityScore);
            characterStats.setAttribute(modifier, modifierCalculation(characterStats.getAttribute(modifier)));
        } else {
            characterStats.setAttribute(attribute, value);
        }
    }

    public void updateArmorClass(int dexToAc) {
        characterStats.setAttribute(VitalityModifier.ARMOR_CLASS , 10 + dexToAc);
    }

    public void updateMaxHitPoints(int conToHitPoints) {
        characterStats.setAttribute(VitalityModifier.MAX_HP, 10 + conToHitPoints);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        if (this.race == race) return;
        if (this.race != null) {
            characterStats.implementAbilityScoreAffector(this.race, -1);
        }
        this.race = race;
        characterStats.implementAbilityScoreAffector(race, 1);
    }

    public RacialAttribute getRacialAttribute() {
        return racialAttribute;
    }

    public void setRacialAttribute(RacialAttribute attribute) {
        if (this.racialAttribute == attribute) return;
        if (this.racialAttribute != null) {
            characterStats.implementAbilityScoreAffector(attribute, -1);
        }
        this.racialAttribute = attribute;
        characterStats.implementAbilityScoreAffector(attribute, 1);
    }

    public void setName(String name) {
        this.name = name;
    }

    private int modifierCalculation(int modifierValue) {
        return (int) (Math.floor(((float) modifierValue - 10) / 2));
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

    public String getStyleDescription(String style){
        String styleDescription;
        switch (style) {
            case "Archery":
                styleDescription ="+2 bonus to attack rolls made with ranged weapons";
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

    public CharacterStats getCharacterStats() {
        CharacterStats statsToReturn = null;
        try {
            statsToReturn = CharacterStats.makeAttributesCopy(characterStats);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return statsToReturn;
    }

    public ArrayList statRoll() {
        DiceRoller stats = new DiceRoller();
        return stats.getStats();
    }

}
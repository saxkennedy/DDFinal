package edu.bsu.cs222.dndcharactergenerator;

import java.util.ArrayList;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private CharacterStats characterStats = new CharacterStats();

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
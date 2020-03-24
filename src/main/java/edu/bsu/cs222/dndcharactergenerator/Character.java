package edu.bsu.cs222.dndcharactergenerator;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private CharacterStats characterStats = new CharacterStats();

    public int getStat(Stats specifier) {
        return characterStats.getStat(specifier);
    }

    public void setStat(Stats stat, int value) {
        if (stat.modifier != null && stat.main == null) {
            characterStats.setStat(stat, value);
            Stats modifier = stat.modifier;
            characterStats.setStat(modifier, modifierCalculation(characterStats.getStat(modifier)));
        } else if(stat.modifier == null && stat.main != null) {
            characterStats.setStat(stat, value);
        } else {
            characterStats.setStat(stat, value);
        }
    }

    public void updateArmorClass(int dexToAc) {
        characterStats.setStat(Stats.ARMOR_CLASS, 10 + dexToAc);
    }

    public void updateMaxHitPoints(int conToHitPoints) {
        characterStats.setStat(Stats.MAX_HP, 10 + conToHitPoints);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        if (this.race == race) return;
        if (this.race != null) {
            characterStats.changeStatsWithStatChanger(this.race, -1);
        }
        this.race = race;
        characterStats.changeStatsWithStatChanger(race, 1);
    }

    public RacialAttribute getRacialAttribute() {
        return racialAttribute;
    }

    public void setRacialAttribute(RacialAttribute attribute) {
        if (this.racialAttribute == attribute) return;
        if (this.racialAttribute != null) {
            characterStats.changeStatsWithStatChanger(attribute, -1);
        }
        this.racialAttribute = attribute;
        characterStats.changeStatsWithStatChanger(attribute, 1);
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
            statsToReturn = CharacterStats.makeStatsCopy(characterStats);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return statsToReturn;
    }
}
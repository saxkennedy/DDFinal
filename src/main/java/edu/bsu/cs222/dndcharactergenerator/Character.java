package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private CharacterStats characterStats = new CharacterStats();

    public int getStat(StatName specifier) {
        return characterStats.getStat(specifier);
    }

    public void setStat(StatName statName, int value) {
        if (statName.isMain) {
            StatName modifier = statName.givenMainGetStatMod(statName);
            characterStats.setStat(statName, value);
            setModFromStat(modifier);
        } else {
            characterStats.setStat(statName, value);
        }
    }

    private void setModFromStat(StatName mainStat) {
        int modifierValue = (int) (Math.floor(((float) characterStats.getStat(mainStat) - 10) / 2));
        characterStats.setStat(mainStat, modifierValue);
    }

    public void updateArmorClass(int dexToAc) {
        characterStats.setStat(StatName.ARMOR_CLASS, 10 + dexToAc);
    }

    public void updateMaxHitPoints(int conToHitPoints) {
        characterStats.setStat(StatName.MAX_HP, 10 + conToHitPoints);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        if (this.race == race) return;
        if (this.race != null) {
            characterStats.removeModifier(this.race);
        }
        this.race = race;
        characterStats.addModifier(race);
    }

    public void setRacialAttribute(RacialAttribute attribute) {
        if (this.racialAttribute != null) {
            this.racialAttribute = attribute;
            characterStats.addModifier(attribute);
        }
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

    public Map<StatName, Integer> getCharacterStats() {
        return characterStats.getFinalStatMap();
    }
}
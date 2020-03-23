package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private Stats stats = new Stats();

    public int getStat(StatSpecifier specifier) {
        return stats.getStat(specifier);
    }

    public void setStat(StatSpecifier specifier, int value) {
        if(specifier.isMain) {
            StatSpecifier modifier = specifier.givenStatGetModifier(specifier);
            stats.setStat(specifier, value);
            int modifierValue = (int) (Math.floor(((float) stats.getStat(modifier) - 10) / 2));
            stats.setStat(modifier, modifierValue);
        } else {
            stats.setStat(specifier, value);
        }
    }

    public void updateArmorClass(int dexToAc) {
        stats.setStat(StatSpecifier.ARMOR_CLASS, 10 + dexToAc);
    }

    public void updateMaxHitPoints(int conToHitPoints) {
        stats.setStat(StatSpecifier.MAX_HP, 10 + conToHitPoints);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        if (this.race != null) {
            this.race = race;
            stats.changeStatsViaStatModifier(race);
        }
    }

    public void setRacialAttribute(RacialAttribute attribute) {
        if (this.racialAttribute != null) {
            this.racialAttribute = attribute;
            stats.changeStatsViaStatModifier(attribute);
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

    public Map<StatSpecifier, Integer> getStats() {
        return stats.getStats();
    }
}
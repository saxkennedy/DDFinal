package edu.bsu.cs222.dndcharactergenerator;

import java.util.ArrayList;
import java.util.Map;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;
    private Stats stats;

    public int getStrMod() {
        return stats.getStat(StatSpecifier.STR_MOD);
    }

    public int getDexMod() {
        return stats.getStat(StatSpecifier.DEX_MOD);
    }

    public int getConMod() {
        return stats.getStat(StatSpecifier.CON_MOD);
    }

    public int getIntMod() {
        return stats.getStat(StatSpecifier.INT_MOD);
    }

    public int getWisMod() {
        return stats.getStat(StatSpecifier.WIS_MOD);
    }

    public int getChaMod() {
        return stats.getStat(StatSpecifier.CHA_MOD);
    }

    public void updateArmorClass(int dexToAc) {
        stats.setStat(StatSpecifier.ARMOR_CLASS, 10 + dexToAc);
    }

    public int getAC() {
        return stats.getStat(StatSpecifier.ARMOR_CLASS);
    }

    public void updateMaxHitPoints(int conToHitPoints) {
        stats.setStat(StatSpecifier.MAX_HP, 10 + conToHitPoints);
    }

    public int getMaxHitPoints() {
        return stats.getStat(StatSpecifier.MAX_HP);
    }

    public int getSTR() {
        return stats.getStat(StatSpecifier.STR);
    }

    public void setSTR(int STR) {
        int strMod = (int) (Math.floor(((float) stats.getStat(StatSpecifier.STR) - 10) / 2));
        stats.setStat(StatSpecifier.STR, STR);
        stats.setStat(StatSpecifier.STR_MOD, strMod);
    }

    public int getDEX() {
        return stats.getStat(StatSpecifier.DEX);
    }

    public void setDEX(int DEX) {
        stats.setStat(StatSpecifier.DEX, DEX);
        int dexMod = (int) (Math.floor(((float) stats.getStat(StatSpecifier.DEX) - 10) / 2));
        updateArmorClass(dexMod);
    }

    public int getCON() {
        return stats.getStat(StatSpecifier.CON);
    }

    public void setCON(int CON) {
        stats.setStat(StatSpecifier.CON, CON);
        int conMod = (int) (Math.floor(((float) stats.getStat(StatSpecifier.CON) - 10) / 2));
        updateMaxHitPoints(conMod);
    }

    public int getINT() {
        return stats.getStat(StatSpecifier.INT);
    }

    public void setINT(int INT) {
        stats.setStat(StatSpecifier.INT, INT);
        int intMod = (int) (Math.floor((float) (stats.getStat(StatSpecifier.INT) - 10) / 2));
        stats.setStat(StatSpecifier.INT_MOD, intMod);
    }

    public int getWIS() {
        return stats.getStat(StatSpecifier.WIS);
    }

    public void setWIS(int WIS) {
        stats.setStat(StatSpecifier.WIS, WIS);
        int wisMod = (int) (Math.floor((float) (stats.getStat(StatSpecifier.WIS) - 10) / 2));
        stats.setStat(StatSpecifier.WIS_MOD, wisMod);
    }

    public int getCHA() {
        return stats.getStat(StatSpecifier.CHA);
    }

    public void setCHA(int CHA) {
        stats.setStat(StatSpecifier.CHA, CHA);
        int chaMod = (int) (Math.floor((float) (stats.getStat(StatSpecifier.CHA) - 10) / 2));
        stats.setStat(StatSpecifier.CHA_MOD, chaMod);
    }


    public ArrayList statRoll() {
        DiceRoller randomStats = new DiceRoller();
        return randomStats.getStats();
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
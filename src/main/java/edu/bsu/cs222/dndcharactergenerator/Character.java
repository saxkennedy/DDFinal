package edu.bsu.cs222.dndcharactergenerator;


import java.util.ArrayList;

public class Character {
    private String name;
    private String style;
    private Race race;
    private RacialAttribute racialAttribute;

    private int STR;
    private int DEX;
    private int CON;
    private int INT;
    private int WIS;
    private int CHA;
    private int AC;
    private int maxHitPoints;
    private int strMod;
    private int dexMod;
    private int conMod;
    private int intMod;
    private int wisMod;
    private int chaMod;

    public int getStrMod() {
        return strMod;
    }

    public int getDexMod() {
        return dexMod;
    }

    public int getConMod() {
        return conMod;
    }

    public int getIntMod() {
        return intMod;
    }

    public int getWisMod() {
        return wisMod;
    }

    public int getChaMod() {
        return chaMod;
    }

    public void updateArmorClass(int dexToAc) {
        this.AC = 10 + dexToAc;
    }

    public int getAC() {
        return this.AC;
    }

    public void updateMaxHitPoints(int conToHitPoints){
        this.maxHitPoints=10+conToHitPoints;
    }
    public int getMaxHitPoints(){
        return maxHitPoints;
    }
    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
        strMod = (int) (Math.floor(((float) this.STR - 10) / 2));
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
        dexMod = (int) (Math.floor(((float) this.DEX - 10) / 2));
        updateArmorClass(dexMod);
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
        conMod = (int) (Math.floor(((float) this.CON - 10) / 2));
        updateMaxHitPoints(conMod);
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
        intMod = (int) (Math.floor((float) (this.INT - 10) / 2));
    }

    public int getWIS() {
        return WIS;
    }

    public void setWIS(int WIS) {
        this.WIS = WIS;
        wisMod = (int) (Math.floor((float) (this.WIS - 10) / 2));
    }

    public int getCHA() {
        return CHA;
    }

    public void setCHA(int CHA) {
        this.CHA = CHA;
        chaMod = (int) (Math.floor((float) (this.CHA - 10) / 2));
    }


    public ArrayList statRoll() {
        DiceRoller stats = new DiceRoller();
        return stats.getStats();
    }


    public Race getRace() {
        return race;
    }

    public void setRace(String race) {

        if (this.race != null) {
            System.out.println("Old race bonus removed");
            this.removeRacialAbilityScoreBonus();
            this.removeRacialAttributeScoreBonus();
        }
        this.assignEnumRace(race);
        this.addRacialAbilityScoreBonus();
        System.out.println("Race changed to " + race);
        System.out.printf("STR: %d DEX: %d CON: %d INT: %d WIS: %d CHR: %d\n",
                this.STR, this.DEX, this.CON, this.INT, this.WIS, this.CHA);
    }

    public void setRacialAttribute(RacialAttribute attribute) {

        if (this.racialAttribute != null) {
            System.out.println("Old racialAbility bonus removed");
            this.removeRacialAttributeScoreBonus();
        }
        try {
            this.racialAttribute = attribute;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.addRacialAttributeScoreBonus();
        System.out.println("Racial Attribute changed to " + this.racialAttribute.attributeName);
        System.out.printf("STR: %d DEX: %d CON: %d INT: %d WIS: %d CHR: %d\n",
                this.STR, this.DEX, this.CON, this.INT, this.WIS, this.CHA);
    }


    public void setName(String name) {
        this.name = name;
        System.out.println("Name changed to " + name);
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
        System.out.println("Style changed to " + this.style);
    }

    public void assignEnumRace(String race) {
        switch (race) {
            case "Half-Orc: +2 STR, +1 CON":
                this.race = Race.HALFORC;
                break;
            case "Dragonborn: +2 STR, +1 CHA":
                this.race = Race.DRAGONBORN;
                break;
            case "Dwarf: +2 CON":
                this.race = Race.DWARF;
                break;
            case "Elf: +2 DEX":
                this.race = Race.ELF;
                break;
            case "Gnome: +2 INT":
                this.race = Race.GNOME;
                break;
            case "Half-Elf: +2 CHA":
                this.race = Race.HALFELF;
                break;
            case "Halfling: +2 DEX":
                this.race = Race.HALFLING;
                break;
            case "Human: +1 TO ALL STATS":
                this.race = Race.HUMAN;
                break;
            case "Tiefling: +2 CHA, +1 INT":
                this.race = Race.TIEFLING;
                break;
            default:
                this.race = Race.ZEROMAN;
                break;
        }
    }

    public void addRacialAbilityScoreBonus() {
        this.setSTR(this.getSTR() + this.race.str);
        this.setDEX(this.getDEX() + this.race.dex);
        this.setCON(this.getCON() + this.race.con);
        this.setINT(this.getINT() + this.race.intel);
        this.setWIS(this.getWIS() + this.race.wis);
        this.setCHA(this.getCHA() + this.race.chr);
    }

    public void removeRacialAbilityScoreBonus() {
        this.setSTR(this.getSTR() - this.race.str);
        this.setDEX(this.getDEX() - this.race.dex);
        this.setCON(this.getCON() - this.race.con);
        this.setINT(this.getINT() - this.race.intel);
        this.setWIS(this.getWIS() - this.race.wis);
        this.setCHA(this.getCHA() - this.race.chr);
    }

    public void addRacialAttributeScoreBonus() {

        this.setSTR(this.getSTR() + this.racialAttribute.str);
        this.setDEX(this.getDEX() + this.racialAttribute.dex);
        this.setCON(this.getCON() + this.racialAttribute.con);
        this.setINT(this.getINT() + this.racialAttribute.intel);
        this.setWIS(this.getWIS() + this.racialAttribute.wis);
        this.setCHA(this.getCHA() + this.racialAttribute.chr);
    }

    public void removeRacialAttributeScoreBonus() {
        if (this.racialAttribute != null) {
            this.setSTR(this.getSTR() - this.racialAttribute.str);
            this.setDEX(this.getDEX() - this.racialAttribute.dex);
            this.setCON(this.getCON() - this.racialAttribute.con);
            this.setINT(this.getINT() - this.racialAttribute.intel);
            this.setWIS(this.getWIS() - this.racialAttribute.wis);
            this.setCHA(this.getCHA() - this.racialAttribute.chr);
            this.racialAttribute = null;
        }
    }
}
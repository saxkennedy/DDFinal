package edu.bsu.cs222.dndcharactergenerator;


import java.util.ArrayList;

public class Character {
    private String name;
    private String fStyle;
    private Race race;
    private RacialAttribute racialAttribute;

    private int STR;
    private int DEX;
    private int CON;
    private int INT;
    private int WIS;
    private int CHA;
    private int AC;

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

    public void updateArmorClass(int AC) {
        this.AC = 10 + AC;
    }

    public int getAC() {
        return this.AC;
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
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
        conMod = (int) (Math.floor(((float) this.CON - 10) / 2));
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
        ArrayList statBlock = stats.getStats();
        return statBlock;
    }


    public Race getRace() {
        return race;
    }

    public void setRace(String race) {

        if (this.race != null) {
            System.out.println("Old race bonus removed");
            this.removeRacialAbilityScoreBonus();
        }
        this.assignEnumRace(race);
        this.addRacialAbilityScoreBonus();
        System.out.println("Race changed to " + race);
        System.out.printf("STR: %d DEX: %d CON: %d INT: %d WIS: %d CHR: %d\n",
                this.STR, this.DEX, this.CON, this.INT, this.WIS, this.CHA);
    }

    public void setRacialAttribute(String racialAttribute) {

        if (this.racialAttribute != null) {
            System.out.println("Old racialAbility bonus removed");
            this.removeRacialAttributeScoreBonus();
        }
        this.assignEnumRacialAttribute(racialAttribute);
        this.addRacialAttributeScoreBonus();
        System.out.println("Racial Attribute changed to " + this.racialAttribute.getAttributeName());
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

    public String getfStyle() {
        return fStyle;
    }

    public void setfStyle(String fStyle) {
        this.fStyle = fStyle;
        System.out.println("Style changed to " + this.fStyle);
    }

    public void assignEnumRace(String race) {
        switch (race) {
            case "Half-Orc":
                this.race = Race.HALFORC;
                break;
            case "Dragonborn":
                this.race = Race.DRAGONBORN;
                break;
            case "Dwarf":
                this.race = Race.DWARF;
                break;
            case "Elf":
                this.race = Race.ELF;
                break;
            case "Gnome":
                this.race = Race.GNOME;
                break;
            case "Half-Elf":
                this.race = Race.HALFELF;
                break;
            case "Halfling":
                this.race = Race.HALFLING;
                break;
            case "Human":
                this.race = Race.HUMAN;
                break;
            case "Tiefling":
                this.race = Race.TIEFLING;
                break;
            default:
                this.race = null;
                break;
        }
    }

    public void assignEnumRacialAttribute(String racialAttribute) {
        switch (racialAttribute) {
            case "Hill Dwarf: +1 WIS":
                this.racialAttribute = RacialAttribute.HILLDWARF;
                break;
            case "Mountain Dwarf: +2 STR":
                this.racialAttribute = RacialAttribute.MOUNTAINDWARF;
                break;
            case "High Elf: +1 INT":
                this.racialAttribute = RacialAttribute.HIGHELF;
                break;
            case "Wood Elf: +1 WIS":
                this.racialAttribute = RacialAttribute.WOODELF;
                break;
            case "Drow: +1 CHA":
                this.racialAttribute = RacialAttribute.DROW;
                break;
            case "Forest Gnome: +1 DEX":
                this.racialAttribute = RacialAttribute.FORESTGNOME;
                break;
            case "Rock Gnome: +1 CON":
                this.racialAttribute = RacialAttribute.ROCKGNOME;
                break;
            case "Lightfoot: +1 CHA":
                this.racialAttribute = RacialAttribute.LIGHTFOOT;
                break;
            case "Stout: +1 CON":
                this.racialAttribute = RacialAttribute.STOUT;
                break;
            default:
                throw new NullPointerException();
        }
        this.racialAttribute.setAttributeName(racialAttribute);
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
        this.setSTR(this.getSTR() - this.racialAttribute.str);
        this.setDEX(this.getDEX() - this.racialAttribute.dex);
        this.setCON(this.getCON() - this.racialAttribute.con);
        this.setINT(this.getINT() - this.racialAttribute.intel);
        this.setWIS(this.getWIS() - this.racialAttribute.wis);
        this.setCHA(this.getCHA() - this.racialAttribute.chr);
    }


}
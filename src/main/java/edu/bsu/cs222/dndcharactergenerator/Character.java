package edu.bsu.cs222.dndcharactergenerator;


import java.util.ArrayList;

public class Character {
    private String name;
    private String fStyle;
    private Race race;

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
        Race enumRace = assignEnumRace(race);
        this.race = enumRace;
        this.addRacialAbilityScoreBonus();
        System.out.println("Race changed to " + race);
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

    public String getfStyle(){return fStyle; }

    public void setfStyle(String fStyle) {
        this.fStyle = fStyle;
        System.out.println("Style changed to " + this.fStyle);
    }

    public Race assignEnumRace(String race) {
        switch (race) {
            case "Half-Orc":
                return Race.HALFORC;
            case "Dragonborn":
                return Race.DRAGONBORN;
            case "Dwarf":
                return Race.DWARF;
            case "Elf":
                return Race.ELF;
            case "Gnome":
                return Race.GNOME;
            case "Half-Elf":
                return Race.HALFELF;
            case "Halfling":
                return Race.HALFLING;
            case "Human":
                return Race.HUMAN;
            case "Tiefling":
                return Race.TIEFLING;
            default:
                return Race.HUMAN;
        }
    }

    public void addRacialAbilityScoreBonus() {
        this.setSTR(this.getSTR() + this.getRace().str);
        this.setDEX(this.getDEX() + this.getRace().dex);
        this.setCON(this.getCON() + this.getRace().con);
        this.setINT(this.getINT() + this.getRace().intel);
        this.setWIS(this.getWIS() + this.getRace().wis);
        this.setCHA(this.getCHA() + this.getRace().chr);
    }

    public void removeRacialAbilityScoreBonus() {
        this.setSTR(this.getSTR() - this.getRace().str);
        this.setDEX(this.getDEX() - this.getRace().dex);
        this.setCON(this.getCON() - this.getRace().con);
        this.setINT(this.getINT() - this.getRace().intel);
        this.setWIS(this.getWIS() - this.getRace().wis);
        this.setCHA(this.getCHA() - this.getRace().chr);
    }


}
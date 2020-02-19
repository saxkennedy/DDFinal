package edu.bsu.cs222.dndcharactergenerator;


import java.util.ArrayList;

public class Character {
    String name;
    Race race;
    int STR;
    int DEX;
    int CON;
    int INT;
    int WIS;
    int CHA;

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public int getCON() {
        return CON;
    }

    public void setCON(int CON) {
        this.CON = CON;
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public int getWIS() {
        return WIS;
    }

    public void setWIS(int WIS) {
        this.WIS = WIS;
    }

    public int getCHA() {
        return CHA;
    }

    public void setCHA(int CHA) {
        this.CHA = CHA;
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
        if(this.race != null) {
            System.out.println("Old race bonus removed");
            this.race.removeRaceModifier(this);
        }

        Race enumRace = assignEnumRace(race);
        this.race = enumRace;
        this.race.addRaceModifier(this);
        System.out.println("Race changed to " + race);
        System.out.printf("STR: %d DEX: %d CON: %d INT: %d WIS: %d CHR: %d\n",this.STR,this.DEX,this.CON,this.INT,this.WIS,this.CHA);
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Name changed to " + name);
    }

    public String getName() {
        return name;
    }

    public Race assignEnumRace(String race) {
        switch(race) {
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

}

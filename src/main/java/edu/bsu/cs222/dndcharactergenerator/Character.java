package edu.bsu.cs222.dndcharactergenerator;


import java.util.ArrayList;

public class Character {
    String name;
    String race;
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


    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
        System.out.println("Race changed to " + race);
    }


    public void setName(String name) {
        this.name = name;
        System.out.println("Name changed to " + name);
    }

    public String getName() {
        return name;
    }

}

package edu.bsu.cs222.dndcharactergenerator;


import java.util.ArrayList;

public class Character {
    private String name;
    private Race race;
    private Integer STR;
    private Integer DEX;
    private Integer CON;
    private Integer INT;
    private Integer WIS;
    private Integer CHA;
    private Integer AC;

    private Integer strMod;
    private Integer dexMod;
    private Integer conMod;
    private Integer intMod;
    private Integer wisMod;
    private Integer chaMod;

    public Integer getStrMod() {
        return strMod;
    }
    public Integer getDexMod() {
        return dexMod;
    }
    public Integer getConMod() {
        return conMod;
    }
    public Integer getIntMod() {
        return intMod;
    }
    public Integer getWisMod() {
        return wisMod;
    }
    public Integer getChaMod() {
        return chaMod;
    }

    public void updateArmorClass(int AC){
        this.AC=10+AC;
    }

    public Integer getAC() {
        return this.AC;
    }

    public Integer getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
        strMod=(int)(Math.floor((this.STR-10)/2));
    }

    public Integer getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
        dexMod=(int)(Math.floor((this.DEX-10)/2));
    }

    public Integer getCON() { return CON;}

    public void setCON(int CON) {
        this.CON = CON;
        conMod=(int)(Math.floor((this.CON-10)/2));
    }

    public Integer getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
        intMod=(int)(Math.floor((this.INT-10)/2));
    }

    public Integer getWIS() {
        return WIS;
    }

    public void setWIS(int WIS) {
        this.WIS = WIS;
        wisMod=(int)(Math.floor((this.WIS-10)/2));
    }

    public Integer getCHA() {
        return CHA;
    }

    public void setCHA(int CHA) {
        this.CHA = CHA;
        chaMod=(int)(Math.floor((this.CHA-10)/2));
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
            this.race.removeRaceModifier(this);
        }

        Race enumRace = assignEnumRace(race);
        this.race = enumRace;
        this.race.addRaceModifier(this);
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

}

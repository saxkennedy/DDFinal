package edu.bsu.cs222.dndcharactergenerator;

public enum Race {
    HALFORC(2,0,1,0,0,0, "Half-Orc"),
    DRAGONBORN(2,0,0,0,0,1, "DragonBorn"),
    DWARF(0,0,2,0,0,0, "Dwarf"),
    ELF(0,2,0,0,0,0, "Elf"),
    GNOME(0,0,0,2,0,0, "Gnome"),
    HALFELF(0,0,0,0,0,2, "Half-Elf"),
    HALFLING(0,2,0,0,0,0, "Halfling"),
    HUMAN(1,1,1,1,1,1, "Human"),
    TIEFLING(0,0,0,1,0,2, "Tiefling");


    public final int str;
    public final int dex;
    public final int con;
    public final int intel;
    public final int wis;
    public final int chr;
    public final String raceName;

    Race(int str,int dex,int con, int intel, int wis, int chr, String raceName) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.chr = chr;
        this.raceName = raceName;
    }

}

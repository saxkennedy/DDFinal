package edu.bsu.cs222.dndcharactergenerator;

public enum Race {
    HALFORC(2,0,1,0,0,0),
    DRAGONBORN(2,0,0,0,0,1),
    DWARF(0,0,2,0,0,0),
    ELF(0,2,0,0,0,0),
    GNOME(0,0,0,2,0,0),
    HALFELF(0,0,0,0,0,2),
    HALFLING(0,2,0,0,0,0),
    HUMAN(1,1,1,1,1,1),
    TIEFLING(0,0,0,1,0,2);


    public final int str;
    public final int dex;
    public final int con;
    public final int intel;
    public final int wis;
    public final int chr;

    Race(int str,int dex,int con, int intel, int wis, int chr) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.chr = chr;
    }

}

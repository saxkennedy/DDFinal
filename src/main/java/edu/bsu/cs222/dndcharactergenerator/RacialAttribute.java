package edu.bsu.cs222.dndcharactergenerator;

public enum RacialAttribute {
    HILLDWARF(0, 0, 0, 0, 1, 0, "Hill Dwarf: +1 WIS"),
    MOUNTAINDWARF(2, 0, 0, 0, 0, 0, "Mountain Dwarf: +2 STR"),
    HIGHELF(0, 0, 0, 1, 0, 0, "High Elf: +1 INT"),
    WOODELF(0, 0, 0, 0, 1, 0, "Wood Elf: +1 WIS"),
    DROW(0, 0, 0, 0, 0, 1, "Drow: +1 CHA"),
    FORESTGNOME(0, 1, 0, 0, 0, 0, "Forest Gnome: +1 DEX"),
    ROCKGNOME(0, 0, 1, 0, 0, 0, "Rock Gnome: +1 CON"),
    LIGHTFOOT(0, 0, 0, 0, 0, 1, "Lightfoot: +1 CHA"),
    STOUT(0, 0, 1, 0, 0, 0, "Stout: +1 CON");


    public final int str;
    public final int dex;
    public final int con;
    public final int intel;
    public final int wis;
    public final int chr;
    public final String attributeName;

    RacialAttribute(int str, int dex, int con, int intel, int wis, int chr, String attributeName) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.chr = chr;
        this.attributeName = attributeName;
    }

}

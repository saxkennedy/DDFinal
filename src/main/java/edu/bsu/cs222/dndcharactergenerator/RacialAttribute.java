package edu.bsu.cs222.dndcharactergenerator;

public enum RacialAttribute {
    HILLDWARF(0, 0, 0, 0, 1, 0),
    MOUNTAINDWARF(2, 0, 0, 0, 0, 0),
    HIGHELF(0, 0, 0, 1, 0, 0),
    WOODELF(0, 0, 0, 0, 1, 0),
    DROW(0, 0, 0, 0, 0, 1),
    FORESTGNOME(0, 1, 0, 0, 0, 0),
    ROCKGNOME(0, 0, 1, 0, 0, 0),
    LIGHTFOOT(0, 0, 0, 0, 0, 1),
    STOUT(0, 0, 1, 0, 0, 0);

    public final int str;
    public final int dex;
    public final int con;
    public final int intel;
    public final int wis;
    public final int chr;
    private String attributeName;

    RacialAttribute(int str, int dex, int con, int intel, int wis, int chr) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.chr = chr;
    }

    public void setAttributeName(String name) {
        this.attributeName = name;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

}

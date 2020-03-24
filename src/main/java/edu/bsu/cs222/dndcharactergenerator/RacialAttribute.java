package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public enum RacialAttribute implements StatChanger {
    HILLDWARF(0, 0, 0, 0, 1, 0, "Hill Dwarf: +1 WIS"),
    MOUNTAINDWARF(2, 0, 0, 0, 0, 0, "Mountain Dwarf: +2 STR"),
    HIGHELF(0, 0, 0, 1, 0, 0, "High Elf: +1 INT"),
    WOODELF(0, 0, 0, 0, 1, 0, "Wood Elf: +1 WIS"),
    DROW(0, 0, 0, 0, 0, 1, "Drow: +1 CHA"),
    FORESTGNOME(0, 1, 0, 0, 0, 0, "Forest Gnome: +1 DEX"),
    ROCKGNOME(0, 0, 1, 0, 0, 0, "Rock Gnome: +1 CON"),
    LIGHTFOOT(0, 0, 0, 0, 0, 1, "Lightfoot: +1 CHA"),
    STOUT(0, 0, 1, 0, 0, 0, "Stout: +1 CON"),

    BLACKDRAGON(0, 0, 0, 0, 0, 0, "Black Dragon: Acid"),
    BLUEDRAGON(0, 0, 0, 0, 0, 0, "Blue Dragon: Lightning"),
    BRASSDRAGON(0, 0, 0, 0, 0, 0, "Brass Dragon: Fire"),
    BRONZEDRAGON(0, 0, 0, 0, 0, 0, "Bronze Dragon: Lightning"),
    COPPERDRAGON(0, 0, 0, 0, 0, 0, "Copper Dragon: Acid"),
    GOLDDRAGON(0, 0, 0, 0, 0, 0, "Gold Dragon: Fire"),
    GREENDRAGON(0, 0, 0, 0, 0, 0, "Green Dragon: Poison"),
    REDDRAGON(0, 0, 0, 0, 0, 0, "Red Dragon: Fire"),
    SILVERDRAGON(0, 0, 0, 0, 0, 0, "Silver Dragon: Cold"),
    WHITEDRAGON(0, 0, 0, 0, 0, 0, "White Dragon: Cold");


    private Map<StatName, Integer> attributes;
    public final String attributeName;

    RacialAttribute(int str, int dex, int con, int intel, int wis, int cha, String attributeName) {
        attributes = new HashMap<>();
        attributes.put(StatName.STR, str);
        attributes.put(StatName.DEX, dex);
        attributes.put(StatName.CON, con);
        attributes.put(StatName.INT, intel);
        attributes.put(StatName.WIS, wis);
        attributes.put(StatName.CHA, cha);
        this.attributeName = attributeName;
    }

    @Override
    public Map<StatName, Integer> getStatChanges() {
        return attributes;
    }
}
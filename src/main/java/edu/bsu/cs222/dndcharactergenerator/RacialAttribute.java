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


    private Map<Stats, Integer> attributes;
    public final String attributeName;

    RacialAttribute(int str, int dex, int con, int intel, int wis, int cha, String attributeName) {
        attributes = new HashMap<>();
        attributes.put(Stats.STR, str);
        attributes.put(Stats.DEX, dex);
        attributes.put(Stats.CON, con);
        attributes.put(Stats.INT, intel);
        attributes.put(Stats.WIS, wis);
        attributes.put(Stats.CHA, cha);
        this.attributeName = attributeName;
    }

    @Override
    public Map<Stats, Integer> getStatChanges() {
        return attributes;
    }
}
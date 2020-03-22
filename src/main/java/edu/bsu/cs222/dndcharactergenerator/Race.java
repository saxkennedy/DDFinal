package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public enum Race implements StatModifier {
    HALFORC(2, 0, 1, 0, 0, 0, "Half-Orc", "Half-Orc: +2 STR, +1 CON"),
    DRAGONBORN(2, 0, 0, 0, 0, 1, "DragonBorn", "Dragonborn: +2 STR, +1 CHA"),
    DWARF(0, 0, 2, 0, 0, 0, "Dwarf", "Dwarf: +2 CON"),
    ELF(0, 2, 0, 0, 0, 0, "Elf", "Elf: +2 DEX"),
    GNOME(0, 0, 0, 2, 0, 0, "Gnome", "Gnome: +2 INT"),
    HALFELF(0, 0, 0, 0, 0, 2, "Half-Elf", "Half-Elf: +2 CHA"),
    HALFLING(0, 2, 0, 0, 0, 0, "Halfling", "Halfling: +2 DEX"),
    HUMAN(1, 1, 1, 1, 1, 1, "Human", "Human: +1 TO ALL STATS"),
    TIEFLING(0, 0, 0, 1, 0, 2, "Tiefling", "Tiefling: +2 CHA, +1 INT"),
    ZEROMAN(0, 0, 0, 0, 0, 0, "Zeroman", null);

    private Map<StatSpecifier, Integer> attributes;
    public final String raceName;
    private final String viewName;

    Race(int str, int dex, int con, int intel, int wis, int cha, String raceName, String viewName) {
        attributes = new HashMap<>();
        attributes.put(StatSpecifier.STR, str);
        attributes.put(StatSpecifier.DEX, dex);
        attributes.put(StatSpecifier.CON, con);
        attributes.put(StatSpecifier.INT, intel);
        attributes.put(StatSpecifier.WIS, wis);
        attributes.put(StatSpecifier.CHA, cha);
        this.raceName = raceName;
        this.viewName = viewName;
    }

    @Override
    public Map<StatSpecifier, Integer> getStatAdditions() {
        return attributes;
    }
}

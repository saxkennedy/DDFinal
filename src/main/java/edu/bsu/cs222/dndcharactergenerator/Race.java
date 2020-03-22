package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public enum Race implements StatChanger {
    HALFORC(2, 0, 1, 0, 0, 0, "Half-Orc"),
    DRAGONBORN(2, 0, 0, 0, 0, 1, "DragonBorn"),
    DWARF(0, 0, 2, 0, 0, 0, "Dwarf"),
    ELF(0, 2, 0, 0, 0, 0, "Elf"),
    GNOME(0, 0, 0, 2, 0, 0, "Gnome"),
    HALFELF(0, 0, 0, 0, 0, 2, "Half-Elf"),
    HALFLING(0, 2, 0, 0, 0, 0, "Halfling"),
    HUMAN(1, 1, 1, 1, 1, 1, "Human"),
    TIEFLING(0, 0, 0, 1, 0, 2, "Tiefling"),
    ZEROMAN(0, 0, 0, 0, 0, 0, "Zeroman");

    private Map<StatSpecifier, Integer> attributes;
    public final String raceName;

    Race(int str, int dex, int con, int intel, int wis, int cha, String raceName) {
        attributes = new HashMap<>();
        attributes.put(StatSpecifier.STR, str);
        attributes.put(StatSpecifier.DEX, dex);
        attributes.put(StatSpecifier.CON, con);
        attributes.put(StatSpecifier.INT, intel);
        attributes.put(StatSpecifier.WIS, wis);
        attributes.put(StatSpecifier.CHA, cha);
        this.raceName = raceName;
    }

    @Override
    public Map<StatSpecifier, Integer> getStatAdditions() {
        return attributes;
    }
}

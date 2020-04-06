package edu.bsu.cs222.dndcharactergenerator;


import java.util.HashMap;
import java.util.Map;

public enum Race implements AbilityScoreChanger {
    HALFORC(2, 0, 1, 0, 0, 0, "Half-Orc", "Half-Orc: +2 STR, +1 CON", "/halfOrcImage.png", "You're a mighty half-orc, you smash good! \nAlso you don't need to wrack that surely massive brain to select further details!"),
    DRAGONBORN(2, 0, 0, 0, 0, 1, "DragonBorn", "Dragonborn: +2 STR, +1 CHA", "/dragonbornImage.png", "You're a Dragonborn, select your breath weapon!"),
    DWARF(0, 0, 2, 0, 0, 0, "Dwarf", "Dwarf: +2 CON", "/dwarfImage.png", "You're a hardy dwarf!  Choose your subrace!"),
    ELF(0, 2, 0, 0, 0, 0, "Elf", "Elf: +2 DEX", "/elfImage.png", "Choose your variant of the 'finer' folk"),
    GNOME(0, 0, 0, 2, 0, 0, "Gnome", "Gnome: +2 INT", "/gnomeImage.png", "You have chosen to be diminutive, but (hopefully) crafty gnome!  Select a subrace!"),
    HALFELF(0, 0, 0, 0, 0, 2, "Half-Elf", "Half-Elf: +2 CHA", "/halfElfImage.png", "As a Half-Elf, you may choose two skills to increase by one point each.\nSimply go back and add one to two SEPARATE ability scores!"),
    HALFLING(0, 2, 0, 0, 0, 0, "Halfling", "Halfling: +2 DEX", "/halflingImage.png", "You are a surprisingly sturdy and resilient race; a halfling!"),
    HUMAN(1, 1, 1, 1, 1, 1, "Human", "Human: +1 TO ALL STATS", "/humanImage.png", "You are a human.  Congratulations."),
    TIEFLING(0, 0, 0, 1, 0, 2, "Tiefling", "Tiefling: +2 CHA, +1 INT", "/tieflingImage.png", "You are a tiefling; try not to burn yourself or others!"),
    ZEROMAN(0, 0, 0, 0, 0, 0, "Zeroman", null, "/bigNope.png", "NO RACE SELECTED!  YOU MIGHT WANNA FIX THIS!");


    private Map<AbilityScore, Integer> attributes;
    public final String raceName;
    public final String viewName;
    public final String picture;
    public final String label;

    Race(int strChanges, int dexChanges, int conChanges, int intChanges, int wisChanges, int chaChanges, String raceName, String viewName, String picture, String label) {
        attributes = new HashMap<>();
        attributes.put(AbilityScore.STR, strChanges);
        attributes.put(AbilityScore.DEX, dexChanges);
        attributes.put(AbilityScore.CON, conChanges);
        attributes.put(AbilityScore.INT, intChanges);
        attributes.put(AbilityScore.WIS, wisChanges);
        attributes.put(AbilityScore.CHA, chaChanges);
        this.raceName = raceName;
        this.viewName = viewName;
        this.picture = picture;
        this.label = label;
    }

    @Override
    public Map<AbilityScore, Integer> getAbilityScoreChanges() {
        return attributes;
    }
}

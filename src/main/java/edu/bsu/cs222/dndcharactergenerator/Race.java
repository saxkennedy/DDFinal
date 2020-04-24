package edu.bsu.cs222.dndcharactergenerator;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Race {
    HALFORC(2, 0, 1, 0, 0, 0, 30, "Half-Orc", "Half-Orc: +2 STR, +1 CON", "/halfOrcImage.png", "You're a mighty half-orc, you smash good! \nAlso you don't need to wrack that surely massive brain to select further details!",
            null),
    DRAGONBORN(2, 0, 0, 0, 0, 1, 30, "DragonBorn", "Dragonborn: +2 STR, +1 CHA", "/dragonbornImage.png", "You're a Dragonborn, select your breath weapon!",
            Arrays.asList(Subrace.BLUEDRAGON, Subrace.BLACKDRAGON, Subrace.BRASSDRAGON, Subrace.BRONZEDRAGON, Subrace.COPPERDRAGON, Subrace.GOLDDRAGON, Subrace.GREENDRAGON, Subrace.SILVERDRAGON, Subrace.WHITEDRAGON)),
    DWARF(0, 0, 2, 0, 0, 0, 25, "Dwarf", "Dwarf: +2 CON", "/dwarfImage.png", "You're a hardy dwarf!  Choose your subrace!",
            Arrays.asList(Subrace.HILLDWARF, Subrace.MOUNTAINDWARF)),
    ELF(0, 2, 0, 0, 0, 0, 30, "Elf", "Elf: +2 DEX", "/elfImage.png", "Choose your variant of the 'finer' folk",
            Arrays.asList(Subrace.HIGHELF, Subrace.WOODELF, Subrace.DROW)),
    GNOME(0, 0, 0, 2, 0, 0, 25, "Gnome", "Gnome: +2 INT", "/gnomeImage.png", "You have chosen to be diminutive, but (hopefully) crafty gnome!  Select a subrace!",
            Arrays.asList(Subrace.FORESTGNOME, Subrace.ROCKGNOME)),
    HALFELF(0, 0, 0, 0, 0, 2, 30,"Half-Elf", "Half-Elf: +2 CHA", "/halfElfImage.png", "As a Half-Elf, you may choose two skills to increase by one point each.\nSimply go back and add one to two SEPARATE ability scores!",
            null),
    HALFLING(0, 2, 0, 0, 0, 0, 25,"Halfling", "Halfling: +2 DEX", "/halflingImage.png", "You are a surprisingly sturdy and resilient race; a halfling!",
            Arrays.asList(Subrace.LIGHTFOOT,Subrace.STOUT)),
    HUMAN(1, 1, 1, 1, 1, 1, 30,"Human", "Human: +1 TO ALL STATS", "/humanImage.png", "You are a human.  Congratulations.",
            null),
    TIEFLING(0, 0, 0, 1, 0, 2,30 ,"Tiefling", "Tiefling: +2 CHA, +1 INT", "/tieflingImage.png", "You are a tiefling; try not to burn yourself or others!",
            null),
    ZEROMAN(0, 0, 0, 0, 0, 0, 0,"Zeroman", null, "/bigNope.png", "NO RACE SELECTED!  YOU MIGHT WANNA FIX THIS!",
            Arrays.asList(Subrace.NA));


    private final Map<AbilityScore, Integer> attributes;
    public final String raceName;
    public final String viewName;
    public final String picture;
    public final String label;
    public final int speed;
    public final List<Subrace> subraces;

    Race(int strChanges, int dexChanges, int conChanges, int intChanges, int wisChanges, int chaChanges, int speed, String raceName, String viewName, String picture, String label,List<Subrace> subraces) {
        attributes = new HashMap<>();
        attributes.put(AbilityScore.STR, strChanges);
        attributes.put(AbilityScore.DEX, dexChanges);
        attributes.put(AbilityScore.CON, conChanges);
        attributes.put(AbilityScore.INT, intChanges);
        attributes.put(AbilityScore.WIS, wisChanges);
        attributes.put(AbilityScore.CHA, chaChanges);
        this.speed=speed;
        this.raceName = raceName;
        this.viewName = viewName;
        this.picture = picture;
        this.label = label;
        this.subraces = subraces;
    }

    public Map<AbilityScore, Integer> getAbilityScoreChanges() {
        return attributes;
    }
}

package edu.bsu.cs222.dndcharactergenerator;

public enum StatSpecifier {
    STR("STR"),
    DEX("DEX"),
    CON("CON"),
    INT("INT"),
    WIS("WIS"),
    CHA("CHA"),
    STR_MOD("STRmod"),
    DEX_MOD("DEXmod "),
    CON_MOD("CONmod"),
    INT_MOD("INTmod"),
    WIS_MOD("WISmod"),
    CHA_MOD("CHAmod"),
    STR_SAVTHROW("ST Strength"),
    DEX_SAVTHROW("ST Dexterity"),
    CON_SAVTHROW("ST Constitution"),
    INT_SAVTHROW("ST Intelligence"),
    WIS_SAVTHROW("ST Wisdom"),
    CHA_SAVTHROW("ST Charisma"),
    MAX_HP("HPMax"),
    CURRENT_HP("HPCurrent"),
    TOTAL_HP("HDTotal");


    StatSpecifier(String name) {
        this.name = name;
    }

    public final String name;
}

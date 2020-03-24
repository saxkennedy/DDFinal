package edu.bsu.cs222.dndcharactergenerator;

public enum Stats {
    STR("STR", Stats.STR_MOD,null),
    DEX("DEX", Stats.DEX_MOD ,null),
    CON("CON", Stats.CON_MOD ,null),
    INT("INT", Stats.INT_MOD ,null),
    WIS("WIS", Stats.WIS_MOD ,null),
    CHA("CHA", Stats.CHA_MOD ,null),
    STR_MOD("STRmod",  null, Stats.STR),
    DEX_MOD("DEXmod ", null, Stats.DEX),
    CON_MOD("CONmod",  null, Stats.CON),
    INT_MOD("INTmod",  null, Stats.INT),
    WIS_MOD("WISmod",  null, Stats.WIS),
    CHA_MOD("CHAmod",  null, Stats.CHA),
    STR_SAVTHROW("ST Strength",     null, null),
    DEX_SAVTHROW("ST Dexterity",    null, null),
    CON_SAVTHROW("ST Constitution", null, null),
    INT_SAVTHROW("ST Intelligence", null, null),
    WIS_SAVTHROW("ST Wisdom",       null, null),
    CHA_SAVTHROW("ST Charisma",     null, null),
    MAX_HP("HPMax",                 null, null),
    CURRENT_HP("HPCurrent",         null, null),
    TOTAL_HP("HDTotal",             null, null),
    ARMOR_CLASS("AC",               null, null);
    //todo: refactor into mainstats, saving throws, and otherStats. i.e seperate enums but loopable as one

    Stats(String pdfGeneratorName, Stats modifier, Stats main) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.modifier = modifier;
        this.main = main;
    }

    public final String pdfGeneratorName;
    public final Stats modifier;
    public final Stats main;
}

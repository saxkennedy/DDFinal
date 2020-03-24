package edu.bsu.cs222.dndcharactergenerator;

public enum Stats {
    STR("STR", false, true, false),
    DEX("DEX", false, true, false),
    CON("CON", false, true, false),
    INT("INT", false, true, false),
    WIS("WIS", false, true, false),
    CHA("CHA", false, true, false),
    STR_MOD("STRmod", true, false, false),
    DEX_MOD("DEXmod ", true, false, false),
    CON_MOD("CONmod", true, false, false),
    INT_MOD("INTmod", true, false, false),
    WIS_MOD("WISmod", true, false, false),
    CHA_MOD("CHAmod", true, false, false),
    STR_SAVTHROW("ST Strength", false, false, true),
    DEX_SAVTHROW("ST Dexterity", false, false, true),
    CON_SAVTHROW("ST Constitution", false, false, true),
    INT_SAVTHROW("ST Intelligence", false, false, true),
    WIS_SAVTHROW("ST Wisdom", false, false, true),
    CHA_SAVTHROW("ST Charisma", false, false, true),
    MAX_HP("HPMax", false, false, false),
    CURRENT_HP("HPCurrent", false, false, false),
    TOTAL_HP("HDTotal", false, false, false),
    ARMOR_CLASS("AC", false, false, false);
    //todo: refactor into mainstats, saving throws, and otherStats seperate enums but loopable as one

    Stats(String pdfGeneratorName, boolean isMod, boolean isMain, boolean isST) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.isMod = isMod;
        this.isMain = isMain;
        this.isST = isST;
    }

    public final boolean isMain;
    public final boolean isST;
    public final boolean isMod;
    public final String pdfGeneratorName;

    public Stats givenMainStatGetMod(Stats mainStat) {
        Stats newMod = null;
        switch(mainStat) {
            case STR: newMod = STR_MOD; break;
            case DEX: newMod = DEX_MOD; break;
            case INT: newMod = INT_MOD; break;
            case WIS: newMod = WIS_MOD; break;
            case CHA: newMod = CHA_MOD; break;
            case CON: newMod = CON_MOD; break;
        }
        return newMod;
    }

    public Stats givenModStatGetMain(Stats modStat) {
        Stats newMod = null;
        switch(modStat) {
            case STR_MOD: newMod = STR; break;
            case DEX_MOD: newMod = DEX; break;
            case INT_MOD: newMod = INT; break;
            case WIS_MOD: newMod = WIS; break;
            case CHA_MOD: newMod = CHA; break;
            case CON_MOD: newMod = CON; break;
        }
        return newMod;
    }
}

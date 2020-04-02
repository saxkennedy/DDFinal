package edu.bsu.cs222.dndcharactergenerator;

public enum AbilityScore implements CharacterAttribute {

    STR("STR", AbilityScoreModifier.STR_MOD, "Strength","ST Strength", 2),
    DEX("DEX", AbilityScoreModifier.DEX_MOD, "Dexterity","ST Dexterity",0),
    CON("CON", AbilityScoreModifier.CON_MOD, "Constitution","ST Constitution",2),
    INT("INT", AbilityScoreModifier.INT_MOD, "Intelligence","ST Intelligence",0),
    WIS("WIS", AbilityScoreModifier.WIS_MOD, "Wisdom","ST Wisdom",0),
    CHA("CHA", AbilityScoreModifier.CHA_MOD, "Charisma","ST Charisma",0);

    public final String pdfGeneratorName;
    public final AbilityScoreModifier modifier;
    public final String viewName;
    public final String pdfSavingThrowName;
    public final int savingThrowProficiency;

    AbilityScore(String pdfGeneratorName, AbilityScoreModifier modifier, String viewName,String pdfSavingThrowName, int savingThrowProficiency) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.modifier = modifier;
        this.viewName = viewName;
        this.pdfSavingThrowName =pdfSavingThrowName;
        this.savingThrowProficiency =savingThrowProficiency;
    }

    public static AbilityScore getAbilityScoreFromString(String abilityScore) {
        for (AbilityScore newAbilityScore : AbilityScore.values()) {
            if (newAbilityScore.viewName.equals(abilityScore)) {
                return newAbilityScore;
            }
        }
        return null;
    }

    @Override
    public String getPdfGeneratorName() {
        return pdfGeneratorName;
    }
}

package edu.bsu.cs222.dndcharactergenerator;

public enum AbilityScoreModifier implements CharacterAttribute {

    STR_MOD("STRmod", AbilityScore.STR),
    DEX_MOD("DEXmod ", AbilityScore.DEX),
    CON_MOD("CONmod", AbilityScore.CON),
    INT_MOD("INTmod", AbilityScore.INT),
    WIS_MOD("WISmod", AbilityScore.WIS),
    CHA_MOD("CHAmod", AbilityScore.CHA);

    String pdfGeneratorName;
    AbilityScore abilityScore;

    AbilityScoreModifier(String pdfGeneratorName, AbilityScore abilityScore) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.abilityScore = abilityScore;
    }

    public static AbilityScore getAbilityScore(AbilityScoreModifier modifier) {
        AbilityScore newAbilityScore = null;
        switch(modifier) {
            case STR_MOD: newAbilityScore = AbilityScore.STR;
            case DEX_MOD: newAbilityScore = AbilityScore.DEX;
            case CON_MOD: newAbilityScore = AbilityScore.CON;
            case INT_MOD: newAbilityScore = AbilityScore.INT;
            case WIS_MOD: newAbilityScore = AbilityScore.WIS;
            case CHA_MOD: newAbilityScore = AbilityScore.CHA;
        }
        return newAbilityScore;
    }

    @Override
    public String getPdfGeneratorName() {
        return this.pdfGeneratorName;
    }
}

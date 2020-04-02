package edu.bsu.cs222.dndcharactergenerator;

public enum AbilityScoreModifier implements CharacterAttribute {

    STR_MOD("STRmod", AbilityScore.STR),
    DEX_MOD("DEXmod ", AbilityScore.DEX),
    CON_MOD("CONmod", AbilityScore.CON),
    INT_MOD("INTmod", AbilityScore.INT),
    WIS_MOD("WISmod", AbilityScore.WIS),
    CHA_MOD("CHamod", AbilityScore.CHA);

    String pdfGeneratorName;
    AbilityScore abilityScore;

    AbilityScoreModifier(String pdfGeneratorName, AbilityScore abilityScore) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.abilityScore = abilityScore;
    }

    @Override
    public String getPdfGeneratorName() {
        return this.pdfGeneratorName;
    }
}

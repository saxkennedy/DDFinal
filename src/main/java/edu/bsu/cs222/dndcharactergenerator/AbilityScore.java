package edu.bsu.cs222.dndcharactergenerator;

public enum AbilityScore implements CharacterAttribute {

    STR("STR", AbilityScoreModifier.STR_MOD),
    DEX("DEX", AbilityScoreModifier.DEX_MOD),
    CON("CON", AbilityScoreModifier.CON_MOD),
    INT("INT", AbilityScoreModifier.INT_MOD),
    WIS("WIS", AbilityScoreModifier.WIS_MOD),
    CHA("CHA", AbilityScoreModifier.CHA_MOD);

    public final String pdfGeneratorName;
    public final AbilityScoreModifier modifier;

    AbilityScore(String pdfGeneratorName, AbilityScoreModifier modifier) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.modifier = modifier;
    }

    public static AbilityScoreModifier getAbilityScoreModifier(AbilityScore abilityScore) {
        AbilityScoreModifier newAbilityScoreModifier = null;
        switch(abilityScore) {
            case STR: newAbilityScoreModifier = AbilityScoreModifier.STR_MOD;
            case DEX: newAbilityScoreModifier = AbilityScoreModifier.DEX_MOD;
            case CON: newAbilityScoreModifier = AbilityScoreModifier.CON_MOD;
            case INT: newAbilityScoreModifier = AbilityScoreModifier.INT_MOD;
            case WIS: newAbilityScoreModifier = AbilityScoreModifier.WIS_MOD;
            case CHA: newAbilityScoreModifier = AbilityScoreModifier.CHA_MOD;
        }
        return newAbilityScoreModifier;
    }

    @Override
    public String getPdfGeneratorName() {
        return pdfGeneratorName;
    }
}

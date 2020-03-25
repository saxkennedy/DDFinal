package edu.bsu.cs222.dndcharactergenerator;

public enum AbilityScore implements CharacterAttribute {

    STR("STR", AbilityScoreModifier.STR_MOD,"Strength"),
    DEX("DEX", AbilityScoreModifier.DEX_MOD,"Dexterity"),
    CON("CON", AbilityScoreModifier.CON_MOD,"Constitution"),
    INT("INT", AbilityScoreModifier.INT_MOD,"Intelligence"),
    WIS("WIS", AbilityScoreModifier.WIS_MOD,"Wisdom"),
    CHA("CHA", AbilityScoreModifier.CHA_MOD,"Charisma");

    public final String pdfGeneratorName;
    public final AbilityScoreModifier modifier;
    public final String viewName;

    AbilityScore(String pdfGeneratorName, AbilityScoreModifier modifier, String viewName) {
        this.pdfGeneratorName = pdfGeneratorName;
        this.modifier = modifier;
        this.viewName = viewName;
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

    public static AbilityScore getAbilityScoreFromString(String abilityScore){
        for (AbilityScore newAbilityScore : AbilityScore.values()){
            if (newAbilityScore.viewName.equals(abilityScore)){
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

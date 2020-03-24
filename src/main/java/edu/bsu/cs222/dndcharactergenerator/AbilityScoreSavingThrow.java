package edu.bsu.cs222.dndcharactergenerator;

public enum AbilityScoreSavingThrow implements CharacterAttribute {

    STR_SAVTHROW("ST Strength"),
    DEX_SAVTHROW("ST Dexterity"),
    CON_SAVTHROW("ST Constitution"),
    INT_SAVTHROW("ST Intelligence"),
    WIS_SAVTHROW("ST Wisdom"),
    CHA_SAVTHROW("ST Charisma");

    private final String pdfGeneratorName;

    AbilityScoreSavingThrow(String pdfGeneratorName) {
        this.pdfGeneratorName = pdfGeneratorName;
    }

    @Override
    public String getPdfGeneratorName() {
        return pdfGeneratorName;
    }
}

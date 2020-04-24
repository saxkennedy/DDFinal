package edu.bsu.cs222.dndcharactergenerator;

public enum VitalityModifier implements CharacterAttribute {
    MAX_HP("HPMax"),
    CURRENT_HP("HPCurrent"),
    TOTAL_HP("HDTotal"),
    ARMOR_CLASS("AC");

    private final String pdfGeneratorName;

    VitalityModifier(String pdfGeneratorName) {
        this.pdfGeneratorName = pdfGeneratorName;
    }

    @Override
    public String getPdfGeneratorName() {
        return pdfGeneratorName;
    }
}

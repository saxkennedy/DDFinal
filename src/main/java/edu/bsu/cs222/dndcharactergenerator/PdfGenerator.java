package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PdfGenerator {
    private Character character;
    private PDDocument newCharacterSheet; // in memory pdf
    private PDAcroForm form; // edits newCharacterSheet's forms

    private String[] statsFieldNames = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
    private String[] modifierFieldNames = {"STRmod", "DEXmod ", "CONmod", "INTmod", "WISmod", "CHamod"};
    private String[] savingThrowsNames = {
            "ST Strength",
            "ST Dexterity",
            "ST Constitution",
            "ST Intelligence",
            "ST Wisdom",
            "ST Charisma"
    };
    private String[] hitPointFieldNames = {"HPMax", "HPCurrent", "HDTotal"};


    public static final class Builder {
        private Character character;

        public Builder setCharacter(Character character) {
            this.character = character;
            return this;
        }

        public PdfGenerator build() {
            return new PdfGenerator(this);
        }
    }

    public PdfGenerator(Builder builder) {
        this.character = builder.character;
        try {
            getPDDocumentFromTemplate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getAcroFormFromTemplate();
        try {
            writeCharacterName();
            writeRace();
            writeArmorClass();
            writeStyle();
            writeInitiative();
            writeModifiers();
            writeSavingTrows();
            writeStats();
            writeHitPoints();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStats() throws IOException {
        for(String field : statsFieldNames) {
            setFieldValue(field, getCharacterValueFromFieldName(field));
        }
    }

    private void writeModifiers() throws IOException {
        for(String field : modifierFieldNames) {
            setFieldValue(field, getCharacterValueFromFieldName(field));
        }
    }

    private void writeInitiative() throws IOException {
        PDTextField initiative = (PDTextField) form.getField("Initiative");
        initiative.setValue(Integer.toString(character.getDexMod()));
    }

    private void writeHitPoints() throws IOException {
        for(String field : hitPointFieldNames) {
            setFieldValue(field, getCharacterValueFromFieldName(field));
        }
    }

    private void writeSavingTrows() throws IOException {
        for(String field : savingThrowsNames) {
            setFieldValue(field, getCharacterValueFromFieldName(field));
        }
    }

    private void writeArmorClass() throws IOException {
        PDTextField armorClass = (PDTextField) form.getField("AC");
        armorClass.setValue(Integer.toString(character.getAC()));
    }

    private void writeStyle() throws IOException {
        PDTextField featuresAndTraits = (PDTextField) form.getField("Features and Traits");
        featuresAndTraits.setValue(character.getStyle() + "\n\nSecond Wind:\nYou have a limited well of stamina that you can draw on to protect yourself from harm.  " +
                "On your turn, you can use a bonus action to regain hit " +
                "points equal to 1d10+your fighter level (1).");
    }

    private void writeRace() throws IOException {
        PDTextField race = (PDTextField) form.getField("Race ");
        race.setValue(character.getRace().raceName);
    }

    private void writeCharacterName() throws IOException {
        PDTextField charName1 = (PDTextField) form.getField("CharacterName");
        charName1.setValue(character.getName());
    }

    private void getPDDocumentFromTemplate() throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("blank_character.pdf");
        PDDocument template = PDDocument.load(stream);
        COSDocument lowTemplate = template.getDocument();
        newCharacterSheet = new PDDocument(lowTemplate);
    }

    private void getAcroFormFromTemplate() {
        PDDocumentCatalog catalog = newCharacterSheet.getDocumentCatalog();
        form = catalog.getAcroForm();
    }

    public void writeNewCharacterSheet(File file) throws IOException {
        newCharacterSheet.save(file);
        newCharacterSheet.close();
    }

    private void setFieldValue(String fieldName, int valueToWrite) throws IOException {
        PDField fieldToSet = form.getField(fieldName);
        fieldToSet.setValue(String.valueOf(valueToWrite));
    }

    private int getCharacterValueFromFieldName(String fieldName) {
        int valueFromCharacter = 0;
        switch(fieldName) {
            case "STR":
                valueFromCharacter = character.getSTR();
                break;
            case "CON":
                valueFromCharacter = character.getCON();
                break;
            case "INT":
                valueFromCharacter = character.getINT();
                break;
            case "WIS":
                valueFromCharacter = character.getWIS();
                break;
            case "CHA":
                valueFromCharacter = character.getCHA();
                break;
            case "DEX":
                valueFromCharacter = character.getDEX();
                break;
            case "STRmod":
                valueFromCharacter = character.getStrMod();
                break;
            case "DEXmod":
            case "ST Dexterity":
                valueFromCharacter = character.getDexMod();
                break;
            case "CONmod":
                valueFromCharacter = character.getConMod();
                break;
            case "WISmod":
            case "ST Wisdom":
                valueFromCharacter = character.getWisMod();
                break;
            case "CHamod":
            case "ST Charisma":
                valueFromCharacter = character.getChaMod();
                break;
            case "ST Strength":
                valueFromCharacter = character.getStrMod() + 2;
                break;
            case "ST Constitution":
                valueFromCharacter = character.getConMod() + 2;
                break;
            case "ST Intelligence":
                valueFromCharacter = character.getIntMod();
                break;
            case "HPCurrent":
            case "HPMax":
            case "HDTotal":
                valueFromCharacter = character.getMaxHitPoints();
                break;
        }
        return valueFromCharacter;
    }
}

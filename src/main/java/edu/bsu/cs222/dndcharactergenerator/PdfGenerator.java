package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PdfGenerator {
    private Character character;
    private PDDocument newCharacterSheet; // in memory pdf
    private PDAcroForm form; // edits newCharacterSheet's forms


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
        PDTextField strField = (PDTextField) form.getField("STR");
        PDTextField dexField = (PDTextField) form.getField("DEX");
        PDTextField conField = (PDTextField) form.getField("CON");
        PDTextField intField = (PDTextField) form.getField("INT");
        PDTextField wisField = (PDTextField) form.getField("WIS");
        PDTextField chaField = (PDTextField) form.getField("CHA");
        strField.setValue(Integer.toString(character.getSTR()));
        dexField.setValue(Integer.toString(character.getDEX()));
        conField.setValue(Integer.toString(character.getCON()));
        intField.setValue(Integer.toString(character.getINT()));
        wisField.setValue(Integer.toString(character.getWIS()));
        chaField.setValue(Integer.toString(character.getCON()));
    }

    private void writeModifiers() throws IOException {
        PDTextField strMod = (PDTextField) form.getField("STRmod");
        PDTextField dexMod = (PDTextField) form.getField("DEXmod ");
        PDTextField conMod = (PDTextField) form.getField("CONmod");
        PDTextField intMod = (PDTextField) form.getField("INTmod");
        PDTextField wisMod = (PDTextField) form.getField("WISmod");
        PDTextField chaMod = (PDTextField) form.getField("CHamod");
        strMod.setValue(Integer.toString(character.getStrMod()));
        dexMod.setValue(Integer.toString(character.getDexMod()));
        conMod.setValue(Integer.toString(character.getConMod()));
        intMod.setValue(Integer.toString(character.getIntMod()));
        wisMod.setValue(Integer.toString(character.getWisMod()));
        chaMod.setValue(Integer.toString(character.getChaMod()));
    }

    private void writeInitiative() throws IOException {
        PDTextField initiative = (PDTextField) form.getField("Initiative");
        initiative.setValue(Integer.toString(character.getDexMod()));
    }

    private void writeHitPoints() throws IOException {
        PDTextField maxHitPoints = (PDTextField) form.getField("HPMax");
        PDTextField currentHitPoints = (PDTextField) form.getField("HPCurrent");
        PDTextField totalHitPoints = (PDTextField) form.getField("HDTotal");
        maxHitPoints.setValue(Integer.toString(character.getMaxHitPoints()));
        currentHitPoints.setValue(Integer.toString(character.getMaxHitPoints()));
        totalHitPoints.setValue(Integer.toString(character.getMaxHitPoints()));
    }

    private void writeSavingTrows() throws IOException {
        PDTextField strSavingThrow = (PDTextField) form.getField("ST Strength");
        PDTextField dexSavingThrow = (PDTextField) form.getField("ST Dexterity");
        PDTextField conSavingthrow = (PDTextField) form.getField("ST Constitution");
        PDTextField intSavingThrow = (PDTextField) form.getField("ST Intelligence");
        PDTextField wisSavingThrow = (PDTextField) form.getField("ST Wisdom");
        PDTextField chaSavingThrow = (PDTextField) form.getField("ST Charisma");
        strSavingThrow.setValue(Integer.toString(character.getStrMod() + 2));
        dexSavingThrow.setValue(Integer.toString(character.getDexMod()));
        conSavingthrow.setValue(Integer.toString(character.getConMod() + 2));
        intSavingThrow.setValue(Integer.toString(character.getIntMod()));
        wisSavingThrow.setValue(Integer.toString(character.getWisMod()));
        chaSavingThrow.setValue(Integer.toString(character.getChaMod()));
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
}

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
            writeStats();
            writeSavingTrows();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStats() throws IOException{
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

    private void writeSavingTrows() throws IOException {
        PDTextField strModField = (PDTextField) form.getField("ST Strength");
        PDTextField dexModField = (PDTextField) form.getField("ST Dexterity");
        PDTextField conModField = (PDTextField) form.getField("ST Constitution");
        PDTextField intModField = (PDTextField) form.getField("ST Intelligence");
        PDTextField wisModField = (PDTextField) form.getField("ST Wisdom");
        PDTextField chaModField = (PDTextField) form.getField("ST Charisma");

        strModField.setValue(Integer.toString(character.getStrMod()));
        dexModField.setValue(Integer.toString(character.getDexMod()));
        conModField.setValue(Integer.toString(character.getConMod()));
        intModField.setValue(Integer.toString(character.getIntMod()));
        wisModField.setValue(Integer.toString(character.getWisMod()));
        chaModField.setValue(Integer.toString(character.getChaMod()));
    }

    private void writeArmorClass() throws IOException {
        PDField armorClass = form.getField("AC");
        PDTextField textField = (PDTextField) armorClass;
        textField.setValue(Integer.toString(character.getAC()));
    }

    private void writeRace() throws IOException {
        PDField race = form.getField("Race ");
        PDTextField raceTextField = (PDTextField) race;
        raceTextField.setValue(character.getRace().raceName);
    }

    private void writeCharacterName() throws IOException {
        PDField charName = form.getField("CharacterName");
        PDTextField charTextField = (PDTextField) charName;
        charTextField.setValue(character.getName());
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

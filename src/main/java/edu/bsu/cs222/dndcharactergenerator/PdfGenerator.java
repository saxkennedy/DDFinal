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
import java.util.Map;

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
            writeStyle();
            writeStats();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStats() throws IOException {
        Map<Stats, Integer> stats = character.getCharacterStats();
        for(Map.Entry<Stats, Integer> entry : stats.entrySet()) {
            PDField fieldToSet = form.getField(entry.getKey().pdfGeneratorName);
            fieldToSet.setValue(String.valueOf(entry.getValue()));
        }
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

    private void setFieldValue(Stats name) throws IOException {
        PDField fieldToSet = form.getField(name.pdfGeneratorName);
        fieldToSet.setValue(String.valueOf(character.getCharacterStats().get(name.pdfGeneratorName)));
    }
}

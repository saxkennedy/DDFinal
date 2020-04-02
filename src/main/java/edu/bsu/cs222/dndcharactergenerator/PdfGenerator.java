package edu.bsu.cs222.dndcharactergenerator;

import javafx.scene.layout.Background;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

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

    private PdfGenerator(Builder builder) {
        this.character = builder.character;
        try {
            getPDDocumentFromTemplate();
            getAcroFormFromTemplate();
            Map<CharacterAttribute, Integer> characterAttributes = character.getCharacterAttributes();
            writeCharacterAttributes(characterAttributes);
            writeCharacterName(character.getName());
            writeDescriptionField();
            writeSavingThrows();
            setField("Background",character.chosenBackground.viewName);
            setField("Race ",character.getRace().raceName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCharacterName(String name) throws IOException {
        setField("CharacterName", name);
    }
    private void writeCharacterAttributes(Map<CharacterAttribute, Integer> stats) throws IOException {
        for(Map.Entry<CharacterAttribute, Integer> entry : stats.entrySet()) {
            setField(entry.getKey().getPdfGeneratorName(), String.valueOf(entry.getValue()));
        }
    }

    private void setField(String key, String value) throws IOException {
        PDField fieldToWrite = form.getField(key);
        fieldToWrite.setValue(value);
    }

    private void writeDescriptionField() throws IOException {
        String traitToWrite = character.getStyle() + "\n\nSecond Wind:\nYou have a limited well of stamina that you can draw on to protect yourself from harm.  " +
                "On your turn, you can use a bonus action to regain hit " +
                "points equal to 1d10+your fighter level (1).\n\nBACKGROUND: "+character.chosenBackground.viewName+":\n" + character.chosenBackground.description+"\n\nBACKGROUND FEATURE:\n"+character.chosenBackground.feature;
        setField("Features and Traits", traitToWrite);
    }

    private void getPDDocumentFromTemplate() throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("blank_character.pdf");
        PDDocument template = PDDocument.load(stream);
        COSDocument lowTemplate = template.getDocument();
        newCharacterSheet = new PDDocument(lowTemplate);
    }
    private void writeSavingThrows() throws IOException {
        for (AbilityScore abilityScore : AbilityScore.values())
        {
            System.out.println(character.getAbilityScoreModifier(abilityScore));
            String savingThrow = String.valueOf(abilityScore.savingThrowProficiency+character.getAbilityScoreModifier(abilityScore));
            setField(abilityScore.pdfSavingThrowName,savingThrow);
        }
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

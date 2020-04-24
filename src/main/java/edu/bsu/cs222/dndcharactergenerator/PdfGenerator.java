package edu.bsu.cs222.dndcharactergenerator;

import java.lang.Object;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.font.FontCache;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class PdfGenerator {
    private final Character character;
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
            writeSkills();
            setField("Background", character.chosenBackground.getViewName());
            setField("Race ", character.getRace().raceName);
            setField("Initiative", String.valueOf(character.getAttribute(AbilityScore.DEX)));
            setField("Speed",String.valueOf(character.getRace().speed));
        } catch (Exception ignored) {
        }
    }

    private void writeCharacterName(String name) {
        setField("CharacterName", name);
    }

    private void writeCharacterAttributes(Map<CharacterAttribute, Integer> stats) {
        for (Map.Entry<CharacterAttribute, Integer> entry : stats.entrySet()) {
            setField(entry.getKey().getPdfGeneratorName(), String.valueOf(entry.getValue()));
        }
    }

    private void setField(String key, String value) {
        PDField fieldToWrite = form.getField(key);
        try {
            System.setProperty("pdfbox.fontcache","/resources/.pdfbox.cache");
            fieldToWrite.setValue(value);
        }catch(Exception ignored){

        }
    }

    private void writeDescriptionField(){
        String racialSpecial = "";
        if (character.getRace().equals(Race.DRAGONBORN)) {
            racialSpecial = "Dragonborn Breath Attack:\n" + character.getSubrace().attributeName;
        }
        String traitToWrite = racialSpecial + "\n\nFIGHTER TRAITS:\n" + character.getStyle() + "\n\nSecond Wind:\nYou have a limited well of stamina that you can draw on to protect yourself from harm.  " +
                "On your turn, you can use a bonus action to regain hit " +
                "points equal to 1d10+your fighter level (1).\n\nBACKGROUND: " + character.chosenBackground.getViewName() + ":\n" + character.chosenBackground.getDescription() + "\n\nBACKGROUND FEATURE:\n" + character.chosenBackground.getFeature();
        setField("Features and Traits", traitToWrite);

    }

    private void writeSkills() throws IOException {
        for (Skill skill : Skill.values()) {
            if (character.selectedSkillsMap.containsKey(skill)) {
                setField(skill.pdfStringName, String.valueOf(character.getAttribute(skill.abilityScoreModifier) + character.proficiencyViaFighterLevel));
                PDField fieldForCasting = form.getField(skill.checkBoxIdentifier);
                ((PDCheckBox) fieldForCasting).check();
            } else {
                setField(skill.pdfStringName, String.valueOf(character.getAttribute(skill.abilityScoreModifier)));
            }
        }
    }

    private void getPDDocumentFromTemplate() throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("blank_character.pdf");
        PDDocument template = PDDocument.load(stream);
        COSDocument lowTemplate = template.getDocument();
        newCharacterSheet = new PDDocument(lowTemplate);
    }

    private void writeSavingThrows() {
        for (AbilityScore abilityScore : AbilityScore.values()) {
            String savingThrow = String.valueOf(abilityScore.savingThrowProficiency + character.getAttribute(abilityScore.modifier));
            setField(abilityScore.pdfSavingThrowName, savingThrow);
        }
    }

    private void getAcroFormFromTemplate() {
        PDDocumentCatalog catalog = newCharacterSheet.getDocumentCatalog();
        form = catalog.getAcroForm();
    }

    public void writeNewCharacterSheet(File file) throws IOException {
        newCharacterSheet.save(file+".pdf");
        newCharacterSheet.close();
    }
}

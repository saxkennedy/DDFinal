package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PdfGenerator {
    private String characterName;
    private Race race;
    private Integer armorClass;
    private List<Integer> savingThrows;
    private List<Integer> skills;
    private PDDocument newCharacterSheet; // in memory pdf
    private PDAcroForm form; // edits newCharacterSheet's forms

    public class Builder {
        private String characterName;
        private Race race;
        private Integer armorClass;
        private List<Integer> savingThrows;
        private List<Integer> skills;

        public Builder setCharacterName(String characterName) {
            this.characterName = characterName;
            return this;
        }
        public Builder setRace(Race race) {
            this.race = race;
            return this;
        }
        public Builder setArmorClass(Integer armorClass) {
            this.armorClass = armorClass;
            return this;
        }
        public Builder setSavingThrows(List<Integer> savingThrows) {
            this.savingThrows = savingThrows;
            return this;
        }
        public Builder setSkills(List<Integer> skills) {
            this.skills = skills;
            return this;
        }
        public PdfGenerator build() {
            return new PdfGenerator(this);
        }


    }

    public PdfGenerator(Builder builder) {
        this.characterName = builder.characterName;
        this.race = builder.race;
        this.armorClass = builder.armorClass;
        this.savingThrows = builder.savingThrows;
        this.skills = builder.skills;

        try {
            getPDDocumentFromTemplate();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        getAcroFormFromTemplate();

        // todo: edit the pdf for each piece of info
    }

    private void getPDDocumentFromTemplate() throws IOException{
        InputStream stream = getClass().getClassLoader().getResourceAsStream("blank_character.pdf");
        PDDocument template = PDDocument.load(stream);
        COSDocument lowTemplate = template.getDocument();
        newCharacterSheet = new PDDocument(lowTemplate);
        lowTemplate.close(); // not needed anymore
        template.close(); // not needed anymore
    }

    private void getAcroFormFromTemplate() {
        PDDocumentCatalog catalog = newCharacterSheet.getDocumentCatalog();
        form = catalog.getAcroForm();
    }

    public void writeNewCharacterSheet() throws IOException{
        newCharacterSheet.save("new_character_sheet.pdf");
        newCharacterSheet.close();
    }
}

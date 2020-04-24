package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class PdfGeneratorTest {
    private static final Character character = new Character();
    private File writtenPDf = null;
    private PDDocument testDoc;
    private PDAcroForm testForm;

    @Disabled
    @BeforeEach
    public void setup() {
        character.setName("Lieutenant Data");
        character.setRace(Race.ELF);
        character.setSubrace(Subrace.HIGHELF);
        character.setStyle("Archery");

        PdfGenerator generator = new PdfGenerator.Builder()
                .setCharacter(character)
                .build();
        Assertions.assertNotNull(character);
        Assertions.assertNotNull(generator);
        try {
            writtenPDf = new File("sheet_test.pdf");
            generator.writeNewCharacterSheet(writtenPDf);
            testDoc = PDDocument.load(writtenPDf);
            PDDocumentCatalog catalog = testDoc.getDocumentCatalog();
            testForm = catalog.getAcroForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCharacterName() {
        String expected = "Lieutenant Data";
        Assertions.assertEquals(expected, testForm.getField("CharacterName").getValueAsString());
    }

}
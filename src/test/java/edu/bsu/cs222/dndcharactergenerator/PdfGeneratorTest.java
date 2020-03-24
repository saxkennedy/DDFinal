package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

public class PdfGeneratorTest {
    private static Character character = new Character();
    private File writtenPDf = null;
    private PDDocument testDoc;
    private PDAcroForm testForm;

    @Disabled
    @BeforeEach
    public void setup() {
        character.setName("Hellen");
        character.setRace(Race.DWARF);
        character.setRacialAttribute(RacialAttribute.MOUNTAINDWARF);
        character.setStyle("foo-bar");

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
}

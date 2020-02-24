package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PdfLearningTest {
    // todo: uncomment when functionality is finished
//    PdfGenerator generator = new PdfGenerator.Builder()
//            .setArmorClass()
//            .setCharacterName()
//            .setRace()
//            .setSavingThrows()
//            .setSkills()
//            .build();

    @Test
    @DisplayName("setup")
    public void setup(){
        PDAcroForm acroForm = null;
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("blank_character.pdf");
            PDDocument template = PDDocument.load(stream);
            COSDocument lowTemplate = template.getDocument();
            PDDocument newCharacterSheet = new PDDocument(lowTemplate);
            lowTemplate.close();
            template.close();
            PDDocumentCatalog catalog = newCharacterSheet.getDocumentCatalog();
            acroForm = catalog.getAcroForm();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(acroForm);

        List<PDField> fields = acroForm.getFields();
        Assertions.assertNotNull(fields);

        Assertions.assertFalse(fields.isEmpty());

        PDField firstField = fields.get(0);
        Assertions.assertEquals("ClassLevel", firstField.getPartialName());
        Assertions.assertEquals("", firstField.getValueAsString());
    }
}

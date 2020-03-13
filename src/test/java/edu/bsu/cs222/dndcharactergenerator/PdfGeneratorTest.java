package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class PdfGeneratorTest {
    private static Character character = new Character();
    private File writtenPDf = null;
    private PDDocument testDoc;
    private PDAcroForm testForm;

    @BeforeEach
    public void setup() {
        character.setName("Hellen");
        character.setRace("Dwarf: +2 CON");
        character.setCHA(3);
        character.setCON(3);
        character.setDEX(3);
        character.setINT(3);
        character.setSTR(3);
        character.setWIS(3);

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

    @Test
    public void testStatsWrittenCorrectly() {
        PDTextField dexField = (PDTextField) testForm.getField("DEX");
        PDTextField strField = (PDTextField) testForm.getField("STR");
        PDTextField conField = (PDTextField) testForm.getField("CON");
        PDTextField intField = (PDTextField) testForm.getField("INT");
        PDTextField wisField = (PDTextField) testForm.getField("WIS");
        PDTextField chaField = (PDTextField) testForm.getField("CHA");
        Assertions.assertEquals(3, Integer.parseInt(dexField.getValue()));
        Assertions.assertEquals(3, Integer.parseInt(strField.getValue()));
        Assertions.assertEquals(3, Integer.parseInt(conField.getValue()));
        Assertions.assertEquals(3, Integer.parseInt(intField.getValue()));
        Assertions.assertEquals(3, Integer.parseInt(wisField.getValue()));
        Assertions.assertEquals(3, Integer.parseInt(chaField.getValue()));
    }

    @Test
    public void testModsWrittenCorrectly() {
        PDTextField strMod = (PDTextField) testForm.getField("STRmod");
        PDTextField dexMod = (PDTextField) testForm.getField("DEXmod ");
        PDTextField conMod = (PDTextField) testForm.getField("CONmod");
        PDTextField intMod = (PDTextField) testForm.getField("INTmod");
        PDTextField wisMod = (PDTextField) testForm.getField("WISmod");
        PDTextField chaMod = (PDTextField) testForm.getField("CHamod");
        Assertions.assertEquals(-4, Integer.parseInt(strMod.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(dexMod.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(conMod.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(intMod.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(wisMod.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(chaMod.getValue()));
    }

    @Test
    public void testInitiativeWrittenCorrectly() {
        PDTextField initiative = (PDTextField) testForm.getField("Initiative");
        Assertions.assertEquals(-4, Integer.parseInt(initiative.getValue()));
    }

    @Test
    public void testHitPointsWrittenCorrectly() {
        PDTextField maxHitPoints = (PDTextField) testForm.getField("HPMax");
        PDTextField currentHitPoints = (PDTextField) testForm.getField("HPCurrent");
        PDTextField totalHitPoints = (PDTextField) testForm.getField("HDTotal");
        Assertions.assertEquals(6, Integer.parseInt(maxHitPoints.getValueAsString()));
        Assertions.assertEquals(6, Integer.parseInt(currentHitPoints.getValueAsString()));
        Assertions.assertEquals(6, Integer.parseInt(totalHitPoints.getValueAsString()));
    }

    @Test
    public void testSavingThrowsWrittenCorrectly() {
        PDTextField strSavingThrow = (PDTextField) testForm.getField("ST Strength");
        PDTextField dexSavingThrow = (PDTextField) testForm.getField("ST Dexterity");
        PDTextField conSavingthrow = (PDTextField) testForm.getField("ST Constitution");
        PDTextField intSavingThrow = (PDTextField) testForm.getField("ST Intelligence");
        PDTextField wisSavingThrow = (PDTextField) testForm.getField("ST Wisdom");
        PDTextField chaSavingThrow = (PDTextField) testForm.getField("ST Charisma");
        Assertions.assertEquals(-2, Integer.parseInt(strSavingThrow.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(dexSavingThrow.getValue()));
        Assertions.assertEquals(-2, Integer.parseInt(conSavingthrow.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(intSavingThrow.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(wisSavingThrow.getValue()));
        Assertions.assertEquals(-4, Integer.parseInt(chaSavingThrow.getValue()));
    }

    @Test
    public void testArmorClassWrittenCorrectly() {
        PDTextField armorClass = (PDTextField) testForm.getField("AC");
        Assertions.assertEquals(6, Integer.parseInt(armorClass.getValue()));
    }

    @Test
    public void testStyleWrittenCorrectly() {
        PDTextField featuresAndTraits = (PDTextField) testForm.getField("Features and Traits");
        Assertions.assertEquals("foo-bar\n\nSecond Wind:\nYou have a limited well of stamina that you can draw on to protect yourself from harm.  " +
                "On your turn, you can use a bonus action to regain hit " +
                "points equal to 1d10+your fighter level (1).", featuresAndTraits.getValue());
    }

    @Test
    public void testRaceWrittenCorrectly() {
        PDTextField raceField = (PDTextField) testForm.getField("Race ");
        Assertions.assertEquals("Dwarf", raceField.getValue());
    }

    @Test
    public void testCharacterNameWrittenCorrectly() {
        PDTextField charName1 = (PDTextField) testForm.getField("CharacterName");
        Assertions.assertEquals("Hellen", charName1.getValue());
    }

    @AfterEach
    public void tearDown() throws IOException {
        testDoc.close();
        writtenPDf.delete();
    }
}

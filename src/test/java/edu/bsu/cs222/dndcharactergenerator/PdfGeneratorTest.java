package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class PdfGeneratorTest {
    private static Character character = new Character();
    private PdfGenerator generator;

    @BeforeEach
    public void setup() {
        character.setName("Hellen");
        character.setRace("Dwarf");
        character.setCHA(3);
        character.setCON(3);
        character.setDEX(3);
        character.setINT(3);
        character.setSTR(3);
        character.setWIS(3);

        character.setRacialAttribute(RacialAttribute.MOUNTAINDWARF);
        character.setStyle("foo-bar");

        generator = new PdfGenerator.Builder()
                .setCharacter(character)
                .build();
        Assertions.assertNotNull(character);
        Assertions.assertNotNull(generator);
    }

    @Test
    public void testGenerator() {
        try {
            File file = new File("sheet_test.pdf");
            generator.writeNewCharacterSheet(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

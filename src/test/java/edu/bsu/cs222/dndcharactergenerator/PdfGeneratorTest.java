package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PdfGeneratorTest {
    private static Character character = new Character();
    private PdfGenerator generator;

    @BeforeEach
    public void setup() {
        character.setName("Hellen");
        character.setRace("Dwarf");
        character.setCHA(20);
        character.setCON(20);
        character.setDEX(20);
        character.setINT(20);
        character.setSTR(20);
        character.setWIS(20);

        character.setRacialAttribute(RacialAttribute.MOUNTAINDWARF);
        character.setStyleLiteral("foo-bar");

        generator = new PdfGenerator.Builder()
                .setCharacter(character)
                .build();
        Assertions.assertNotNull(character);
        Assertions.assertNotNull(generator);
    }

    @Test
    public void testGenerator() {
        try {
            generator.writeNewCharacterSheet("sheet_test.pdf");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}

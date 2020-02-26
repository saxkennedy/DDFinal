package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PdfGeneratorTest {
    private static Character character = new Character();
    private PdfGenerator generator;

    @BeforeEach
    public void setup() {
        character.setName("Hellen");
        character.setRace("Dwarf");
        character.setCHA(1);
        character.setCON(1);
        character.setDEX(1);
        character.setINT(1);
        character.setSTR(1);
        character.setWIS(1);

        character.setRacialAttribute("Mountain Dwarf: +2 STR");
        character.setStyleLiteral("foo-bar");

        generator = new PdfGenerator.Builder()
                .setCharacter(character)
                .build();
        Assertions.assertNotNull(character);
        Assertions.assertNotNull(generator);
    }

    @Test
    public void testGenerator() {

    }
}

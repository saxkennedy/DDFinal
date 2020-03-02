package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

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

        character.setRacialAttribute(RacialAttribute.MOUNTAINDWARF);
        character.setStyleLiteral("foo-bar");

        generator = new PdfGenerator.Builder()
                .setCharacter(character)
                .build();
        Assertions.assertNotNull(character);
        Assertions.assertNotNull(generator);
    }

//    @Test
//    public void testGenerator() {
//        try {
//            generator.writeNewCharacterSheet("sheet_test.pdf");
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
}

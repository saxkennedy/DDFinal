package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    Character player = new Character();

    @Test
    public void testSettingName(){
        player.setName("Test Name");
        Assertions.assertEquals("Test Name", player.getName());
    }

    @Test
    public void testGetsRace(){
        player.setRace("Human");
        Assertions.assertEquals(Race.HUMAN, player.getRace());
    }

    @Test
    public void testRemoveRaceModifier(){
        player.setRace("Dragonborn");
        player.setRace("Human");
        Assertions.assertEquals(1, player.getSTR());
    }

    @Test
    public void testRacialAttributeScoreBonus(){
        player.setRacialAttribute("Hill Dwarf: +1 WIS");
        player.setRacialAttribute("Mountain Dwarf: +2 STR");
        Assertions.assertEquals(0, player.getWIS());
        Assertions.assertEquals(2, player.getSTR());
    }

    @Test
    public void testSetsFightingStyle(){
        player.setStyleLiteral("Archery");
        Assertions.assertEquals("Archery", player.getStyleLiteral());


    }

}

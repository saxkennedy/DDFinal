package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    Character player = new Character();

    @Test
    public void testSettingName() {
        player.setName("Test Name");
        Assertions.assertEquals("Test Name", player.getName());
    }

    @Test
    public void testGetsRace() {
        player.setRace("Human: +1 TO ALL STATS");
        Assertions.assertEquals(Race.HUMAN, player.getRace());
    }

    @Test
    public void testRemoveRaceModifier() {
        player.setRace("Dragonborn: +2 STR, +1 CHA");
        player.addRacialAbilityScoreBonus();
        player.removeRacialAbilityScoreBonus();
        player.setRace("Human: +1 TO ALL STATS");
        player.addRacialAbilityScoreBonus();
        Assertions.assertEquals(1, player.getSTR());
    }

    @Test
    public void testRacialAttributeScoreBonus() {
        player.setRacialAttribute(RacialAttribute.HILLDWARF);
        player.setRacialAttribute(RacialAttribute.MOUNTAINDWARF);
        Assertions.assertEquals(0, player.getWIS());
        Assertions.assertEquals(2, player.getSTR());
    }

    @Test
    public void testSetsFightingStyle() {
        player.setStyle("Archery"+":\n"+"+2 bonus to attack rolls made with ranged weapons");
        Assertions.assertEquals("Archery"+":\n"+"+2 bonus to attack rolls made with ranged weapons", player.getStyle());
    }

}

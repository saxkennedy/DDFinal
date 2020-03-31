package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CharacterTest {
    private Character player;

    @BeforeEach
    public void testSettingName() {
        player = new Character();
        player.setName("Test Name");
        Assertions.assertEquals("Test Name", player.getName());
    }

    @ParameterizedTest
    @CsvSource({"20,5", "10,0"})
    public void testModifierCalculation(int input, int expected) {
        Assertions.assertEquals(expected, player.modifierCalculation(input));
    }

    @Test
    public void testSetRaceBeforeStats() {
        player.setRace(Race.HALFORC);
        player.setAbilityScore(AbilityScore.STR, 10);
        int strValue = player.getAttribute(AbilityScore.STR);
        Assertions.assertEquals(12, strValue);
    }

    @Test
    public void testSetStatsBeforeRace() {
        player.setAbilityScore(AbilityScore.STR, 10);
        player.setRace(Race.HALFORC);
        int strValue = player.getAttribute(AbilityScore.STR);
        Assertions.assertEquals(12, strValue);
    }

    @Test
    public void testGetRace() {
        player.setRace(Race.HUMAN);
        Assertions.assertEquals(Race.HUMAN, player.getRace());
    }

    @Test
    public void testRemoveRaceModifier() {
        player.setRace(Race.DRAGONBORN);
        player.setRace(Race.HUMAN);
        Assertions.assertEquals(1, player.getAttribute(AbilityScore.STR));
    }

    @Test
    public void testRacialAttributeScoreBonus() {
        player.setRacialAttribute(RacialAttribute.HILLDWARF);
        player.setRacialAttribute(RacialAttribute.MOUNTAINDWARF);
        Assertions.assertEquals(0, player.getAttribute(AbilityScore.WIS));
        Assertions.assertEquals(2, player.getAttribute(AbilityScore.STR));
    }

    @Test
    public void testSetsFightingStyle() {
        player.setStyle("Archery" + ":\n" + "+2 bonus to attack rolls made with ranged weapons");
        Assertions.assertEquals("Archery" + ":\n" + "+2 bonus to attack rolls made with ranged weapons", player.getStyle());
    }

    @BeforeEach
    public void setupConChange() {
        player = new Character();
        player.setName("Test Name");
        player.setAbilityScore(AbilityScore.CON, 20);
        player.setAbilityScore(AbilityScore.DEX, 20);
        Assertions.assertEquals(20, player.getAttribute(AbilityScore.CON));
    }

    @Test
    public void assertConModSavedCorrectly() {
        Assertions.assertEquals(5, player.getAttribute(AbilityScoreModifier.CON_MOD));
    }

    @Test
    public void assertHpSavedCorrectly() {
        Assertions.assertEquals(15, player.getAttribute(VitalityModifier.MAX_HP));
    }

    @BeforeEach
    public void setUpDexChange() {
        player = new Character();
        player.setName("Test Name");
        Assertions.assertEquals("Test Name", player.getName());
        player.setAbilityScore(AbilityScore.DEX, 20);
        Assertions.assertEquals(20, player.getAttribute(AbilityScore.DEX));
    }

    @Test
    public void assertDexModSavedCorrectly() {
        Assertions.assertEquals(5, player.getAttribute(AbilityScoreModifier.DEX_MOD));
    }

    @Test
    public void assertACUpdateCorrectly() {
        Assertions.assertEquals(15, player.getAttribute(VitalityModifier.ARMOR_CLASS));
    }

}

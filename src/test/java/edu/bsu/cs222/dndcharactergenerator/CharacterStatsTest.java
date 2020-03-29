package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CharacterStatsTest {

    CharacterStats stats = new CharacterStats();

    @ParameterizedTest
    @CsvSource({"20,5", "10,0"})
    public void testModifierCalculation(int input, int expected) {
        Assertions.assertEquals(expected, stats.modifierCalculation(input));
    }


    @BeforeEach
    public void setupConChange() {
        stats.setAttribute(AbilityScore.CON, 20);
    }


    @Test
    public void assertConModSavedCorrectly() {
        Assertions.assertEquals(5, stats.getAttribute(AbilityScoreModifier.CON_MOD));
    }


    @Test
    public void assertHpSavedCorrectly() {
        Assertions.assertEquals(15, stats.getAttribute(VitalityModifier.MAX_HP));
    }

    @BeforeEach
    public void setUpDexChange() {
        stats.setAttribute(AbilityScore.DEX, 20);
    }

    @Test
    public void assertDexModSavedCorrectly() {
        Assertions.assertEquals(5, stats.getAttribute(AbilityScoreModifier.DEX_MOD));
    }

    @Test
    public void assertACUpdateCorrectly() {
        Assertions.assertEquals(15, stats.getAttribute(VitalityModifier.ARMOR_CLASS));
    }
}

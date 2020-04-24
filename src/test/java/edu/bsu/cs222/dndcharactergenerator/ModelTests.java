package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTests {
    Character player;

    @BeforeEach
    public void setup() {
        player = new Character();
        player.setRace(Race.DWARF);
        player.setSubrace(Subrace.MOUNTAINDWARF);
        player.setAbilityScore(AbilityScore.STR, 10);
        player.buildfinalCharacterStats();
    }

    @Test
    public void testStrengthIs12() {
        Assertions.assertEquals(12, player.getAttribute(AbilityScore.STR));
    }

    @Test
    public void testStrengthModifierIs1() {
        Assertions.assertEquals(1, player.getAttribute(AbilityScoreModifier.STR_MOD));
    }
}

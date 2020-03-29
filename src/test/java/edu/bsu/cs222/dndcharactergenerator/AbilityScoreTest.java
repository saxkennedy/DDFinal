package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbilityScoreTest {
    AbilityScore[] abilityScores = AbilityScore.values();
    AbilityScoreModifier[] actualAbilityScoreModifiers = AbilityScoreModifier.values();
    AbilityScoreModifier[] generatedAbilityScoreModifiers;


    @BeforeEach
    public void generateAbilityScoreModifiers() {
        generatedAbilityScoreModifiers = new AbilityScoreModifier[6];
        Assertions.assertNotNull(abilityScores);
        for(int i = 0; i < abilityScores.length; i++) {
            generatedAbilityScoreModifiers[i] = abilityScores[i].modifier;
        }
    }

    @Test
    public void testGetModifier() {
        Assertions.assertArrayEquals(actualAbilityScoreModifiers, generatedAbilityScoreModifiers);
    }
}

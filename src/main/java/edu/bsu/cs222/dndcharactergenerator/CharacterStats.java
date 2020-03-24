package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class CharacterStats {
    private Map<CharacterAttribute, Integer> characterAttributes = new HashMap<>();

    public void implementAbilityScoreAffector(AbilityScoreAffecter affecter, int additionOrSubtraction) {
        populateAttributesWithZero();
        Map<AbilityScore, Integer> abilityScoreIntegerMap = affecter.getAbilityScoreChanges();
        for (Map.Entry<AbilityScore, Integer> entry : abilityScoreIntegerMap.entrySet()) {
            CharacterAttribute specifier = entry.getKey();
            characterAttributes.put(specifier, characterAttributes.get(specifier) + (additionOrSubtraction * entry.getValue()));
        }
    }

    public Map<CharacterAttribute, Integer> getFinalAttributeMap() {
        return new HashMap<>(characterAttributes);
    }

    public void setAttribute(CharacterAttribute specifier, int value) {
        if (characterAttributes.isEmpty()) {
            populateAttributesWithZero();
        }
        characterAttributes.put(specifier, value);
    }

    public int getAttribute(CharacterAttribute specifier) {
        return characterAttributes.get(specifier);
    }

    private void populateAttributesWithZero() {
        for(CharacterAttribute abilityScore : AbilityScore.values()) {
            characterAttributes.put(abilityScore, 0);
        }
        for(CharacterAttribute abilityScoreModifier : AbilityScoreModifier.values()) {
            characterAttributes.put(abilityScoreModifier, 0);
        }
        for(CharacterAttribute abilityScore : AbilityScoreSavingThrow.values()) {
            characterAttributes.put(abilityScore, 0);
        }
        for(CharacterAttribute vitalityModifier : VitalityModifier.values()) {
            characterAttributes.put(vitalityModifier, 0);
        }
    }

    public static CharacterStats makeAttributesCopy(CharacterStats oldStats) throws CloneNotSupportedException {
        return (CharacterStats) oldStats.clone();
    }
}

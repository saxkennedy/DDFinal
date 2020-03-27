package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class CharacterStats {
    private Map<CharacterAttribute, Integer> attributeMap = new HashMap<>();

    public void implementAbilityScoreAffector(AbilityScoreAffecter affecter, int additionOrSubtraction) {
        populateAttributesWithZero();
        Map<AbilityScore, Integer> abilityScoreIntegerMap = affecter.getAbilityScoreChanges();
        for (Map.Entry<AbilityScore, Integer> entry : abilityScoreIntegerMap.entrySet()) {
            CharacterAttribute specifier = entry.getKey();
            attributeMap.put(specifier, attributeMap.get(specifier) + (additionOrSubtraction * entry.getValue()));
        }
    }

    public Map<CharacterAttribute, Integer> getFinalAttributeMap() {
        return new HashMap<>(attributeMap);
    }

    public void setAttribute(CharacterAttribute specifier, int value) {
        if (attributeMap.isEmpty()) {
            populateAttributesWithZero();
        }
        attributeMap.put(specifier, value);
    }

    public int getAttribute(CharacterAttribute specifier) {
        return attributeMap.get(specifier);
    }

    private void populateAttributesWithZero() {
        for (CharacterAttribute abilityScore : AbilityScore.values()) {
            attributeMap.put(abilityScore, 0);
        }
        for (CharacterAttribute abilityScoreModifier : AbilityScoreModifier.values()) {
            attributeMap.put(abilityScoreModifier, 0);
        }
        for (CharacterAttribute abilityScore : AbilityScoreSavingThrow.values()) {
            attributeMap.put(abilityScore, 0);
        }
        for (CharacterAttribute vitalityModifier : VitalityModifier.values()) {
            attributeMap.put(vitalityModifier, 0);
        }
    }

    public static CharacterStats makeAttributesCopy(CharacterStats oldStats) throws CloneNotSupportedException {
        return (CharacterStats) oldStats.clone();
    }


}

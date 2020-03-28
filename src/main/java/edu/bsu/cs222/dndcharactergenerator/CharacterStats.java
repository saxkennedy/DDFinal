package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class CharacterStats {
    private Map<CharacterAttribute, Integer> attributeMap = new HashMap<>();

    public void implementAbilityScoreAffector(AbilityScoreAffecter affecter, int additionOrSubtraction) {
        zeroOutStatsIfEmpty();
        Map<AbilityScore, Integer> abilityScoreIntegerMap = affecter.getAbilityScoreChanges();
        for (Map.Entry<AbilityScore, Integer> entry : abilityScoreIntegerMap.entrySet()) {
            CharacterAttribute specifier = entry.getKey();
            int valueToBeAddedOrSubtracted = additionOrSubtraction * entry.getValue();
            attributeMap.put(specifier, attributeMap.get(specifier) + valueToBeAddedOrSubtracted);
        }
    }

    public Map<CharacterAttribute, Integer> getFinalAttributeMap() {
        return new HashMap<>(attributeMap);
    }

    public void setAttribute(CharacterAttribute specifier, int value) {
        zeroOutStatsIfEmpty();
        if (specifier instanceof AbilityScore) {
            AbilityScore abilityScore = (AbilityScore) specifier;
            attributeMap.put(abilityScore, value);
            setAttribute(abilityScore.modifier, value);
        }
        if(specifier instanceof AbilityScoreModifier) {
            int modifierValue = modifierCalculation(value);
            AbilityScoreModifier modifier = (AbilityScoreModifier) specifier;
            if(modifier.equals(AbilityScoreModifier.CON_MOD)){
                updateHpValues(modifierValue);
            }
            attributeMap.put(modifier, modifierValue);
        }
    }

    private void updateHpValues(int conModValue) {
        int lvl1Value = conModValue + 10;
        attributeMap.put(VitalityModifier.MAX_HP, lvl1Value);
        attributeMap.put(VitalityModifier.CURRENT_HP, lvl1Value);
        attributeMap.put(VitalityModifier.TOTAL_HP, lvl1Value);
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

    private void zeroOutStatsIfEmpty() {
        if(attributeMap.isEmpty()) {
            populateAttributesWithZero();
        }
    }

    public int modifierCalculation(int mainStatValue) {
        int minusTen = mainStatValue - 10;
        float divideInHalf = (float) (minusTen / 2);
        int floored = (int) Math.floor(divideInHalf);
        return floored;
    }
}

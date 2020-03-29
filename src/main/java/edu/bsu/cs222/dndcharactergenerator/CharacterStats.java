package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class CharacterStats {
    public Map<CharacterAttribute, Integer> attributeMap = new HashMap<>();        //RETURN TO PRIVATE

    public void implementAbilityScoreAffector(AbilityScoreAffecter affecter, int additionOrSubtraction) {
        zeroOutStatsIfEmpty();
        for (Map.Entry<AbilityScore, Integer> entry : affecter.getAbilityScoreChanges().entrySet()) {
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
            if(getAttribute(abilityScore) != 0) {
                attributeMap.put(abilityScore, getAttribute(abilityScore) + value);
            } else {
                attributeMap.put(abilityScore, value);
            }
            setAttribute(abilityScore.modifier, value);
        }
        if(specifier instanceof AbilityScoreModifier) {
            int modifierValue = modifierCalculation(value);
            AbilityScoreModifier modifier = (AbilityScoreModifier) specifier;
            if(getAttribute(modifier) != 0) {
                attributeMap.put(modifier, getAttribute(modifier) + modifierValue);
            } else {
                attributeMap.put(modifier, modifierValue);
            }
            runModifierTasks(modifier, modifierValue);
            attributeMap.put(modifier, modifierValue);
        }
    }

    private void runModifierTasks(AbilityScoreModifier modifier, int modifierValue) {
        switch(modifier) {
            case CON_MOD: updateHpValues(modifierValue); break;
            case DEX_MOD: updateAC(modifierValue); break;
        }
    }

    private void updateAC(int modifierValue) {
        attributeMap.put(VitalityModifier.ARMOR_CLASS, modifierValue + 10);
    }

    private void updateHpValues(int conModValue) {
        int lvl1Value = conModValue + 10;
        attributeMap.put(VitalityModifier.MAX_HP, lvl1Value);
        attributeMap.put(VitalityModifier.CURRENT_HP, lvl1Value);
        attributeMap.put(VitalityModifier.TOTAL_HP, lvl1Value);
    }

    public int getAttribute(CharacterAttribute specifier) {
        int value = attributeMap.get(specifier);
        return value;
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

package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public interface AbilityScoreChanger {
    Map<AbilityScore, Integer> getAbilityScoreChanges();
}

package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public interface AbilityScoreAffecter {
    Map<AbilityScore, Integer> getAbilityScoreChanges();
}

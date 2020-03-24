package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class CharacterStats {
    private Map<Stats, Integer> stats = new HashMap<>();

    public void addModifier(StatChanger modifier) {
        Map<Stats, Integer> statChanges = modifier.getStatChanges();
        makeChangesWithMap(statChanges, 1);
    }

    public void removeModifier(StatChanger modifier) {
        Map<Stats, Integer> statChanges = modifier.getStatChanges();
        makeChangesWithMap(statChanges, -1);
    }

    private void makeChangesWithMap(Map<Stats, Integer> statMap, int modifier) {
        for (Map.Entry<Stats, Integer> entry : statMap.entrySet()) {
            Stats specifier = entry.getKey();
            stats.put(specifier, stats.get(specifier) + (modifier * entry.getValue()));
        }
    }

    public Map<Stats, Integer> getFinalStatMap() {
        return new HashMap<>(stats);
    }

    public void setStat(Stats specifier, int value) {
        if (stats.isEmpty()) {
            populateStatsWithZero();
        }
        stats.put(specifier, value);
    }

    public int getStat(Stats specifier) {
        return stats.get(specifier);
    }

    private void populateStatsWithZero() {
        for (Stats specifier : Stats.values()) {
            stats.put(specifier, 0);
        }
    }

    public static CharacterStats makeStatsCopy(CharacterStats oldStats) throws CloneNotSupportedException {
        return (CharacterStats) oldStats.clone();
    }
}

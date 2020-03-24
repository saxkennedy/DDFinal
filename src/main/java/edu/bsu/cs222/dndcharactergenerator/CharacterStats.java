package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class CharacterStats {
    private Map<StatName, Integer> stats = new HashMap<>();

    public void addModifier(StatChanger modifier) {
        Map<StatName, Integer> statChanges = modifier.getStatChanges();
        makeChangesWithMap(statChanges, 1);
    }

    public void removeModifier(StatChanger modifier) {
        Map<StatName, Integer> statChanges = modifier.getStatChanges();
        makeChangesWithMap(statChanges, -1);
    }

    private void makeChangesWithMap(Map<StatName, Integer> statMap, int modifier) {
        for(Map.Entry<StatName, Integer> entry: statMap.entrySet()) {
            StatName specifier = entry.getKey();
            stats.put(specifier, stats.get(specifier) + (modifier * entry.getValue()));
        }
    }

    public Map<StatName, Integer> getFinalStatMap() {
        return new HashMap<>(stats);
    }

    public void setStat(StatName specifier, int value) {
        initMapIfEmpty();
        stats.put(specifier, value);
    }

    public int getStat(StatName specifier) {
        return stats.get(specifier);
    }

    private void initMapIfEmpty() {
        if(stats.isEmpty()) {
            zeroOutStats();
        }
    }

    private void zeroOutStats() {
        for(StatName specifier : StatName.values()) {
            stats.put(specifier, 0);
        }
    }
}

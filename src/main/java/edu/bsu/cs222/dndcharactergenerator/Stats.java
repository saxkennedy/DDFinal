package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class Stats {
    private Map<StatSpecifier, Integer> stats;

    public Stats() {
        stats = new HashMap<>();
        zeroOutStats();
    }

    private void zeroOutStats() {
        for(StatSpecifier specifier : StatSpecifier.values()) {
            stats.put(specifier, 0);
        }
    }

    public void addToCurrentStats(Map<StatSpecifier, Integer> statMap) {
        for(Map.Entry<StatSpecifier, Integer> entry: statMap.entrySet()) {
            stats.put(entry.getKey(), entry.getValue());
        }
    }

    public void setStat(StatSpecifier specifier, int value) {
        stats.put(specifier, value);
    }

    public int getStat(StatSpecifier specifier) {
        return stats.get(specifier);
    }

    public void changeStatsViaStatModifier(StatModifier statModifier) {
        Map<StatSpecifier, Integer> statsFromChanger = statModifier.getStatAdditions();
        addToCurrentStats(statsFromChanger);
    }

    public Map<StatSpecifier, Integer> getStats() {
        Map<StatSpecifier, Integer> newStats = new HashMap<>();
        newStats.putAll(stats);
        return newStats;
    }
}

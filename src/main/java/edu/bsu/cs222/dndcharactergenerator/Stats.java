package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;

public class Stats {
    private Map<StatSpecifier, Integer> stats = new HashMap<>();

    public void changeStatsViaStatModifier(StatModifier statModifier) {
        Map<StatSpecifier, Integer> statsFromChanger = statModifier.getStatAdditions();
        initMapIfEmpty();
        addToCurrentStats(statsFromChanger);
    }

    public void addToCurrentStats(Map<StatSpecifier, Integer> statMap) {
        for(Map.Entry<StatSpecifier, Integer> entry: statMap.entrySet()) {
            stats.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<StatSpecifier, Integer> getStats() {
        Map<StatSpecifier, Integer> newStats = new HashMap<>();
        newStats.putAll(stats);
        return newStats;
    }

    public void setStat(StatSpecifier specifier, int value) {
        initMapIfEmpty();
        stats.put(specifier, value);
    }

    public int getStat(StatSpecifier specifier) {
        return stats.get(specifier);
    }

    private void initMapIfEmpty() {
        if(stats.isEmpty()) {
            zeroOutStats();
        }
    }

    private void zeroOutStats() {
        for(StatSpecifier specifier : StatSpecifier.values()) {
            stats.put(specifier, 0);
        }
    }
}

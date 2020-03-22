package edu.bsu.cs222.dndcharactergenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Stats {
    private Map<StatSpecifier, Integer> stats;

    public Stats() {
        stats = new HashMap<>();
    }

    public void setStat(StatSpecifier specifier, int value) {
        Set<StatSpecifier> currentStats = stats.keySet();
        if (currentStats.contains(specifier)) {
            stats.put(specifier, stats.get(specifier) + value);
        } else {
            stats.put(specifier, value);
        }
    }

    public int getStat(StatSpecifier specifier) {
        return stats.get(specifier);
    }

    public void modifyStatsViaStatChanger(StatChanger statChanger) {
        Map<StatSpecifier, Integer> statsFromChanger = statChanger.getStatAdditions();
        for (StatSpecifier specifier : statsFromChanger.keySet()) {
            setStat(specifier, statsFromChanger.get(specifier));
        }
    }

    public Map<StatSpecifier, Integer> getStats() {
        return stats;
    }
}

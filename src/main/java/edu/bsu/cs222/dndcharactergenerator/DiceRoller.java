package edu.bsu.cs222.dndcharactergenerator;

import java.util.ArrayList;
import java.util.Collections;

public class DiceRoller {

    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> sorter = new ArrayList<>();
            sorter.add((int) (Math.random() * 6 + 1));
            sorter.add((int) (Math.random() * 6 + 1));
            sorter.add((int) (Math.random() * 6 + 1));
            sorter.add((int) (Math.random() * 6 + 1));
            Collections.sort(sorter);
            sorter.remove(0);
            int sum = sorter.get(0) + sorter.get(1) + sorter.get(2);
            stats.add(sum);
        }
        return stats;
    }
}
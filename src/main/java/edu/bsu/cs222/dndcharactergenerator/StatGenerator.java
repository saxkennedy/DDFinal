package edu.bsu.cs222.dndcharactergenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class StatGenerator {

    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> sorter = new ArrayList<>();
            sorter.add((int) Math.random() * 6 + 1);
            sorter.add((int) Math.random() * 6 + 1);
            sorter.add((int) Math.random() * 6 + 1);
            sorter.add((int) Math.random() * 6 + 1);
            sorter.remove(0);
            int sum = sorter.get(1) + sorter.get(2) + sorter.get(3);
            stats.set(i,sum);
        }
        return stats;
    }
}
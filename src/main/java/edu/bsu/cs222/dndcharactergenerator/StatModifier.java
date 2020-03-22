package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public interface StatModifier {
    Map<StatSpecifier, Integer> getStatAdditions();
}

package edu.bsu.cs222.dndcharactergenerator;

import java.util.Map;

public interface StatChanger {
    Map<StatSpecifier, Integer> getStatAdditions();
}

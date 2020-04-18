package edu.bsu.cs222.dndcharactergenerator;

import java.util.Arrays;
import java.util.List;

public class Handbook {
    public List<Race> getRaces() { return Arrays.asList(Race.values());}

    public List<Subrace> getSubracesOfRace(Race race) { return race.subraces != null ? race.subraces : Race.ZEROMAN.subraces; }
}

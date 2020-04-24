package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HandbookTest {
    Handbook handbook;

    @BeforeEach
    public void setup() {
        handbook = new Handbook();
    }

    @Test
    public void testGetRaces() {
        List<Race> expected = Arrays.asList(Race.values());
        List<Race> actual = handbook.getRaces();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetSubracesOfRace() {
        List<Subrace> expected = Race.DWARF.subraces;
        List<Subrace> actual = handbook.getSubracesOfRace(Race.DWARF);
    }
}

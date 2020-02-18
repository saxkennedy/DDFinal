package edu.bsu.cs222.dndcharactergenerator;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Character {
    String name;
    String race;

    public void setStats() {
        StatGenerator stats = new StatGenerator();
        ArrayList statBlock=stats.getStats();
        System.out.println(statBlock);
    }
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
        System.out.println("Race changed to "+race);
    }


    public void setName(String name) {
        this.name = name;
        System.out.println("Name changed to "+name);
    }
    public String getName() {
        return name;
    }

}

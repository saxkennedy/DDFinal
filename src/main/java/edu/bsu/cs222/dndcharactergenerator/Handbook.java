package edu.bsu.cs222.dndcharactergenerator;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Handbook {
    Handbook(){generateBackgroundArray();}
    public CharacterBackground[] characterBackgroundArray;
    public List<Race> getRaces() { return Arrays.asList(Race.values());}

    public List<Subrace> getSubracesOfRace(Race race) { return race.subraces != null ? race.subraces : Race.ZEROMAN.subraces; }

    public CharacterBackground[] generateBackgroundArray(){
        BackgroundParser parser = new BackgroundParser();
        try {
            characterBackgroundArray =parser.setBackgroundFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterBackgroundArray;

    }
}

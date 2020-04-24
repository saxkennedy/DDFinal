package edu.bsu.cs222.dndcharactergenerator;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Handbook {
    Handbook() {
        generateBackgroundArray();
    }

    public CharacterBackground[] characterBackgroundArray;

    public List<Race> getRaces() {
        return Arrays.asList(Race.values());
    }

    public String[] raceNames = new String[]{"Half-Orc: +2 STR, +1 CON", "Dragonborn: +2 STR, +1 CHA", "Dwarf: +2 CON", "Elf: +2 DEX", "Gnome: +2 INT", "Half-Elf: +2 CHA", "Halfling: +2 DEX", "Human: +1 TO ALL STATS", "Tiefling: +2 CHA, +1 INT"};
    public String[] styles = new String[]{"Archery", "Defense", "Dueling", "Great Weapon Fighting", "Protection", "Two-Weapon Fighting",};

    public List<Subrace> getSubracesOfRace(Race race) {
        return race.subraces != null ? race.subraces : Race.ZEROMAN.subraces;
    }

    public CharacterBackground[] generateBackgroundArray() {
        BackgroundParser parser = new BackgroundParser();
        try {
            characterBackgroundArray = parser.setBackgroundFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterBackgroundArray;

    }

    public String getStyleDescription(String style) {
        String styleDescription;
        switch (style) {
            case "Archery":
                styleDescription = "+2 bonus to attack rolls made with ranged weapons";
                break;
            case "Defense":
                styleDescription = "+1 bonus to AC while wearing armor";
                break;
            case "Dueling":
                styleDescription = "+2 bonus to damage rolls when holding a single weapon";
                break;
            case "Great Weapon Fighting":
                styleDescription = "You may re-roll the damage die while holding a two-handed weapon if you rolled a 1 or a 2";
                break;
            case "Protection":
                styleDescription = "When wielding a shield, you may impose a disadvantage on an enemy\n creature's attack roll when it attacks\na target" +
                        " other than you that is both within 5 feet of you and in your sight";
                break;
            case "Two-Weapon Fighting":
                styleDescription = "When you engage in two weapon fighting,\n you can add you ability modifier to the damage of the second attack";
                break;
            default:
                styleDescription = "No style found";
                break;
        }
        return styleDescription;
    }

}

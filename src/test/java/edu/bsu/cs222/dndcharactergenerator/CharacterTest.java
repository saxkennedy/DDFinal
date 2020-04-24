package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {
    Character character;

    @BeforeEach
    public void setup() {
        character = new Character();
        character.setName("Test");
    }

    @Test
    public void testSetAbilityScore() {
        character.setAbilityScore(AbilityScore.STR, 10);
        int expected = 10;
        int actual = character.getAttribute(AbilityScore.STR);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSetAbilityScoreThenSetRace(){
        character.setAbilityScore(AbilityScore.STR,10);
        character.setRace(Race.DRAGONBORN);
        int expected = 12;
        int actual = character.getAttribute(AbilityScore.STR);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testSetRaceThenSetAbilityScore(){
        character.setRace(Race.DRAGONBORN);
        character.setAbilityScore(AbilityScore.STR,10);
        int expected = 12;
        int actual = character.getAttribute(AbilityScore.STR);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testSetAbilityScoreModifier(){
        character.setAbilityScore(AbilityScore.STR,20);
        int expected = 5;
        int actual = character.getAttribute(AbilityScore.STR.modifier);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testSetRacialAttribute(){
        character.setRace(Race.HALFELF);
        character.setSubrace(Subrace.HIGHELF);
        int expected = 1;
        int actual = character.getAttribute(AbilityScore.INT);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testCheckBoxFlipper(){
        character.checkCounter = 0;
        character.checkBoxFlipper(Skill.ACROBATICS);
        Assertions.assertEquals(Skill.ACROBATICS,character.fighterSkill1);
    }

    @Test
    public void testSetProficiencySkillsMap() {
        character.backgroundSkill1 = Skill.ACROBATICS;
        character.backgroundSkill2 = Skill.HISTORY;
        character.fighterSkill1 = Skill.ATHLETICS;
        character.fighterSkill2 = Skill.ANIMALHANDLING;

        character.setProficiencySkillsMap();

        for (Skill s : character.selectedSkillsMap.keySet()) {
            int actual = character.selectedSkillsMap.get(s);
            Assertions.assertEquals(2, actual);
        }
    }

    @Test
    public void testGetRace(){
        character.setRace(Race.HALFELF);
        Race expected = Race.HALFELF;
        Race actual = character.getRace();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testGetAttribute(){
        character.setAbilityScore(AbilityScore.STR,100);
        int expected = 100;
        int actual = character.getAttribute(AbilityScore.STR);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testSetAndGetName(){
        character.setName("Pat Metheny");
        String expected = ("Pat Metheny");
        String actual  = character.getName();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testSetAndGetStyle(){
        character.setStyle("Defense");
        String expected = "Defense";
        String actual = character.getStyle();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testGetStyleDescription(){
        character.setStyle("Archery");
        String expected = "+2 bonus to attack rolls made with ranged weapons";
        String actual = character.getStyleDescription("Archery");
        Assertions.assertEquals(expected,actual);
    }
}

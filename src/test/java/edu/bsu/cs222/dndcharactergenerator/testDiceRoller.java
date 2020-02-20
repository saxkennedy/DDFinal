package edu.bsu.cs222.dndcharactergenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testDiceRoller {

    DiceRoller diceRoller = new DiceRoller();
    ArrayList<Integer> diceRolls = diceRoller.getStats();

    @Test
    public void testHasStats(){
        Assertions.assertNotNull(diceRolls);
    }

    @Test
    public void testRollsInBounds(){
        boolean withinBounds = false;
        for(Integer stat: diceRolls){
            if (stat <= 18 && stat >= 3){
                withinBounds = true;
                Assertions.assertTrue(withinBounds);
            }
        }
    }
}

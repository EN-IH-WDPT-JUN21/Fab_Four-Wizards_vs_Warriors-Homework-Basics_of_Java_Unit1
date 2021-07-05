package test.java.com.ironhack.battlesimulator.model;

import main.java.com.ironhack.battlesimulator.model.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WarriorTest {

    @Test
    public void addWarrior_checkTruncationToMaxValues (){
        Warrior warrior = new Warrior("Test Warrior",201,51,11);

        Assertions.assertEquals(200,warrior.getHp());
        Assertions.assertEquals(50,warrior.getStamina());
        Assertions.assertEquals(10,warrior.getStrength());
        Assertions.assertEquals("Test Warrior",warrior.getName());
        Assertions.assertTrue(warrior.isAlive());

        warrior.setHp(warrior.getHp()-201);

        Assertions.assertFalse(warrior.isAlive());
        Assertions.assertEquals(0,warrior.getHp());
    }

    @Test
    public void addWizard_checkTruncationToMinValues() {
        Warrior warrior = new Warrior("Test Warrior",20,9,0);

        Assertions.assertEquals(20,warrior.getHp());
        Assertions.assertEquals(10,warrior.getStamina());
        Assertions.assertEquals(1,warrior.getStrength());
        Assertions.assertEquals("Test Warrior",warrior.getName());
        Assertions.assertTrue(warrior.isAlive());
    }

}
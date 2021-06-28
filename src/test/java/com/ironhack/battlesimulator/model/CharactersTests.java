package com.ironhack.battlesimulator.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharactersTests {

    @Test
    public void WarriorTest (){
        Warrior warrior = new Warrior("Adam",400,100,30);

        Assertions.assertEquals(200,warrior.getHp());
        Assertions.assertEquals(100,warrior.getStamina());
        Assertions.assertEquals(30,warrior.getStrength());
        Assertions.assertEquals("Adam",warrior.getName());
        Assertions.assertTrue(warrior.isAlive());

        warrior.setHp(warrior.getHp()-300);

        Assertions.assertFalse(warrior.isAlive());
        Assertions.assertEquals(0,warrior.getHp());
    }

    @Test
    public void WizardTest (){
        Wizard wizard = new Wizard("Adam",400,100,30);

        System.out.println(wizard.getId());
        Assertions.assertEquals(100,wizard.getHp());
        Assertions.assertEquals(50,wizard.getMana());
        Assertions.assertEquals(30,wizard.getIntelligence());
        Assertions.assertEquals("Adam",wizard.getName());
        Assertions.assertTrue(wizard.isAlive());

        wizard.setHp(wizard.getHp()-300);

        Assertions.assertFalse(wizard.isAlive());
        Assertions.assertEquals(0,wizard.getHp());
    }
}

package com.ironhack.battlesimulator.service;

import com.ironhack.battlesimulator.model.Warrior;
import com.ironhack.battlesimulator.model.Wizard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttackTests {

    @Test
    public void warriorAttack() throws InterruptedException {
        Wizard wizard = new Wizard("Best Wizard",5,30,50);
        Warrior warrior = new Warrior("Best Warrior", 100,50,10);


        warrior.attack(wizard);

        Assertions.assertFalse(wizard.isAlive());
        Assertions.assertTrue(warrior.isAlive());
    }

    @Test
    public void wizardAttack() throws InterruptedException {
        Wizard wizard = new Wizard("Best Wizard",10,30,50);
        Warrior warrior = new Warrior("Best Warrior", 5,50,10);

        wizard.attack(warrior);

        Assertions.assertTrue(wizard.isAlive());
        Assertions.assertFalse(warrior.isAlive());
    }
}

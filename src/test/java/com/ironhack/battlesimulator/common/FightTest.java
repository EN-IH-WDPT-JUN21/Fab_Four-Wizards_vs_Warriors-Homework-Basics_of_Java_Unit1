package com.ironhack.battlesimulator.common;

import static org.junit.jupiter.api.Assertions.*;

import com.ironhack.battlesimulator.model.Warrior;
import com.ironhack.battlesimulator.model.Wizard;
import com.ironhack.battlesimulator.model.Character;
import com.ironhack.battlesimulator.service.Attacker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FightTest {

    static Fight fight;
    static Warrior warrior;
    static Wizard wizard;
    static Character character;

    @BeforeAll
    public static void setup() {
        fight = new Fight();
    }

    @Test
    public void getRandomIdTest() {
        String dictionary = "abcdefghijklmnoprstuwxyz1234567890";
        String newId = Fight.getRandomID();
        assertEquals(6, newId.length());
        for(int i = 0; i < newId.length(); i++) {
            assert(dictionary.contains(newId.valueOf(newId.charAt(i))));
        }
    }
}

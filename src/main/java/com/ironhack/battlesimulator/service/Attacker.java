package com.ironhack.battlesimulator.service;

import com.ironhack.battlesimulator.model.Character;

public interface Attacker {

    void attack(Character character) throws InterruptedException;
}

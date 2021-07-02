package com.ironhack.battlesimulator.service;

import com.ironhack.battlesimulator.model.GeneralCharacter;

public interface Attacker {

    void attack(GeneralCharacter character) throws InterruptedException;
}

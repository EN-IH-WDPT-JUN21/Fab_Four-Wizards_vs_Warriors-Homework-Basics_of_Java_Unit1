package main.java.com.ironhack.battlesimulator.service;


import main.java.com.ironhack.battlesimulator.model.GeneralCharacter;

public interface Attacker {

    void attack(GeneralCharacter character) throws InterruptedException;
}

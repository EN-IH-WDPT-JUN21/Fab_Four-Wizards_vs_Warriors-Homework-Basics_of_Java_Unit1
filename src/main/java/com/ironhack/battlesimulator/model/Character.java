package main.java.com.ironhack.battlesimulator.model;

import main.java.com.ironhack.battlesimulator.common.RandomGenerator;
import com.ironhack.battlesimulator.service.Attacker;

import java.util.UUID;

public abstract class Character implements Attacker {

    private String id;
    private String name;
    private int hp;
    private boolean isAlive;

    public Character(int hp) {
        setId(UUID.randomUUID().toString());
        setName(RandomGenerator.getInstance().generate());
        setHp(hp);
        setAlive(true);
    }

    public Character(String name, int hp, boolean isAlive) {
        setId(UUID.randomUUID().toString());
        setName(name);
        setHp(hp);
        setAlive(isAlive);
    }

    public Character(String id, String name, int hp, boolean isAlive) {
        setId(id);
        setName(name);
        setHp(hp);
        setAlive(isAlive);
    }

    public Character(String name, int hp) {
        setId(UUID.randomUUID().toString());
        setName(name);
        setHp(hp);
        setAlive(true);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
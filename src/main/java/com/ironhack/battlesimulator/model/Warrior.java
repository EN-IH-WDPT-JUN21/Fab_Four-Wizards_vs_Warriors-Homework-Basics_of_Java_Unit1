package com.ironhack.battlesimulator.model;

import com.ironhack.battlesimulator.common.RandomGenerator;

public class Warrior extends Character {

    private int stamina;
    private int strength;

    private static final int MAX_HP = 200;

    private static final int MIN_STAMINA = 10;
    private static final int MAX_STAMINA = 50;

    private static final int MIN_STRENGTH = 1;
    private static final int MAX_STRENGTH = 10;

    // Constructor for party imported from CSV
    public Warrior(String id, String name, int hp, boolean isAlive, int stamina, int strength) {
        super(id, name, hp, isAlive);
        setStamina(stamina);
        setStrength(strength);
    }

    // Constructor for customized character
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        setStamina(stamina);
        setStrength(strength);
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        if(stamina >MAX_STAMINA) {
            this.stamina = MAX_STAMINA;
            System.out.println("You cannot set strength over " +MAX_STAMINA+"!");
        } else if(stamina <MIN_STAMINA) {
            this.stamina = MIN_STAMINA;
        } else {
            this.stamina = stamina;
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if(strength >MAX_STRENGTH) {
            this.strength = MAX_STRENGTH;
            System.out.println("You cannot set strength over "+MAX_STRENGTH+"!");
        } else if(strength <MIN_STRENGTH) {
            this.strength = MIN_STRENGTH;
        } else {
            this.strength = strength;
        }
    }

    @Override
    public void setHp(int hp) {
        if(hp <=0) {
            super.setHp(0);
            super.setAlive(false);
        } else if(hp>MAX_HP) {
            super.setHp(MAX_HP);
        } else {
            super.setHp(hp);
        }
    }

    @Override
    public void attack(Character character) throws InterruptedException {
        String opponent  = character instanceof Warrior ? "warrior" : "wizard";
        int damage;

        if(this.stamina >= 5) {
            System.out.println(this.getName() +"(warrior) is attacking "+character.getName()+"("+opponent+") with a Heavy Attack!");
            damage = this.strength;
            Thread.sleep(1000);
            this.stamina -=5;
        } else {
            System.out.println(this.getName() +"(warrior) is attacking : "+character.getName()+"("+opponent+") with a Weak Attack!");
            Thread.sleep(1000);
            damage = strength/2;
            this.stamina++;
        }

        character.setHp(character.getHp() - damage);
        System.out.println(character.getName()+" has lost " +damage+ " hp!");
        if(!character.isAlive()) {
            System.out.println(character.getName() + " is dead [*]");
        }
    }
}

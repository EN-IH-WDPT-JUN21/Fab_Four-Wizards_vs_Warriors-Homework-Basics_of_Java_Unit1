package com.ironhack.battlesimulator.model;

import com.ironhack.battlesimulator.common.RandomGenerator;

public class Warrior extends Character {

    private int stamina;
    private int strength;

    private static final int MIN_HP = 100;
    private static final int MAX_HP = 200;

    private static final int MIN_STAMINA = 10;
    private static final int MAX_STAMINA = 50;

    private static final int MIN_STRENGTH = 1;
    private static final int MAX_STRENGTH = 10;

    // Default constructor for party with full randomized stats
    public Warrior() {
        super(RandomGenerator.getInstance().randomInt(MIN_HP,MAX_HP));
        setStamina(RandomGenerator.getInstance().randomInt(MIN_STAMINA,MAX_STAMINA));
        setStrength(RandomGenerator.getInstance().randomInt(MIN_STRENGTH,MAX_STRENGTH));
    }

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
        if(stamina >50) {
            this.stamina = 50;
            System.out.println("You cannot set strength over " +MAX_STAMINA);
        } else if(stamina <10) {
            System.out.println("You cannot set strength less than "+MIN_STAMINA);
            this.stamina = 10;
        } else {
            this.stamina = stamina;
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if(strength >10) {
            this.strength = 10;
            System.out.println("You cannot set strength over "+MAX_STRENGTH);
        } else if(strength <1) {
            System.out.println("You cannot set strength less than "+MIN_STRENGTH);
            this.strength = 1;
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
            System.out.println("You can't set hp over "+MAX_HP+"!");
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

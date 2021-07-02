package com.ironhack.battlesimulator.model;

import com.ironhack.battlesimulator.common.RandomGenerator;

public class Wizard extends Character {

    private static final int MAX_HP = 100;

    private static final int MIN_MANA = 10;
    private static final int MAX_MANA = 50;

    private static final int MIN_INTELLIGENCE = 1;
    private static final int MAX_INTELLIGENCE = 50;

    private int mana;
    private int intelligence;

    // Constructor for party imported from CSV
    public Wizard(String id,String name, int hp, boolean isAlive, int mana, int intelligence) {
        super(id, name, hp, isAlive);
        setMana(mana);
        setIntelligence(intelligence);
    }

    // Constructor for customized character
    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, hp);
        setMana(mana);
        setIntelligence(intelligence);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if(mana >MAX_MANA) {
            this.mana = MAX_MANA;
            System.out.println("You cannot set mana over "+MAX_MANA);
        } else if(mana <MIN_MANA) {
            this.mana = MIN_MANA;
        } else {
            this.mana = mana;
        }
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        if(intelligence >MAX_INTELLIGENCE) {
            this.intelligence = MAX_INTELLIGENCE;
            System.out.println("You cannot set intelligence over " + MAX_INTELLIGENCE);
        } else if(intelligence <MIN_INTELLIGENCE) {
            this.intelligence = MIN_INTELLIGENCE;
        } else {
            this.intelligence = intelligence;
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
        String opponent  = character instanceof Warrior ? "Warrior" : "Wizard";
        int damage;

        if(this.getMana() >=5) {
            System.out.println(this.getName() +"(wizard) is attacking "+character.getName()+"("+opponent+") with a Fireball!");
            Thread.sleep(1000);
            damage = this.intelligence;
            setMana(getMana()-5);
        } else {
            System.out.println(this.getName() +"(wizard) is attacking "+character.getName()+"("+opponent+") with a Staff hit!");
            Thread.sleep(1000);
            setMana(getMana()+1);
            damage=2;
        }

        character.setHp(character.getHp() - damage);
        System.out.println(character.getName() + " lost " +damage+ " hp!");
        if(!character.isAlive()) {
            System.out.println(character.getName() + " is dead [*]");
        }
    }
}

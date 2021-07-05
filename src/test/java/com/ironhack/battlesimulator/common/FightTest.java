package test.java.com.ironhack.battlesimulator.common;

import main.java.com.ironhack.battlesimulator.common.Fight;
import main.java.com.ironhack.battlesimulator.model.Character;
import main.java.com.ironhack.battlesimulator.model.Warrior;
import main.java.com.ironhack.battlesimulator.model.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightTest {
    Fight fight = new Fight();
    Warrior warrior;
    Wizard wizard;
    List<Object> graveyard;
    List<Object> myParty1;
    List<Object> myParty2;

    @BeforeEach
    public void setup() {
        warrior = new Warrior("warrior1", 150, 45, 10);
        wizard = new Wizard("wizard1", 70, 45, 25);
        graveyard = Fight.getGraveyard();
        myParty1 = fight.createRandomParty(5);
        myParty2 = new ArrayList<>();
        myParty2.add(warrior);
        myParty2.add(wizard);
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

    @Test
    public void checkHpTest() {
        assertEquals(70, fight.checkHp(wizard));
    }

    @Test
    public void createRandomPartyTest() {
        myParty1 = fight.createRandomParty(5);
        Object second = myParty1.get(1);
        assertEquals(5, myParty1.size());
        for(Object o: myParty1) {
            assert (o instanceof Wizard || o instanceof Warrior);
        }
        if(second instanceof Warrior) {
            assert(((Warrior) second).getStamina() >= 10 && ((Warrior) second).getStamina() <= 50);
        } else if(second instanceof Wizard) {
            assert(((Wizard) second).getIntelligence() >= 1 && ((Wizard) second).getIntelligence() <= 50);
        }
    }

    @Test
    public void checkFighterHpTest() {
        try {
            fight.fightToDeath(warrior, wizard);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        int tempPartySize = myParty2.size();
        fight.checkFighterHp(warrior, myParty2);
        fight.checkFighterHp(wizard, myParty2);
        assertEquals(1, graveyard.size());
        assertEquals(tempPartySize + 1, myParty2.size());
    }

    @Test
    public void checkRandomFighters() {
        Object randomFighter = fight.randomFighters(myParty1);
        assertEquals(4, myParty1.size());
        assert(randomFighter instanceof Wizard || randomFighter instanceof Warrior);
    }

    @Test
    public void selectFighterTest() {
        String name = warrior.getName();
        Object selectedFighter = null;
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(name.getBytes()));
            selectedFighter = fight.selectFighter(myParty2);
        } finally {
            System.setIn(stdin);
        }
        assertEquals(1, myParty2.size());
        assert(selectedFighter instanceof Wizard || selectedFighter instanceof Warrior);
    }

    @Test
    public void toStringTest() {
        String myPartyNames = fight.toString(myParty2);
        assert(myPartyNames instanceof String);
        assertEquals(((Character) myParty2.get(0)).getName() + ", " + ((Character) myParty2.get(1)).getName(), myPartyNames);
    }

    @Test
    public void fightToDeathTest() {
        try {
            fight.fightToDeath(warrior, wizard);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        assert(warrior.getHp() == 0 || wizard.getHp() == 0);
        assert(warrior.isAlive() == false || wizard.isAlive() == false);
    }


}

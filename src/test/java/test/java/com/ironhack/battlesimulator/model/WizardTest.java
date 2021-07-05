package test.java.com.ironhack.battlesimulator.model;

import com.ironhack.battlesimulator.model.Wizard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WizardTest {

    @Test
    public void addWizard_checkTruncationToMaxValues (){
        Wizard wizard = new Wizard("Test Wizard",101,51,51);

        Assertions.assertEquals(100,wizard.getHp());
        Assertions.assertEquals(50,wizard.getMana());
        Assertions.assertEquals(50,wizard.getIntelligence());
        Assertions.assertEquals("Test Wizard",wizard.getName());
        Assertions.assertTrue(wizard.isAlive());

        wizard.setHp(wizard.getHp()-101);

        Assertions.assertFalse(wizard.isAlive());
        Assertions.assertEquals(0,wizard.getHp());
    }

    @Test
    public void addWizard_checkTruncationToMinValues (){
        Wizard wizard = new Wizard("Test Wizard",30,9,0);

        Assertions.assertEquals(30,wizard.getHp());
        Assertions.assertEquals(10,wizard.getMana());
        Assertions.assertEquals(1,wizard.getIntelligence());
        Assertions.assertEquals("Test Wizard",wizard.getName());
        Assertions.assertTrue(wizard.isAlive());
    }
}
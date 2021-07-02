package com.ironhack.battlesimulator.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomGeneratorTest {

    @Test
    public void randomInt_correctResultOfTheMethod() {
        int min = 1;
        int max = 5;

        for (int i = 0; i < 100; i++) {
            int result = RandomGenerator.getInstance().randomInt(min, max);
            Assertions.assertTrue(result <= max && result >= min);
        }
    }

    @Test
    public void generate_checkSuffix() {
        RandomGenerator randomGenerator = RandomGenerator.getInstance();

        String name = randomGenerator.generate();
        boolean condition = false;
        String name2 = null;

        while(!condition) {
            if((name2 = randomGenerator.generate()).equals(name+" Jr")) {
                condition=true;
            }
        }

        Assertions.assertEquals(name+" Jr", name2);
    }

    @Test
    public void getInstance_checkIfTwoObjectAreTheSameInstance() {
        RandomGenerator randomGenerator1 = RandomGenerator.getInstance();
        RandomGenerator randomGenerator2 = RandomGenerator.getInstance();

        randomGenerator1.generate();

        randomGenerator2.generate();
        randomGenerator2.generate();

        Assertions.assertSame(randomGenerator1,randomGenerator2); // check if objects have the same references
        Assertions.assertEquals(randomGenerator1, randomGenerator2); // check if objects are the same
    }
}

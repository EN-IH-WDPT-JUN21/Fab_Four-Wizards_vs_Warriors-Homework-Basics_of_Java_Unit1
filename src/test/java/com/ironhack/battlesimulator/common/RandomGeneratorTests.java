package com.ironhack.battlesimulator.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomGeneratorTests {

    @Test
    public void randomIntTest() {
        int min = 1;
        int max = 5;

        for (int i = 0; i < 100; i++) {
            int result = RandomGenerator.getInstance().randomInt(min, max);
            Assertions.assertTrue(result <= max && result >= min);
        }
    }
}

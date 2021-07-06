package test.java.com.ironhack.battlesimulator.common;

import main.java.com.ironhack.battlesimulator.common.InputOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputTest {

    private static InputOutput tester;

    @BeforeAll
    public static void setup() {
        tester = new InputOutput();
    }

    @Test
    @DisplayName("Test: readFile(). Asserts that List returned")
    public void InputOutput_readFileTest_ObjectReturned() throws IOException {

        File file = new File("BattleStar_2021-07-03.csv");
        file.createNewFile();
        File file1 = new File("Merry_2021-07-04.csv");
        file1.createNewFile();

        assertNotNull(tester.readFile("BattleStar_2021-07-03.csv"));
        assertNotNull(tester.readFile("Merry_2021-07-04.csv"));

        file.delete();
        file1.delete();
    }
}
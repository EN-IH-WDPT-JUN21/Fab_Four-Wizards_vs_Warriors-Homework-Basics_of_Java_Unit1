package com.ironhack.battlesimulator.common;

import com.ironhack.battlesimulator.model.Character;
import com.ironhack.battlesimulator.model.Warrior;
import com.ironhack.battlesimulator.model.Wizard;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args) {
        InputOutput tester = new InputOutput();
        tester.exportParty(tester.createParty());
        tester.exportParty(tester.createParty());
        tester.exportParty(tester.createParty());
        System.out.println(tester.getPreviousParties());
    }

    private static List<String> previousParties = new ArrayList<>();
    private static char versionCounter = 'a';
    private LocalDate date = LocalDate.now();

    public List<String> getPreviousParties() {
        return previousParties;
    }

    public void addParty(String aString) {
        this.previousParties.add(aString);
    }

    public List<Object> readFile(File aFile) {
        List<Object> tempList = new ArrayList<>();
        // Temporary holders of common constructor values
        String anID; String aName; int hp; boolean isAlive;
        // Temporary holders of Warrior stats
        int stamina; int strength;
        // Temporary holders of Wizard stats
        int mana; int intelligence;
        Object tempObject;
        Scanner aScanner;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(aFile));
            aScanner = new Scanner(reader);
            aScanner.useDelimiter(",");
            while (aScanner.hasNextLine()) {
                if (aScanner.nextInt() == 1) {
                    // Construct a Warrior
                    anID = aScanner.next();
                    aName = aScanner.next();
                    hp = aScanner.nextInt();
                    isAlive = aScanner.nextBoolean();
                    stamina = aScanner.nextInt();
                    strength = aScanner.nextInt();
                    tempObject = new Warrior(anID, aName, hp, isAlive, stamina, strength);
                    tempList.add(tempObject);
                }
                if (aScanner.nextInt() == 2) {
                    // Construct a Wizard
                    anID = aScanner.next();
                    aName = aScanner.next();
                    hp = aScanner.nextInt();
                    isAlive = aScanner.nextBoolean();
                    mana = aScanner.nextInt();
                    intelligence = aScanner.nextInt();
                    tempObject = new Wizard(anID, aName, hp, isAlive, mana, intelligence);
                    tempList.add(tempObject);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        finally {
            try {
                reader.close();
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return tempList;
    }

    public void exportParty(List<Object> aList) {
        BufferedWriter buffWrite = null;
        String fileName = "Party_Created_" + date + ".txt";
        if(this.getPreviousParties().contains(fileName)) {
            fileName = "Party_Created_" + date + "_" + versionCounter + ".txt";
            System.out.println(fileName);
            versionCounter=(char)(versionCounter+1);
        }
        try {
            buffWrite = new BufferedWriter(new FileWriter(fileName)); // name changed
            for (Object battler: aList) {
                if(battler instanceof Warrior) {
                    buffWrite.write(1 + ",");
                }
                if(battler instanceof Wizard) {
                    buffWrite.write(2 + ",");
                }
                //changed: type casting to use Chracter class methods and change method name to isAlive() like in Patryk's class
                buffWrite.write(((Character) battler).getId() + "," + ((Character) battler).getName() + "," + ((Character) battler).getHp() + "," + ((Character) battler).isAlive() + ",");
                if(battler instanceof Warrior) {
                    buffWrite.write(((Warrior) battler).getStamina() + "," + ((Warrior) battler).getStrength() + System.lineSeparator());
                }
                if(battler instanceof Wizard) {
                    buffWrite.write(((Wizard) battler).getMana() + "," + ((Wizard) battler).getIntelligence() + System.lineSeparator());
                }
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
        finally {
            try {
                buffWrite.close();
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        this.addParty(fileName);
    }

    public List<Object> createParty() {
        List<Object> tempList = new ArrayList<>();
        int typeSet = 0; String tempID = ""; //changed: initialized tempID to use it in while loop
        String tempName; int tempHP;
        int tempStamina = 0; int tempStrength = 0; int tempMana = 0; int tempIntelligence = 0;
        boolean done = false; Object tempObject; String choice;
        Scanner aScanner = new Scanner(System.in);
        while(!done) {//changed: the loop wasn't running due to boolean expression evaluating to false
            System.out.println("Will your fighter be a warrior or a wizard? Type 'f' for warrior or 'm' for wizard.");
            choice = aScanner.next(); //changed: stored user input in variable because calling next() two times caused program to hang
            if (choice.equals("f")) { //changed: equals(0 instead of == to compare data
                typeSet = 1;
            } else if (choice.equals("m")){ //changed: equals(0 instead of == to compare data
                typeSet = 2;
            }
            else {
                System.out.println("Hmm, I don't know that type. Please try again.");
                continue;
            }
            while(tempID.length() < 6) { //changed: added a while loop to get the correct ID length
                System.out.println("Enter an ID for your character (6 letters or numbers).");
                tempID = aScanner.next();
            }
            System.out.println("What is their name?");
            tempName = aScanner.next();
            if (typeSet == 1) {
                System.out.println("What is their HP? It should be between 100 and 200.");
                tempHP = aScanner.nextInt();
                System.out.println("What is their Stamina? It should be between 10 and 50.");
                tempStamina = aScanner.nextInt();
                System.out.println("What is their Strength? It should be between 1 and 10");
                tempStrength = aScanner.nextInt();
                //changed: added isAlive = true to satisfy the CSV constructor
                tempObject = new Warrior(tempID,tempName,tempHP, true, tempStamina,tempStrength);
                tempList.add(tempObject);
                tempID = "";
            } else if (typeSet == 2) {
                System.out.println("What is their HP? It should be between 50 and 100.");
                tempHP = aScanner.nextInt();
                System.out.println("What is their Mana? It should be between 10 and 50.");
                tempMana = aScanner.nextInt();
                System.out.println("What is their Intelligence? It should be between 1 and 50");
                tempIntelligence = aScanner.nextInt();
                //changed: added isAlive = true to satisfy the CSV constructor
                tempObject = new Wizard(tempID,tempName,tempHP, true, tempMana,tempIntelligence);
                tempList.add(tempObject);
                tempID = "";
            }
            System.out.println("Done! Would you like to create another character? y/n");
            if (aScanner.next().equals("n")) {
                done = true;
            }
        }
        return tempList;
    }
}

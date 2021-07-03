package main.java.com.ironhack.battlesimulator.common;

import main.java.com.ironhack.battlesimulator.model.Warrior;
import main.java.com.ironhack.battlesimulator.model.Wizard;
import main.java.com.ironhack.battlesimulator.service.Attacker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InputOutput {

    private LocalDate date = LocalDate.now();

    public List<Object> readFile(String aFile) {
        List<Object> tempList = new ArrayList<>();
        String typeSetter; Object tempObject;
        // Temporary holders of common constructor values
        String anID; String aName; int hp; boolean isAlive;
        // Temporary holders of Warrior stats
        int stamina; int strength;
        // Temporary holders of Wizard stats
        int mana; int intelligence;
        BufferedReader csvReader = null;
        String row;
        try {
            csvReader = new BufferedReader(new FileReader(aFile));
            if (aFile == null) {
                throw new NullPointerException("No input provided");
            }
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                typeSetter = data[0];
                if (typeSetter.equals("1")) {
                    anID = data[1];
                    aName = data[2];
                    hp = Integer.parseInt(data[3]);
                    isAlive = Boolean.parseBoolean(data[4]);
                    stamina = Integer.parseInt(data[5]);
                    strength = Integer.parseInt(data[6]);
                    tempObject = new Warrior(anID, aName, hp, isAlive, stamina, strength);
                    tempList.add(tempObject);
                }
                if (typeSetter.equals("2")) {
                    anID = data[1];
                    aName = data[2];
                    hp = Integer.parseInt(data[3]);
                    isAlive = Boolean.parseBoolean(data[4]);
                    mana = Integer.parseInt(data[5]);
                    intelligence = Integer.parseInt(data[6]);
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
                csvReader.close();
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return tempList;
    }

    public void exportParty(List<Object> aList) {
        BufferedWriter csvWriter = null;
        Scanner aScanner = new Scanner(System.in);
        System.out.println("What is your party's name?");
        String partyName = aScanner.next();
        aScanner.close();
        String fileName = partyName + "_" + date + ".csv";
        try {
            csvWriter = new BufferedWriter(new FileWriter(fileName));
            csvWriter.append("TypeSetter,ID,Name,HP,isAlive,Stat1,Stat2\n");

            for (Object attacker : aList) {
                if (attacker instanceof Warrior) {
                    csvWriter.append("1,");
                    csvWriter.append(((Warrior) attacker).getId() + "," + ((Warrior) attacker).getName() + ",");
                    csvWriter.append(((Warrior) attacker).getHp()+ "," + ((Warrior) attacker).isAlive() + ",");
                    csvWriter.append(((Warrior) attacker).getStamina() + "," + ((Warrior) attacker).getStrength() + "\n");
                }
                if (attacker instanceof Wizard) {
                    csvWriter.append("2,");
                    csvWriter.append(((Wizard) attacker).getId() + "," + ((Wizard) attacker).getName() + ",");
                    csvWriter.append(((Wizard) attacker).getHp() + "," + ((Wizard) attacker).isAlive() + ",");
                    csvWriter.append(((Wizard) attacker).getMana() + "," + ((Wizard) attacker).getIntelligence() + "\n");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        finally {
            try {
                csvWriter.flush();
                csvWriter.close();
                System.out.println("Success! Your party has been saved as " + fileName);
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public List<Object> createParty() {
        List<Object> tempList = new ArrayList<>();
        int typeSet = 0; String tempID = ""; //changed: initialized tempID to use it in while loop
        String tempName; int tempHP;
        int tempStamina = 0; int tempStrength = 0; int tempMana = 0; int tempIntelligence = 0;
        boolean done = false; Attacker tempObject; String choice;
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
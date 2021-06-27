import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutput {
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
        try {
            buffWrite = new BufferedWriter(new FileWriter("Party Created: " + java.time.LocalDate.now()));
            for (Object battler: aList) {
                if(battler instanceof Warrior) {
                    buffWrite.write(1 + ",");
                }
                if(battler instanceof Wizard) {
                    buffWrite.write(2 + ",");
                }
                buffWrite.write(battler.getId() + "," + battler.getName() + "," + battler.getHp() + "," + battler.getIsAlive() + ",");
                if(battler instanceof Warrior) {
                    buffWrite.write(battler.getStamina() + "," + battler.getStrength());
                }
                if(battler instanceof Wizard) {
                    buffWrite.write(battler.getMana() + "," + battler.getIntelligence());
                }
                buffWrite.write("/r/n");
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
    }

    public List<Object> createParty() {
        List<Object> tempList = new ArrayList<>();
        int typeSet = 0; String tempID; String tempName; int tempHP;
        int tempStamina = 0; int tempStrength = 0; int tempMana = 0; int tempIntelligence = 0;
        boolean done = false; Object tempObject;
        Scanner aScanner = new Scanner(System.in);
        while(done != false) {
            System.out.println("Will your fighter be a warrior or a wizard? Type 'f' for warrior or 'm' for wizard.");
            if (aScanner.next() == "f") {
                typeSet = 1;
            } else if (aScanner.next() == "m") {
                typeSet = 2;
            }
            System.out.println("Enter an ID for your character (6 letters or numbers).");
            tempID = aScanner.next();
            System.out.println("What is their name?");
            tempName = aScanner.next();
            if (typeSet == 1) {
                System.out.println("What is their HP? It should be between 100 and 200.");
                tempHP = aScanner.nextInt();
                System.out.println("What is their Stamina? It should be between 10 and 50.");
                tempStamina = aScanner.nextInt();
                System.out.println("What is their Strength? It should be between 1 and 10");
                tempStrength = aScanner.nextInt();
                tempObject = new Warrior(tempID,tempName,tempHP,tempStamina,tempStrength);
                tempList.add(tempObject);
            } else if (typeSet == 2) {
                System.out.println("What is their HP? It should be between 50 and 100.");
                tempHP = aScanner.nextInt();
                System.out.println("What is their Mana? It should be between 10 and 50.");
                tempMana = aScanner.nextInt();
                System.out.println("What is their Intelligence? It should be between 1 and 50");
                tempIntelligence = aScanner.nextInt();
                tempObject = new Wizard(tempID,tempName,tempHP,tempMana,tempIntelligence);
                tempList.add(tempObject);
            }
            System.out.println("Done! Would you like to create another character? y/n");
            if (aScanner.next().equals("n")) {
                done = true;
            }
        }
        return tempList;
    }


}

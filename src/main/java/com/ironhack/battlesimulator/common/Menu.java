package main.java.com.ironhack.battlesimulator.common;

import main.java.com.ironhack.battlesimulator.model.GeneralCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu {

    private static List<Object> myTeam = new ArrayList<>();
    private static List<Object> enemyTeam = new ArrayList<>();
    private static List<Object> graveyard = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        playWarriorsVsWizards();
    }

    public static void playWarriorsVsWizards() throws InterruptedException {
        System.out.println(
                "##################################################################\n" +
                        "#                                                                #\n" +
                        "#                      WARRIORS VS WIZARDS                       #\n" +
                        "#                                                                #\n" +
                        "##################################################################");
        //Greet the user
        greetUser();
        //Create the team
        System.out.println("\n" + "It's time to create your team.");
        createTeam();
        //Choose your playing mode and start the game
        chooseGameMode();
    }

    public static void greetUser() {
        System.out.println("\n" + "Greetings and welcome to the greatest of tournaments! \n" +
                "Take a seat in the stands and bear witness to the battle of might vs magic.");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Before we start, what's your name?");
        String name = scanner.nextLine();
        System.out.println("What a lovely name! We are happy to have you here, " + name + ".");
    }

    public static void createTeam() throws InterruptedException {
        System.out.println("\n" + "Please choose an option. \n" +
                "Press 1 to handpick the champions for your party. \n" +
                "Press 2 to let the local champions form their own party. \n" +
                "Press 3 to choose your old party for the fight. \n" +
                "Press q to quit.");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextLine()) {
            case "1":
                createTeamOption1(); // Add and customize each member of your team individually, enemy team randomly created
                break;
            case "2":
                createTeamOption2(); // Both teams are created randomly
                break;
            case "3":
                createTeamOption3(); // Import your own team, enemy team randomly created
                break;
            case "q":
                quitGame(); // Quit and start a new game from the beginning
                break;
            default:
                System.out.println("This is not a valid option. Try again.");
                createTeam();
        }
    }

    public static int getTeamsize(List<Object> myList) {
        return myList.size();
    }

    // Method for option 1: Add and customize each member of your team individually, enemy team randomly created
    public static void createTeamOption1() throws InterruptedException {
        Fight fight = new Fight();
        InputOutput io = new InputOutput();
        myTeam = io.createParty();
        //System.out.println(myTeam); // Remove?

        // Option to export your team
        askExport(myTeam);

        // Create the enemy team randomly
        int teamsize = getTeamsize(myTeam);
        enemyTeam = fight.createRandomParty(teamsize);
        System.out.println("The teams are set and ready to fight! Each team has " + teamsize + " team member(s).");
    }

    // Method for option 2: Both teams are created randomly
    public static void createTeamOption2() {
        Fight fight = new Fight();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's tell the champions to form their own party. How many team members should each team have?");
        while (true) {
            int teamsize = scanner.nextInt();
            if (teamsize > 0) {
                myTeam = fight.createRandomParty(teamsize);
                enemyTeam = fight.createRandomParty(teamsize);
                System.out.println("The teams are set and ready to fight! Each team has " + teamsize + " team member(s).");
                break;
            } else {
                System.out.println("The team size cannot be 0 or less. Please try again.");
            }
        }
    }

//     Method for option 3: Import your own team, enemy team randomly created
    public static void createTeamOption3() {
        Fight tempFight = new Fight(); // added to allow calling of createRandomParty, might be better to have createRandomParty as static?
        InputOutput io = new InputOutput();
        Scanner aScanner = new Scanner(System.in); // Scanner opened to get the user's input
        System.out.println("Good choice! What was the name of your last team's file?");
        String aFile = aScanner.next();
        myTeam = io.readFile(aFile);
        int teamsize = getTeamsize(myTeam);
        enemyTeam = tempFight.createRandomParty(teamsize);
        System.out.println("The teams are set and ready to fight! Each team has " + teamsize + " team member(s).");
        aScanner.close();
    }

    public static void askExport(List<Object> myList) throws InterruptedException {
        InputOutput io = new InputOutput();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nice team! Do you want to export it in order to be able to use it again in the next game? \n" +
                "Press 1 for YES and 2 for NO. If you want to quit the game to start a new one, press q.");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                io.exportParty(myTeam);
                break;
            case "2":
                break;
            case "q":
                quitGame();
                break;
            default:
                System.out.println("This is not a valid option. Try again.");
                askExport(myList);
        }
    }

    public static void quitGame() throws InterruptedException {
        myTeam.clear();
        enemyTeam.clear();
        playWarriorsVsWizards();
    }

    public static void chooseGameMode() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("One last question. How would you like play? \n" +
                "Press 1 to select the champion for each round. \n" +
                "Press 2 to watch the battle unfold. \n" +
                "Press q to quit.");
        switch(scanner.nextLine()) {
            case "1":
                //Method to instantiate outer loop
                System.out.println("Great. Let's start!");
                playNormalBattle();
                break;
            case "2":
                //Randomly choose a player each time
                System.out.println("Great. Let's start!");
                playWithOneClick();
                break;
            case "q":
                quitGame();
                break;
            default:
                System.out.println("This is not a valid option. Try again.");
                chooseGameMode();
        }
    }

    public static void playNormalBattle() throws InterruptedException {
        Fight fight = new Fight(); //changed: to use non-static Fight class methods
        while(hasPartyFighters()) {
            GeneralCharacter champion = (GeneralCharacter) fight.selectFighter(myTeam);
            GeneralCharacter opponent = (GeneralCharacter) fight.randomFighters(enemyTeam);
            fight.fightToDeath(champion, opponent);
            fight.checkFighterHp(champion, myTeam);
            fight.checkFighterHp(opponent, enemyTeam);
        }
        graveyard = Fight.getGraveyard();
        evaluateGame();
    }

    public static void playWithOneClick() throws InterruptedException {
        Fight fight = new Fight();
        while(hasPartyFighters()) {
            GeneralCharacter champion = (GeneralCharacter) fight.randomFighters(myTeam);
            GeneralCharacter opponent = (GeneralCharacter) fight.randomFighters(enemyTeam);
            fight.fightToDeath(champion, opponent);
            fight.checkFighterHp(champion, myTeam);
            fight.checkFighterHp(opponent, enemyTeam);
        }
        graveyard = Fight.getGraveyard();
        evaluateGame();
    }

    public static boolean hasPartyFighters() {
        if (myTeam.size() == 0 || enemyTeam.size() == 0) {
            return false;
        }
        return true;
    }

    public static void evaluateGame() throws InterruptedException {
        Fight fight = new Fight();

        if(myTeam.size() == 0) {
            System.out.println("Your team has lost the tournament. \n" +
                    "These are the fighters that survived until the end: " + fight.toString(enemyTeam));
        } else {
            System.out.println("Congratulations! Your team has won! \n" +
                    "These are the fighters that survived until the end: " + fight.toString(myTeam));
        }

        System.out.println("These are the fighters that succumbed their wounds: " + fight.toString(graveyard));

        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want to start a new game, please press q. Press Enter to end your adventure.");
        String newGame = scanner.nextLine();

        if(newGame.equals("q")) {
            quitGame();
        }
        scanner.close();
    }
}

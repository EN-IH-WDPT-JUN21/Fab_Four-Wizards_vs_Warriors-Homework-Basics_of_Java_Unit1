public class Game {
    /*
    * 1. DATA STRUCTURES
    * 2. GAME SETUP - generate initial state
    * 3. GAME LOGIC - loops, conditionals, functions
    * 4. INPUT/OUTPUT - csv file, logs, interact with user
    *
    *
    * ###################################################################
    *
    * 1. DATA STRUCTURES
    * - abstract class Character
    * - class Warrior extends Character implements Attacker
    * - class Wizard extends Character implements Attacker
    * - Attacker interface
    * - party container - List<Object> partyOne = new ArrayList<Object>()
    *                   - List<Object> partyTwo = new ArrayList<Object>()
    * - graveyard - List<Object> graveyard = new ArrayList<Object>()
    *
    * ###################################################################
    *
    * 2. GAME SETUP
    * - graveyard -> empty ArrayList
    * - choose parties size (should be equal) -> random number >= 1
    * -----------------------------
    * - generate two parties:
    *   - character type -> random number between 1(Warrior) and 2(Wizard)
    *   - character data for the constructors
    *       - String[] names -> random number < names.length
    *       - if party list contains name, name.concat("Jr")
    *       - hp(100-200/50-100), stamina(10-50), strength(1-10), mana(10-50), intelligence(1-50) -> random numbers within range
    *   - store generated objects in two ArrayLists
    * -----------------------------
    *   - save each party in CSV file to be imported when the game begins
    *_________________
    * - two modes
    *    - random
    *    - custom - > user input for object attributes
    * ##################################################################
    *
    * 3. GAME LOGIC
    * - select one member from each party -> random number < party.length
    * - store in a variable
    * - remove selected from their parties
    * - round counter = 0
    * ----------------------------------------------------------------
    * a)  outer loop for the whole tournament -> iterate over a party
    * b) individual skirmish loop
    * - WHILE both fighters' hp > 0
    *      - start a round, one attack from each party fighter
    *           - System.out.println("Round " + counter)
    *           - System.out.println(name1 + " vs " + name2)
    *           - IF a Warrior's stamina >= 5, use Heavy Attack (damage == strength)
    *                - fighter's stamina -= 5
    *                - opponent's hp -= damage
    *                - System.out.println(name + " scored a hit of " + damage + " with Heavy Attack")
    *           - ELSE, use Weak Attack (damage == strength / 2) -> return whole number
    *                - fighter's stamina += 1
    *                - opponent's hp -= damage
    *                - System.out.println(name + " scored a hit of " + damage + " with Weak Attack")
    *           - IF a Wizard's mana >= 5, use Fireball (damage == intelligence)
    *                - fighter's mana -= 5
    *                - opponent's hp -= damage
    *                - System.out.println(name + " scored a hit of " + damage + " with Fireball")
    *           - ELSE, use Staff hit (damage == 2)
    *                - fighter's mana += 1
    *                - opponent's hp -= damage
    *                - print name + " scored a hit of " + damage + " with Stuff hit"
    *      - increment round counter
    * ---------------------------------------------------------------
    * - check fighters' hp
    *      - hp == 0
    *           - mark as loser -> change the boolean flag
    *           - add fighter to the graveyard
    *      - hp > 0
    *           - add fighter back to their party
    *           - mark as winner -> change the boolean flag
    * - if name1 hp == 0 and name2 hp == 0
    *      - print "Both fighters succumbed to their wounds"
    * - else
    *      - print: loser + " has fallen in battle, " + winner + " is the winner"
    * ------------------------------------------------------------------
    * - check both parties length
    *      - if party.length == 0
    *          - mark as losing party, mark the other as winners
    *          - print: losing party + "have lost the tournament. " + winner party + " are the winners."
    * -----------------------------------------------------------------
    * - show graveyard -> just names
    * - can also print the surviving fighters
    *
    * ##################################################################
    * FUNCTIONS
    * - one wrapper function to simulate whole game with one command
    * ######################################################################
    *
    * 4. INPUT/OUTPUT
    * - create importable CSV file
    * - decide on data to print
    * - user input
    * - numbered menu:
    * 1. Greet the user
    * 2. Choose the mode
    *     Creation mode
    *    - custom party -> create individual fighters with custom attributes
    *    - random party -> choose a party size
    *    - previous party (import csv) -> check if file exists and import your fighters (create mobs again)
    *    Fighting mode
    *    - simulate everything -> additional step
    *    - choose your champion (opponent is random)
    *
    * 3. If custom, create fighters one by one and add to the array -> do you want to export the party?
    * 4. Quit
    * 5. Keep a log -> print to the screen
    * 6. Show the graveyard
    *       */
}

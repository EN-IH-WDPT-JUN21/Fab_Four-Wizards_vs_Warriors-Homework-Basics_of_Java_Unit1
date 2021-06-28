import java.util.ArrayList;
import java.util.List;


public class Fight {
    private static List<Object> myTeam = new ArrayList<>();
    private static List<Object> enemyTeam = new ArrayList<>();
    private static List<Object> graveyard = new ArrayList<>();

    //TODO: move methods to correct classes, remove type casting

    public static void main(String[] args) {
        //For testing purposes only
        Warrior w = new Warrior("mivk", "W", 100, true, 25, 10);
        Wizard ww = new Wizard("ccc", "Saruman", 120, true, 122, 23);
        fightToDeath(w, ww);
        myTeam = createRandomParty(4);
        System.out.println(new Fight().toString());
    }

    //TODO: fight logic depends on the implementation of attack function

    public static void causeDamage(Object fighter, int damage) {
        ((Character) fighter).setHp(checkHp(fighter) - damage);
    }

    public static void fullAttack(Object fighter) {
        if(fighter instanceof Warrior) {
            ((Warrior) fighter).attack();
            causeDamage(fighter, ((Warrior) fighter).getStrength());
        } else {
            ((Wizard) fighter).attack();
            causeDamage(fighter, ((Wizard) fighter).getIntelligence());
        }
    }

    public static void exchangeBlows(Object champion, Object opponent) {
        fullAttack(champion);
        fullAttack(opponent);
    }

    public static void fightToDeath(Object champion, Object opponent) {
        while(checkHp(champion) > 0 && checkHp(opponent) > 0) {
            exchangeBlows(champion, opponent);
        }
        System.out.println("End of fight!");
    }

    public static void checkFighterHp(Object fighter, List<Object> party) {
        if(checkHp(fighter) == 0) {
            ((Character) fighter).setAlive(false);
            graveyard.add(fighter);
        } else {
            party.add(fighter);
        }
    }

    public static int checkHp(Object fighter) {
        int hp = ((Character) fighter).getHp();
        return hp;
    }

    public static List<Object> createRandomParty(int teamSize) {
        List<Object> randomParty = new ArrayList<Object>();
        Object randomObject; int typeSet; String randomID; String randomName; int randomHP;
        int randomStamina = 0; int randomStrength = 0; int randomMana = 0; int randomIntelligence = 0;
        //TODO: use name generator for randomName
        String[] names = new String[]{"A", "b", "C", "I"};
        for(int i = 0; i < teamSize; i++) {
            typeSet = getRandomNumber(1, 3);
//            randomName = getRandomName();
            randomName = names[getRandomNumber(0, names.length)];
            randomID = getRandomID();
            if(typeSet == 1) {
                randomHP = getRandomNumber(100, 201);
                randomStamina = getRandomNumber(10, 51);
                randomStrength = getRandomNumber(1, 11);
                randomObject = new Warrior(randomID, randomName, randomHP, true, randomStamina, randomStrength);
                randomParty.add(randomObject);
            }
            if(typeSet == 2) {
                randomHP = getRandomNumber(50, 101);
                randomIntelligence = getRandomNumber(1, 51);
                randomMana = getRandomNumber(10, 51);
                randomObject = new Wizard(randomID, randomName, randomHP, true, randomMana, randomIntelligence);
                randomParty.add(randomObject);
            }
        }
        return randomParty;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getRandomID() {
        String id = "";
        String dictionary = "abcdefghijklmnoprstuwyz1234567890";
        for(int i = 0; i < 6; i++) {
            id += dictionary.charAt(getRandomNumber(0, dictionary.length()));
        }
        return id;
    }

    //TODO: discuss game modes

    public static Object randomFighters(List<Object> party) {
        Object fighter = party.get(getRandomNumber(0, party.size()));
        party.remove(fighter);
        return fighter;
    }

    @Override
    public String toString() {
        List<String> names = new ArrayList<String>();
        String name;
        for (Object fighter : myTeam) {
            name = ((Character) fighter).getName();
            names.add(name);
        }
        return names.toString().replace("[", "").replace("]", "");
    }

}


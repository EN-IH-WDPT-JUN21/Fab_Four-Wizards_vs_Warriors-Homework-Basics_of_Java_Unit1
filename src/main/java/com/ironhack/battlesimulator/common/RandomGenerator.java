package main.java.com.ironhack.battlesimulator.common;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    private static RandomGenerator randomGenerator;
    private ArrayList<String> alreadyUsedNames;
    private final String [] CHARACTERS_NAMES = {
            "Xhaiden","Luther","Inga","Manfrid","Kalva","Ecgtheow","Adelie","Finian","Aryo","Lita",
            "Ingaberg","Kondo","Batal","Siraj","Abner","Halle","Cuchulainn","Bathilde","Aysel","Edrei",
            "Batal","Kango","Aardburzin","Danasur","Isolde","Tehmina","Dhrishit","Rajveer","Harbin","Eleanor",
            "Citlalmina","Kaunteya","Jasbir","Abhiveer","Radames","Sigurd","Barbod","Sheridan","Ebba","Harb",
            "Audra","Siraj","Mandek","Bijann","Laertes","Wyetta","Thibaut","Lesedi","Onella","Hyldeihera"
    };


    private RandomGenerator(ArrayList<String> alreadyUsedNames) {
        this.alreadyUsedNames = alreadyUsedNames;
    }

    public String generate() {
        String name = getCHARACTERS_NAMES()[randomInt(0, CHARACTERS_NAMES.length-1)];

        for (String characterName: getAlreadyUsedNames()) {
            if (characterName.equals(name)) {
                name+=" Jr";
            }
        }

        alreadyUsedNames.add(name);
        return name;
    }

    public static RandomGenerator getInstance() {
        if(randomGenerator == null) {
            randomGenerator = new RandomGenerator(new ArrayList<>());
        }
        return randomGenerator;
    }

    public String[] getCHARACTERS_NAMES() {
        return CHARACTERS_NAMES;
    }

    public int randomInt(int min, int max) {

        if(min >= max) {
            return max;
        }
        else {
            Random random = new Random();
            return random.nextInt(max - min +1) + min;
        }
    }

    public ArrayList<String> getAlreadyUsedNames() {
        return this.alreadyUsedNames;
    }
}

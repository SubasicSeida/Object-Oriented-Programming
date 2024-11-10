package Week6;

import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> words;

    public Dictionary(){
        words = new HashMap<>();
    }

    public String translate(String word){
        if(words.get(word) == null){
            return "Word " + word + " is not in dictionary.";
        } else {
            return words.get(word);
        }
    }

    public void add(String word, String translation){
        words.put(word, translation);
    }

    public int amountOfWords(){
        return words.size();
    }

    public ArrayList<String> translationList(){
        ArrayList<String> translations = new ArrayList<>();
        words.forEach((key, value) -> translations.add(key + " = " + value));
        return translations;
    }

    public static void main(String[] args){
        Dictionary dictionary = new Dictionary();
        dictionary.add("apina", "monkey");
        dictionary.add("banaani", "banana");
        dictionary.add("cembalo", "harpsichord");
        System.out.println(dictionary.translate("apina"));
        System.out.println(dictionary.translate("porkkana"));
        System.out.println(dictionary.amountOfWords());

        ArrayList<String> translations = dictionary.translationList();
        for(String translation: translations) {
            System.out.println(translation);
        }

    }
}

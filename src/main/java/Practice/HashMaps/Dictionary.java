package Practice.HashMaps;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private HashMap<String, HashMap<String, String>> dictionary;
    //The outer HashMap maps the word (in a base language) to an inner HashMap
    // The inner HashMap maps a language code (like EN, FR, ES) to the translation of the word

    public Dictionary(){
        dictionary = new HashMap<>();
    }

    public Dictionary(HashMap<String, HashMap<String, String>> dictionary){
        this.dictionary = dictionary;
    }

    public void addTranslation(String word, String language, String translation){
        word = word.toLowerCase();
        language = language.toUpperCase();
        translation = translation.toLowerCase();

        if(dictionary.containsKey(word)){
            // if it contains the word, update/add translation
            dictionary.get(word).put(language, translation);
        } else {
            // if it doesn't contain the word, make a new translation
            HashMap<String, String> wordTranslation = new HashMap<>();
            wordTranslation.put(language, translation);
            dictionary.put(word, wordTranslation);
        }
    }

    public String getWordTranslations(String word){
        // get all translations for a word
        word = word.toLowerCase();
        if(!dictionary.containsKey(word)) return "No translations for specified word.";
        String output = "Translations for word " + word + " : \n";
        for(HashMap.Entry<String, String> entry : dictionary.get(word).entrySet()){
            output += "\t" + entry.getValue() + " (" + entry.getKey() + ")\n";
        }
        return output;
    }

    public void removeWordTranslations(String word){
        // remove all translations for a word
        dictionary.remove(word);
    }

    public String searchTranslation(String word, String language){
        // search a translation of a word in one language
        if(!dictionary.containsKey(word)) return "No such word in dictionary.";
        return dictionary.get(word).get(language);
    }

    public void listAllTranslations(){
        // lists all translations of all words
        for(String word : dictionary.keySet()){
            System.out.println(getWordTranslations(word));
        }
    }

    public Set<String> getAvailableLanguages(){
        // get all languages currently in dictionary
        Set<String> languages = new HashSet<>();
        for(HashMap<String, String> translation : dictionary.values()){
            languages.addAll(translation.keySet());
        }
        return languages;
    }

    public void saveToFile(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(String word : dictionary.keySet()){
                String line = word + ":\n";
                HashMap<String, String> translations = dictionary.get(word);
                for(String language : translations.keySet()){
                    line += language + " : " + translations.get(language) + "\n";
                }
                writer.write(line);
            }
        } catch(IOException e){
            System.out.println("Exception while saving to file " + e.getMessage());
        }
    }

    public int numOfTranslations(String word){
        if(!dictionary.containsKey(word)) return 0;
        return dictionary.get(word).size();
    }

    public static void main(String[] args){
        Dictionary myDictionary = new Dictionary();
        myDictionary.addTranslation("hello", "en", "hello");
        myDictionary.addTranslation("Hello", "FR", "Bonjour");
        myDictionary.addTranslation("Hello", "es", "Hola");
        myDictionary.addTranslation("world", "en", "world");
        myDictionary.addTranslation("world", "fr", "monde");
        myDictionary.addTranslation("world", "es", "mundo");

        myDictionary.listAllTranslations();
        myDictionary.saveToFile("translations.txt");
        System.out.println("Available languages : " + myDictionary.getAvailableLanguages());
    }
}

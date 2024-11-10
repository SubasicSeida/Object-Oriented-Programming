package Week6;

import java.util.Scanner;

public class TextUserInterface {
    private Scanner reader;
    private Dictionary dictionary;

    public TextUserInterface(Scanner reader,Dictionary dictionary){
        this.reader = reader;
        this.dictionary = dictionary;
    }

    public void start(){
        while(true){
            System.out.println("Statement : ");
            System.out.println("\tquit - quit user interface");
            System.out.println("\tadd - add a word pair to dictionary");
            System.out.println("\ttranslate - ask a word and get its translation");
            String choice = reader.nextLine();
            if(choice.equalsIgnoreCase("quit")){
                System.out.println("Cheers!");
                break;
            }else if(choice.equalsIgnoreCase("add")){
                add();
            }else if(choice.equalsIgnoreCase("translate")){
                translate();
            }else {
                System.out.println("Unknown statement");
            }
        }
    }

    public void add(){
        System.out.println("Enter the word : ");
        String word = reader.nextLine();
        System.out.println("Enter the translation : ");
        String translation = reader.nextLine();
        dictionary.add(word, translation);
    }

    public void translate(){
        System.out.println("Enter the word : ");
        String word = reader.nextLine();
        System.out.println(dictionary.translate(word));
    }

    public static void main(String[] args){
        Dictionary dictionary = new Dictionary();
        Scanner reader = new Scanner(System.in);
        TextUserInterface ui = new TextUserInterface(reader, dictionary);
        ui.start();
    }
}

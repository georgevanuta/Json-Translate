package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* ---Task 01--- */
        Dictionary dictionary = new Dictionary();
        File dicts = new File("dicts"); // directorul cu fisierele .json primite
        for (File file : dicts.listFiles()) {    //trece prin toate fisierele
            dictionary.readFromFile(file);
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti numarul task-ului(2-10):");

        int taskNr = s.nextInt();
        switch (taskNr) {
            case 2:
                Definition d1 = new Definition("Dictionar RO", "synonyms", 1978,
                        new ArrayList<String>());
                ArrayList<Definition> defs = new ArrayList<Definition>();
                defs.add(d1);
                ArrayList<String> sing = new ArrayList<String>();
                sing.add("gaina");
                ArrayList<String> plur = new ArrayList<String>();
                plur.add("gaini");
                Word w1 = new Word("gaina", "chicken", "noun", sing, plur, defs);

                System.out.println("---TASK 02---");
                System.out.println("-inainte de adaugare:");
                System.out.println(dictionary.getWords().get("ro") + "\n");

                System.out.println("-caz normal:");

                if (dictionary.addWord(w1, "ro")) {
                    System.out.println("Cuvant adaugat!");
                }
                else {
                    System.out.println("Cuvant deja existent!");
                }
                System.out.println("Dupa prima adaugare:");
                System.out.println(dictionary.getWords().get("ro") + "\n");

                System.out.println("-caz exceptie:");
                if (dictionary.addWord(w1, "ro")) {
                    System.out.println("Cuvant adaugat!");
                }
                else {
                    System.out.println("Cuvant deja existent!");
                }
                System.out.println("Dupa a doua adaugare:");
                System.out.println(dictionary.getWords().get("ro"));
                break;
            case 3:
                System.out.println("---TASK 03---");
                System.out.println("-inainte de stergere:");
                System.out.println(dictionary.getWords().get("fr")+ "\n");

                if (dictionary.removeWord("chat", "fr")) {
                    System.out.println("Cuvant sters!");
                }
                else {
                    System.out.println("Cuvant deja existent");
                }
                System.out.println("-caz normal:");
                System.out.println(dictionary.getWords().get("fr") + "\n");

                if (dictionary.removeWord("chat", "fr")) {
                    System.out.println("Cuvant sters!");
                }
                else {
                    System.out.println("Cuvant deja existent");
                }
                System.out.println("-caz exceptie:");
                System.out.println(dictionary.getWords().get("fr"));
                break;
            case 4:
                System.out.println("---TASK 04---");
                ArrayList<String> text = new ArrayList<String>();
                text.add("paseste");
                text.add("se deplaseaza");
                text.add("alearga");
                Definition def1 = new Definition("Dictionar Simplu", "synonyms",
                        1898, text);

                System.out.println("-inainte de adaugare:");
                System.out.println(dictionary.stringToWord("merge", "ro").getDefinitions() + "\n");

                System.out.println("-caz normal:");
                if (dictionary.addDefinitionForWord("merge", "ro", def1)) {
                    System.out.println("Definitie adaugata!");
                }
                else {
                    System.out.println("Definitie deja existenta sau cuvant inexistent!");
                }
                System.out.println(dictionary.stringToWord("merge", "ro").getDefinitions() + "\n");

                Definition def2 = new Definition("Micul dicționar academic, ediția a II-a", "synonyms",
                        1782, text);

                System.out.println("-caz exceptie:");
                if (dictionary.addDefinitionForWord("merge", "ro", def2)) {
                    System.out.println("Definitie adaugata!");
                }
                else {
                    System.out.println("Definitie deja existenta sau cuvant inexistent!");
                }
                System.out.println(dictionary.stringToWord("merge", "ro").getDefinitions());
                break;
            case 5:
                System.out.println("---TASK 05---");

                System.out.println("-inainte de stergere:");
                System.out.println(dictionary.stringToWord("poisson", "fr").getDefinitions() + "\n");

                System.out.println("-caz normal:");
                if (dictionary.removeDefinition("poisson", "fr", "Larousse")) {
                    System.out.println("Definitie stearsa!");
                }
                else {
                    System.out.println("Definitie inexistenta sau cuvant inexistent!");
                }
                System.out.println(dictionary.stringToWord("poisson", "fr").getDefinitions() + "\n");

                System.out.println("-caz exceptie:");
                if (dictionary.removeDefinition("poisson", "fr", "Dictionar roman")) {
                    System.out.println("Definitie stearsa!");
                }
                else {
                    System.out.println("Definitie inexistenta sau cuvant inexistent!");
                }
                System.out.println(dictionary.stringToWord("poisson", "fr").getDefinitions());
                break;
            case 6:
                System.out.println("---TASK 06---");

                System.out.println("-caz normal:");
                if (dictionary.translateWord("mananca", "ro", "fr") != null) {
                    System.out.println("Traducere cuvantului *mananca* din -ro- in -fr- este *" +
                            dictionary.translateWord("mananca", "ro", "fr") + "*");
                }
                else {
                    System.out.println("Cuvant inexistent in una din cele doua limbi!");
                }
                System.out.println();

                System.out.println("-caz exceptie:");
                if (dictionary.translateWord("jeu", "fr", "ro") != null) {
                    System.out.println("Traducere cuvantului *jeu* din -fr- in -ro- este *" +
                            dictionary.translateWord("jeu", "fr", "ro") + "*");
                }
                else {
                    System.out.println("Cuvant inexistent in una din cele doua limbi!");
                }
                break;
            case 7:
                System.out.println("---TASK 07---");

                System.out.println("-caz normal:");
                if (dictionary.translateSentence("pisică mananca peste","ro",
                        "fr") != null) {
                    System.out.println(dictionary.translateSentence("pisică mananca peste",
                            "ro", "fr"));
                }
                else {
                    System.out.println("Unul sau mai multe cuvinte inexistente in una din limbi!");
                }

                System.out.println();
                System.out.println("-caz exceptie:");
                if (dictionary.translateSentence("maimuta mananca banane","ro",
                        "fr") != null) {
                    System.out.println(dictionary.translateSentence("maimuta mananca banane",
                            "ro", "fr"));
                }
                else {
                    System.out.println("Unul sau mai multe cuvinte inexistente in una din limbi!");
                }
                break;
            case 8:
                System.out.println("---TASK 08---");

                System.out.println("-caz normal:");
                System.out.println("Traducerile propozitie *poisson manger chat* din -fr- in -ro- sunt:");
                System.out.println(dictionary.translateSentences("poisson manger chat", "fr",
                        "ro"));

                System.out.println();
                System.out.println("caz exceptie:");
                System.out.println("Traducerile propozitie *singe manger chat* din -fr- in -ro- sunt:");
                System.out.println(dictionary.translateSentences("singe manger chat*", "fr",
                        "ro"));
                break;
            case 9:
                System.out.println("---TASK 09---");

                System.out.println("-caz normal:");
                System.out.println(dictionary.getDefinitionsForWord("câine", "ro"));
                System.out.println();

                System.out.println("-caz exceptie:");
                System.out.println(dictionary.getDefinitionsForWord("maimuta", "ro"));
                break;
            case 10:
                System.out.println("---TASK 10---");

                System.out.println("-caz normal:");
                dictionary.exportDictionary("ro");
                System.out.println("Verificati fisierul ro_myDict.json din folderul myDicts!");

                System.out.println("-caz exceptie:");
                dictionary.exportDictionary("hu");
                System.out.println("Verificati fisierul hu_myDict.json din folderul myDicts!");
                break;
        }
    }
}

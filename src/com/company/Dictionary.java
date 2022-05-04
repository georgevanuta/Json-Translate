package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Dictionary {
    /* HashMap in care se stocheaza toate cuvintele din toate limbile */
    static HashMap<String, ArrayList<Word>> words;

    public Dictionary() {
        words = new HashMap<String, ArrayList<Word>>();
    }

    public HashMap<String, ArrayList<Word>> getWords() {
        return words;
    }
    /* ---TASK 01--- */
    /* citeste dintr-un fisier de tip .gson si adauga in Dictionary*/
    public void readFromFile(File file) {
        String language = file.getName().substring(0,2);    // primele doua litere reprezinta limba
        Gson gson = new Gson();
        String filePath = "dicts/" + file.getName();    // folderul cu .json-uri
        try {
            String gsonString = Files.readString(Paths.get(filePath));
            ArrayList<Word> wordsList = gson.fromJson(gsonString, new TypeToken<ArrayList<Word>>(){}.getType());
            words.put(language, wordsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ---TASK 02--- */
    /* verifica daca exista un cuvant in dictionar pentru acea limba */
    public boolean contains(Word word, String language) {
        ArrayList<Word> wordsOfLanguage = words.get(language);
        for (Word wordAux : wordsOfLanguage) {
            if (word.compareTo(wordAux) == 0) {
                return true;
            }
        }
        return false;
    }

    /* adauga un cuvant dintr-o anume limba in Dictionary */
    public boolean addWord(Word word, String language) {
        ArrayList<Word> wordsOfLanguage = words.get(language);

        if (this.contains(word, language)) {    // nu adauga daca deja exista
            return false;
        }

        words.get(language).add(word);
        return true;
    }

    /* ---TASK 03--- */
    /* sterge un cuvant dintr-o anume limba din Dictionary */
    public boolean removeWord(String word, String language) {
        ArrayList<Word> wordsOfLanguage = words.get(language);

        for (int i = 0; i < wordsOfLanguage.size(); i++) {              // itereaza prin cuvinte
            if (wordsOfLanguage.get(i).getWord().equals(word)) {             // daca il gaseste
                words.get(language).remove(wordsOfLanguage.get(i));     // il sterge
                return true;                                            // true->cuvant gasit
            }
        }
        return false;   // false->cuvant inexistent
    }

    /* ---TASK 04--- */
    /* verifica daca exista o definitie in Word */
    public boolean containsDefinition(Word word, String dict) {
        for (int i = 0; i < word.getDefinitions().size(); i++) {
            if (word.getDefinitions().get(i).getDict().equals(dict)) {
                return true;
            }
        }
        return false;
    }

    /* adauga o definitie pentru un cuvant dintr-o limba */
    public boolean addDefinitionForWord(String word, String language, Definition definition) {
        ArrayList<Word> wordsOfLanguage = words.get(language);

        for (int i = 0; i < wordsOfLanguage.size(); i++) {                          // itereaza prin cuvintele limbii
            if (wordsOfLanguage.get(i).getWord().equals(word)) {                         // daca gaseste cuvantul
                if (containsDefinition(wordsOfLanguage.get(i), definition.getDict())) {  // daca are deja acea definitie
                    return false;                                                   // false->are deja definitia
                }
                words.get(language).get(i).getDefinitions().add(definition);
                return true;    // true->nu are definitia, deci este adaugata
            }
        }
        return false;   // false->nu exista cuvantul in acea limba
    }

    /* ---TASK 05--- */
    /* sterge o definitie pentru un cuvant dintr-o limba */
    public boolean removeDefinition(String word, String language, String dictionary) {
        ArrayList<Word> wordsOfLanguage = words.get(language);

        for (int i = 0; i < wordsOfLanguage.size(); i++) {                              // itereaza prin cuvintele limbii
            if (wordsOfLanguage.get(i).getWord().equals(word)) {                             // daca gaseste cuvantul
                ArrayList<Definition> definitions = wordsOfLanguage.get(i).getDefinitions();
                for (int j = 0; j < definitions.size(); j++) {                          // itereaza prin def cuvantului
                    if (definitions.get(j).getDict().equals(dictionary)) {                   // daca o gaseste
                        words.get(language).get(i).getDefinitions().remove(j);               // sterge definitia
                        return true;                                                    // true->definitie gasita, stearsa
                    }
                }
                return false;   // false->nu a gasit definitia
            }
        }
        return false;   // false->nu a gasit cuvantul
    }

    /* ---TASK 06--- */
    /* traduce un word din fromLanguage in toLanguage */
    String translateWord(String word, String fromLanguage, String toLanguage) {
        ArrayList<Word> fromLangList = words.get(fromLanguage);
        ArrayList<Word> toLangList = words.get(toLanguage);

        for (int i = 0; i < fromLangList.size(); i++) {                 // cauta cuvantul in fromLang
            if (fromLangList.get(i).getWord().equals(word)) {
                String word_en = fromLangList.get(i).getWord_en();
                for (int j = 0; j < toLangList.size(); j++) {           // daca il gaseste il cauta si in toLang prin word_en
                    if (toLangList.get(j).getWord_en().equals(word_en)) {
                        return toLangList.get(j).getWord();
                    }
                }
                return null;    // cuvantul nu exista in toLang
            }
        }
        return null;            // cuvantul nu exista in fromLang
    }

    /* ---TASK 07--- */
    /* traduce o propozitie din fromLanguage in toLanguage */
    String translateSentence(String sentence, String fromLanguage, String toLanguage) {
        List<String> sentenceSplit = Arrays.asList(sentence.split(" "));
        String translated = new String();   // to be returned
        for (int i = 0; i < sentenceSplit.size(); i++) {
            if (translateWord(sentenceSplit.get(i), fromLanguage, toLanguage) == null) {
                return null;    // daca nu exista un cuvant in una din limbi, returneaza null deoarece prop nu are fi valida
            }
            translated = translated + translateWord(sentenceSplit.get(i), fromLanguage, toLanguage) + " ";  // construieste propozitia tradusa
        }
        return translated;
    }

    /* ---TASK 08--- */
    /* dandu-se un String, returneaza, daca il gaseste, Word-ul corespunzator limbii; altfel arunca e eroare */
    Word stringToWord(String word, String language) throws Error{
        ArrayList<Word> wordsOfLanguage = words.get(language);

        for (int i = 0; i < wordsOfLanguage.size(); i++) {
            if (wordsOfLanguage.get(i).getWord().equals(word)) {
                return wordsOfLanguage.get(i);
            }
        }
        throw new Error("Cuvant invalid!");
    }

    /* dandu-se un String, returneaza varianta Word tradusa din fromLanguage in toLanguage */
    Word returnTranslatedWord(String word, String fromLanguage, String toLanguage) {
        return stringToWord(translateWord(word, fromLanguage, toLanguage), toLanguage);
    }

    /* cauta prin Definitions unui cuvant si returneaza Definition.text unde Definition.dictype este "synonyms" */
    ArrayList<String> getSynonyms(Word word) {
        for (int i = 0; i < word.getDefinitions().size(); i++) {
            if (word.getDefinitions().get(i).getDictType().equals("synonyms")) {
                return word.getDefinitions().get(i).getText();
            }
        }
        return null;
    }

    /* obtine cate sentences sinonime se pot crea */
    int minSizeOfArrayLists(ArrayList<ArrayList<String>> arrayLists) {
        int min = arrayLists.get(0).size();
        for (int i = 1; i < arrayLists.size(); i++) {
            if (arrayLists.get(i).size() < min) {
                min = arrayLists.get(i).size();
            }
        }
        return min;
    }
    /* traduce o propozitie in maxim 3 variante sinonime */
    ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage) {
        List<String> sentenceSplit = Arrays.asList(sentence.split(" "));
        ArrayList<Word> translatedWords = new ArrayList<Word>();

        /* se construieste o lista cu cuvintele traduse */
        for (int i = 0; i < sentenceSplit.size(); i++) {
            translatedWords.add(returnTranslatedWord(sentenceSplit.get(i), fromLanguage, toLanguage));
        }

        ArrayList<ArrayList<String>> allSynonyms= new ArrayList<ArrayList<String>>();

        /* se construieste o lista de liste cu sinonimele fiecarui cuvant */
        for (int i = 0; i < sentenceSplit.size(); i++) {
            allSynonyms.add(getSynonyms(translatedWords.get(i)));
        }

        ArrayList<String> synonymSentences = new ArrayList<String>();   // to be returned
        String synonymSentenceAux = new String();   // se memoreaza doar o propozitie sinonima aici

        final int maxSynonyms = 3;  // maxim 3 sinonime
        int min = minSizeOfArrayLists(allSynonyms); // cate propozitii se pot crea

        for (int i = 0; i < maxSynonyms && i < min; i++) {
            for (int j = 0; j < sentenceSplit.size(); j++) {
                synonymSentenceAux = synonymSentenceAux + allSynonyms.get(j).get(i) + " ";
            }
            synonymSentences.add(synonymSentenceAux);
            synonymSentenceAux = "";
        }

        return synonymSentences;
    }

    /* ---TASK 09--- */
    /* getter pentru definitiile unui cuvant, sortated dupa data */
    ArrayList<Definition> getDefinitionsForWord(String word, String language) {
        Word wordObj = stringToWord(word, language);    // transformarea din String in Word
        ArrayList<Definition> definitionsSorted = (ArrayList<Definition>) wordObj.getDefinitions().clone();

        Collections.sort(definitionsSorted, new DefinitionDateComparator());    // custom comparator pentru Definition

        return definitionsSorted;
    }

    /* ---TASK 10--- */
    /* exporteaza un dictionar intr-un fisier .json, cuvintele fiind sortate alfabetic, iar definitiile lor dupa data */
    void exportDictionary(String language) {
        ArrayList<Word> wordsOfLanguage = (ArrayList<Word>) words.get(language).clone();
        new File("myDicts").mkdir();    // directory in care se pun dictionarele; se creaza doar daca nu exista
        File myDictFile = new File("myDicts/" + language + "_myDict.json"); // fisierul .json
        try {
            myDictFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(wordsOfLanguage, new WordComparator());    // sorteaza cuvintele
        for (int i = 0; i < wordsOfLanguage.size(); i++) {          // sorteaza definitiile fiecarui cuvant
            Collections.sort(wordsOfLanguage.get(i).getDefinitions(), new DefinitionDateComparator());
        }
        Gson gsonDict = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("myDicts/" + language + "_myDict.json"));
            gsonDict.toJson(wordsOfLanguage, writer);
            writer.close();
        } catch (IOException f) {
            f.printStackTrace();
        }

    }
}

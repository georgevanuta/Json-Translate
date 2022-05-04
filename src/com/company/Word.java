package com.company;

import java.util.ArrayList;

public class Word implements Comparable<Word>{
    private String word;
    private String word_en;
    private String type;
    private ArrayList<String> singular;
    private ArrayList<String> plural;
    private ArrayList<Definition> definitions;

    /* constructor folosit pentru exemple */
    public Word(String word, String word_en, String type, ArrayList<String> singular,
                ArrayList<String> plural, ArrayList<Definition> definitions) {
        this.word = word;
        this.word_en = word_en;
        this.type = type;
        this.singular = singular;
        this.plural = plural;
        this.definitions = definitions;
    }

    public String getWord() {
        return this.word;
    }

    public String getWord_en() {
        return this.word_en;
    }

    public String getType() {
        return this.type;
    }

    public ArrayList<String> getSingular() {
        return this.singular;
    }

    public ArrayList<String> getPlural() {
        return this.plural;
    }

    public ArrayList<Definition> getDefinitions() {
        return this.definitions;
    }

    /* daca sunt egale -> 0 \ daca nu -> -1 */
    public int compareTo(Word word) {
        if (this.word.equals(word.word) && this.word_en.equals(word.word_en) && this.type.equals(word.type) &&
            this.singular.equals(word.singular) && this.plural.equals(word.plural)) {

            if (this.definitions.size() == word.definitions.size()) {
                for (int i = 0; i < this.definitions.size(); i++) {
                    if (this.definitions.get(i).compareTo(word.definitions.get(i)) != 0) {
                        return -1;
                    }
                }
            }
            else {
                return -1;
            }
            return 0;
        }
        return -1;
    }

    /* pentru afisare */
    public String toString() {
        return this.word;
    }
}

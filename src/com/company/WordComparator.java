package com.company;

import java.util.Comparator;

/* Custom Comparator pentru a sorta cuvintele alfabetic */
public class WordComparator implements Comparator<Word> {
    public int compare(Word w1, Word w2) {
        return w1.getWord().compareTo(w2.getWord());
    }
}

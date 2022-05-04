package com.company;

import java.util.Comparator;

/* Custom Comparator pentru a sorta definitiile dupa data */
public class DefinitionDateComparator implements Comparator<Definition> {
    public int compare(Definition d1, Definition d2) {
        return d1.getYear() - d2.getYear();
    }
}

package com.company;

import java.util.ArrayList;

public class Definition implements Comparable<Definition>{
    private String dict;
    private String dictType;
    private Integer year;
    private ArrayList<String> text;

    /* constructor pentru exemple */
    public Definition(String dict, String dictType, Integer year, ArrayList<String> text) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
        this.text = text;
    }

    public String getDict() {
        return this.dict;
    }

    public Integer getYear() {
        return this.year;
    }

    public String getDictType() {
        return this.dictType;
    }

    public ArrayList<String> getText() {
        return this.text;
    }

    /* pentru a compara doua Definitions */
    public int compareTo(Definition def) {
        if (this.dict.equals(def.dict) && this.dictType.equals(def.dictType) &&
            this.year.equals(def.year) && this.text.equals(def.text)) {

            return 0;
        }
        return -1;
    }

    /* afisare pentru exemple */
    public String toString() {
        return this.dict + " din anul " + this.year;
    }
}

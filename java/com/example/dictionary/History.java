package com.example.dictionary;

public class History extends SearchManagement {
    public void printAll()
    {
        for (String word : searchedWord)
        {
            System.out.println(word);
        }
    }
}

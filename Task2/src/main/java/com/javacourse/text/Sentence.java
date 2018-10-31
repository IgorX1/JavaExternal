package com.javacourse.text;

import java.util.List;

public class Sentence {
    List<Word> words;

    public Sentence(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }
}

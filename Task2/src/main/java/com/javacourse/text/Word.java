package com.javacourse.text;

import com.javacourse.text.Symbol;

import java.util.List;

public class Word {
    List<Symbol> symbols;
    int length;

    public Word(List<Symbol> symbols) {
        this.symbols = symbols;
        length = symbols.size();
    }

    public Symbol getLetter(int index){
        return symbols.get(index);
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(symbols.size());
        for(Symbol c: symbols){
            res.append(c.getSymbol());
        }
        return res.toString();
    }
}

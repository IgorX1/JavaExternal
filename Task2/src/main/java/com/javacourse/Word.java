package com.javacourse;

import java.util.List;

public class Word {
    List<Symbol> symbols;

    public Word(List<Symbol> symbols) {
        this.symbols = symbols;
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

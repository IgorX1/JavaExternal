package com.javacourse;

public class PunctuationSymbol extends Symbol {

    public PunctuationSymbol(char symbol) {
        super(symbol);
        acceptOnlyPunctuationSymbols();
    }

    final void acceptOnlyPunctuationSymbols(){
        if(".,;:".indexOf(getSymbol())==-1){
            throw new NotPunctuationSymbolException("Not a punctuation symbol");
        }
    }
}

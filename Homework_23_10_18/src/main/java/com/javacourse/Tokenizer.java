package com.javacourse;

import java.util.ArrayList;

public class Tokenizer {
    static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        final char EMPTY = ' ';
        StringBuilder currentToken = new StringBuilder();
        for(char c : input.toCharArray()){
            if(c==EMPTY)
                continue;
            if(isOperator(c)){
                if(currentToken.length()>0){
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(Character.toString(c));
            }else{
                if(Character.isDigit(c))
                    currentToken.append(c);
                else throw new TokenNotSupportedException(c+" can't be a token");
            }
        }
        return tokens;
    }

   static boolean isOperator(char c){
        return "+-*/()".indexOf(c)!=-1;
    }
}

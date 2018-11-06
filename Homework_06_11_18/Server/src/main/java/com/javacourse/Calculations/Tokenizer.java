package com.javacourse.Calculations;

import java.util.ArrayList;

public class Tokenizer {
    static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        if(input==null)
            return tokens;
        final char EMPTY = ' ';
        StringBuilder currentToken = new StringBuilder();
        for(int i=0;i<input.length(); ++i){
            char c = input.charAt(i);
            if(c==EMPTY)
                continue;
            if(isOperator(c)){
                if (currentToken.length()>0) {
                    if(isAllowedFormatOfToken(currentToken.toString())){
                        tokens.add(currentToken.toString());
                        currentToken.setLength(0);
                    }else throw new TokenNotSupportedException(currentToken+" can't be a token");
                }
                tokens.add(Character.toString(c));
            }else{
                if(Character.isDigit(c) || Character.isLetter(c))
                    currentToken.append(c);
                else throw new TokenNotSupportedException(c+" can't be a token");
            }
        }
        if(currentToken.length()>0)
            tokens.add(currentToken.toString());
        return tokens;
    }

   static boolean isAllowedFormatOfToken(String token){
        return isInteger(token) || isMathFunction(token);
   }

   static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    static boolean isMathFunction(String s){
        return s.equals("sin") || s.equals("log");
    }

   static boolean isOperator(char c){
        return "+-*/()".indexOf(c)!=-1;
    }
}

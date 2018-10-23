package com.javacourse;

import java.util.ArrayList;

public class PostfixTransformator {
    private MyStack stack;
    private ArrayList<String> input;
    private ArrayList<String> output;

    public PostfixTransformator(String input) {
        this.input = Tokenizer.tokenize(input);
        this.output = new ArrayList<>();
        stack = new MyStack(input.length());
    }

    public String transform() {
        for(String s: input){
            switch (s){
                case "+":
                case "-":
                    gotOperator(s, 1);
                    break;
                case "*":
                case "/":
                    gotOperator(s, 2);
                case "(":
                    stack.push(s);
                    break;
                case ")":
                    gotClosingParenthies();
                    break;

            }
        }
        return null;
    }
}

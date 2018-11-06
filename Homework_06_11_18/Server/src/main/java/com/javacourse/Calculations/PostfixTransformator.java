package com.javacourse.Calculations;

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

    public ArrayList<String> transform() {
        analizaBasicParanthesesCorrectness(input);
        //allocate new objects every ime this method is called
        this.output = new ArrayList<>();
        stack = new MyStack();

        for(String s: input){
            switch (s){
                case "+":
                case "-":
                    gotOperator(s, 1);
                    break;
                case "*":
                case "/":
                    gotOperator(s, 2);
                    break;
                case "sin":
                case "log":
                    gotOperator(s, 3);
                    break;
                case "(":
                    stack.push(s);
                    break;
                case ")":
                    gotClosingParentheses(s);
                    break;
                default:
                    output.add(s);
                    break;

            }
        }

        while(!stack.isEmpty()){
            output.add(stack.pop());
        }

        return output;
    }

    void analizaBasicParanthesesCorrectness(ArrayList<String> tokens){
        String prev, next = tokens.get(0);
        for(int i=1; i<tokens.size(); ++i){
            prev = next;
            next = tokens.get(i);
            if(prev.equals("(")&&next.equals(")"))
                throw new TokenNotSupportedException("Empty parentheses are not acceptable");
        }
    }

    void gotOperator(String currentOperator, int priority1) {
        while(!stack.isEmpty()){
            String operatorTop = stack.pop();
            if(operatorTop.equals("(")){
                stack.push(operatorTop);
                break;
            }else {
                int priority2;
                if(operatorTop.equals("+")||operatorTop.equals("-")){
                    priority2 = 1;
                }else if(operatorTop.equals("*")||operatorTop.equals("/"))
                    priority2 = 2;
                else priority2 = 3;
                if(priority2<priority1){
                    stack.push(operatorTop);
                    break;
                }else {
                    output.add(operatorTop);
                }
            }
        }
        stack.push(currentOperator);
    }

    void gotClosingParentheses(String current) {
        while (!stack.isEmpty()){
            String top = stack.pop();
            if(top.equals("("))
                break;
            else output.add(top);
        }
    }

}

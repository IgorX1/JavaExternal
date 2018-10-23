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

    public ArrayList<String> transform() {
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

    void gotOperator(String currentOpeartor, int priority1) {
        while(!stack.isEmpty()){
            String operatorTop = stack.pop();
            if(operatorTop.equals("(")){
                stack.push(operatorTop);
                break;
            }else {
                int priority2;
                if(operatorTop.equals("+")||operatorTop.equals("-")){
                    priority2 = 1;
                }else
                    priority2 = 2;
                if(priority2<priority1){
                    stack.push(operatorTop);
                    break;
                }else {
                    output.add(operatorTop);
                }
            }
        }
        stack.push(currentOpeartor);
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

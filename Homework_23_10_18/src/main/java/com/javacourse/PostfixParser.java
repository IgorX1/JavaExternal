package com.javacourse;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class PostfixParser {
    private MyStack stack;
    private ArrayList<String> reversePolishNotation;

    public PostfixParser(ArrayList<String> reversePolishNotation) {
        this.reversePolishNotation = reversePolishNotation;
    }

    public int parse(){
        stack = new MyStack();
        int leftOperand, rightOperand, answer;

        try {
            for(String current: reversePolishNotation){
                if(isInteger(current))
                    stack.push(current);
                else{
                    rightOperand = Integer.parseInt(stack.pop());
                    leftOperand = Integer.parseInt(stack.pop());
                    switch (current){
                        case "+":
                            answer = leftOperand + rightOperand;
                            break;
                        case "-":
                            answer = leftOperand - rightOperand;
                            break;
                        case "*":
                            answer = leftOperand * rightOperand;
                            break;
                        case "/":
                            answer = leftOperand / rightOperand;
                            break;
                        default:
                            answer = 0;
                    }
                    stack.push(Integer.toString(answer));
                }
            }
        } catch (NumberFormatException | EmptyStackException exc) {
            throw new WrongReversePolishNotationFormat("Unacceptable RPN format");
        }
        answer = Integer.parseInt(stack.pop());
        return answer;
    }

    boolean isInteger(String s){
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

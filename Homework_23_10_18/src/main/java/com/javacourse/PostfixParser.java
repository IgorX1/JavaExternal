package com.javacourse;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class PostfixParser {
    private MyStack stack;
    private ArrayList<String> reversePolishNotation;

    public PostfixParser(ArrayList<String> reversePolishNotation) {
        this.reversePolishNotation = reversePolishNotation;
    }

    public double parse(){
        stack = new MyStack();
        double leftOperand, rightOperand, answer;

        try {
            for(String current: reversePolishNotation){
                if(isNumber(current))
                    stack.push(current);
                else if(Tokenizer.isMathFunction(current)){
                    leftOperand = Double.parseDouble(stack.pop());
                    answer = Math.sin(leftOperand);
                    stack.push(Double.toString(answer));
                }
                else{
                    rightOperand = Double.parseDouble(stack.pop());
                    leftOperand = Double.parseDouble(stack.pop());
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
                    stack.push(Double.toString(answer));
                }
            }
        } catch (NumberFormatException | EmptyStackException exc) {
            throw new WrongReversePolishNotationFormat("Unacceptable RPN format");
        }
        answer = Double.parseDouble(stack.pop());
        return answer;
    }

    boolean isNumber(String s){
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

package com.javacourse.Calculations;

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
                    answer = getMathFunctionValue(stack.pop(), current);
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
                            checkDividor(rightOperand);
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
        } catch (ArithmeticException exc){
            throw new ArithmeticException("Division on zero is unacceptable");
        }
        answer = Double.parseDouble(stack.pop());
        return answer;
    }

    void checkDividor(double d){
        final double EPS = 0.00000001;
        if(Math.abs(d)<EPS) throw new ArithmeticException();
    }

    double getMathFunctionValue(String argument, String operation){
        if(operation.equals("sin"))
            return Math.sin(Double.parseDouble(argument));
        else if(operation.equals("log"))
            return Math.log(Double.parseDouble(argument));
        else throw new OperationNotSupportedException("This operation is not supported. Check allowed operations list for details");
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

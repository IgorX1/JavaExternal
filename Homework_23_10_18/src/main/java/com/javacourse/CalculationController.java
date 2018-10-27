package com.javacourse;

import java.util.Scanner;

class CalculationController {

    CalculationView view;
    Scanner scanner;

    public CalculationController(CalculationView view) {
        this.view = view;
        scanner = new Scanner(System.in);
    }

    void processUser(){
        view.printMessage("Welcome to RPN calculator!");
        PostfixTransformator postfixTransformator;
        PostfixParser postfixParser;
        String expression;
        do{
            expression = getExpression();
            postfixTransformator = new PostfixTransformator(expression);
            postfixParser = new PostfixParser(postfixTransformator.transform());
            view.printMessage(Double.toString(postfixParser.parse()));
        }while (true);
    }

    String getExpression(){
        view.printMessage("Enter the expression to calculate");
        view.printMessage("Allowed operations:+ - / * sin lg ()");
        String expr = scanner.nextLine();
        return expr;
    }
}

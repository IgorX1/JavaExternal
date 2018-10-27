package com.javacourse;

import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

class CalculationController {

    CalculationView view;
    Scanner scanner;

    //Log4j logger
    private static Logger logger;

    static {
        logger = Logger.getLogger(CalculationController.class);
    }

    public CalculationController(CalculationView view) {
        this.view = view;
        scanner = new Scanner(System.in);

        //Set configuration file for the log4j logger
        DOMConfigurator.configure("log/log4j.xml");
    }

    void processUser(){
        view.printMessage("Welcome to RPN calculator!");
        PostfixTransformator postfixTransformator;
        PostfixParser postfixParser;
        String expression;
        do{
            try{
                expression = getExpression();
                postfixTransformator = new PostfixTransformator(expression);
                postfixParser = new PostfixParser(postfixTransformator.transform());
                view.printMessage(Double.toString(postfixParser.parse()));
            }catch (NumberFormatException
                    | ArithmeticException
                    | WrongReversePolishNotationFormat
                    | TokenNotSupportedException exc){
                view.printMessage(exc.getMessage());
                logger.debug(exc.getMessage());
            }

        }while (true);
    }

    String getExpression(){
        view.printMessage("Enter the expression to calculate");
        view.printMessage("Allowed operations:+ - / * sin lg ()");
        String expr = scanner.nextLine();
        return expr;
    }
}

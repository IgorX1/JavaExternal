package com.javacourse.Calculations;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.EmptyStackException;

public class CalculationController {
    private static final Logger logger;
    private Socket socket;

    static {
        logger = Logger.getLogger(CalculationController.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public void processClient(BufferedReader in, PrintWriter out){
        String expression;
        try {
            expression = readAllTextFromInputStream(in);
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println("Can't get input from client");
            return;
        }
        //get expression from stream


    }

    String readAllTextFromInputStream(BufferedReader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = in.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();
    }

    String calculateExpression(String expression){
        String result = null;
        try {
            PostfixTransformator postfixTransformator = new PostfixTransformator(expression);
            PostfixParser postfixParser = new PostfixParser(postfixTransformator.transform());
            result = Double.toString(postfixParser.parse());
        } catch (NumberFormatException
                | ArithmeticException
                | WrongReversePolishNotationFormat
                | TokenNotSupportedException
                | EmptyStackException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}

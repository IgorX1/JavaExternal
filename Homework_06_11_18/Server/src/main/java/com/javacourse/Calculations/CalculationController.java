package com.javacourse.Calculations;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.EmptyStackException;

public class CalculationController {
    private static final Logger logger;
    private static final int  LOWER_BOUND_FOR_PLOT = 0;
    private static final int HIGHER_BOUND_FOR_PLOT = 10;

    static {
        logger = Logger.getLogger(CalculationController.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public String processClient(String expression){
        String  result;

        //TODO: change signature to return Documnet

        result = calculateExpression(expression);// TODO: getXmlWithResult replace
        return result;
    }

    Document getXmlWithResult(String expression){
        String  result = calculateExpression(expression);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
        Document doc = db.newDocument();

        Element root = doc.createElement("result");
        doc.appendChild(root);

        Element points = doc.createElement("points");
        root.appendChild(points);

        Element boundaries = doc.createElement("boundaries");
        boundaries.setAttribute("low",  Integer.toString(LOWER_BOUND_FOR_PLOT));
        boundaries.setAttribute("high", Integer.toString(HIGHER_BOUND_FOR_PLOT));
        root.appendChild(boundaries);


        return doc;
    }

    String calculateExpression(String expression){
        String result = "";
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

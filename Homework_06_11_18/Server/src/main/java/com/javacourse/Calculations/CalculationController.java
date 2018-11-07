package com.javacourse.Calculations;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.EmptyStackException;

public class CalculationController {
    private String expr;
    private boolean isExprParametrized = false;
    private static final Logger logger;
    private static final int  LOWER_BOUND_FOR_PLOT = 0;
    private static final int HIGHER_BOUND_FOR_PLOT = 10;
    private static final String expressionVariableName = "x";

    static {
        logger = Logger.getLogger(CalculationController.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public Document processClient(String expression){
        expr = expression;
        isExprParametrized = isExprParametrized(expr);
        Document result = getXmlWithResult(expression);
        return result;
    }

    boolean isExprParametrized(String expression){
        return expression.contains(expressionVariableName);
    }

    Document getXmlWithResult(String expression){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
        Document doc = db.newDocument();

        Element errors = doc.createElement("errors");
        String  result = null;
        try {
            result = calculateExpression(expression);
        } catch (UnsuccessfulExpressionProcessingException e) {
            Element error = doc.createElement("error");
            errors.appendChild(error);
        }
        Element root = doc.createElement("result");
        root.setAttribute("value", result);
        doc.appendChild(root);
        root.appendChild(errors);

        Element points = doc.createElement("points");
        root.appendChild(points);

        for(int i=LOWER_BOUND_FOR_PLOT; i<=HIGHER_BOUND_FOR_PLOT; ++i){
            Element point = doc.createElement("point");
            point.setAttribute("x", Integer.toString(i));
            try {
                point.setAttribute("y",
                        calculateExpression(substituteNumberInsteadOfVaraible(expr, i)));
            } catch (UnsuccessfulExpressionProcessingException e) {
                logger.error("Unable to process parametrized expression");
            }
            points.appendChild(point);
        }

        Element boundaries = doc.createElement("boundaries");
        boundaries.setAttribute("low",  Integer.toString(LOWER_BOUND_FOR_PLOT));
        boundaries.setAttribute("high", Integer.toString(HIGHER_BOUND_FOR_PLOT));
        root.appendChild(boundaries);


        return doc;
    }

    String calculateExpression(String expression) throws UnsuccessfulExpressionProcessingException{
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
            throw new UnsuccessfulExpressionProcessingException("Could not process your expression");
        }
        return result;
    }

    String substituteNumberInsteadOfVaraible(String expression, int number){
        return isExprParametrized?
                expression.replaceAll(expressionVariableName, Integer.toString(number)):
                expr;
    }

}

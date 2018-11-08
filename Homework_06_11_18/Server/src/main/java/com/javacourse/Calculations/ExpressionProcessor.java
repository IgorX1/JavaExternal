package com.javacourse.Calculations;

import java.util.EmptyStackException;
import static com.javacourse.App.logger;

public class ExpressionProcessor {

    private String expression;
    private boolean isExpressionParametrized;
    private static final String expressionVariableName = "x";

    public ExpressionProcessor(String expression) {
        this.expression = expression;
        this.isExpressionParametrized = isExpressionParametrized();
    }

    boolean isExpressionParametrized(){
        return expression.contains(expressionVariableName);
    }

    public String calculateExpression(String expression) throws UnsuccessfulExpressionProcessingException{
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

    public String calculateExpressionReplacingVariableWithNumber(String expression, int number)
            throws UnsuccessfulExpressionProcessingException{
        return calculateExpression(substituteNumberInsteadOfVaraible(expression, number));
    }

    String substituteNumberInsteadOfVaraible(String expression, int number){
        return isExpressionParametrized ?
                expression.replaceAll(expressionVariableName, Integer.toString(number)):
                this.expression;
    }
}

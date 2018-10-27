package com.javacourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PostfixParserTest {
    PostfixParser parser;
    public static final double DELTA = 0.1;

    @Before
    public void setUp() {
        parser = new PostfixParser(new ArrayList<String>());
    }

    @Test
    public void isNumber_correctDigit_returnsTrue(){
        assertTrue(parser.isNumber("-112"));
        assertTrue(parser.isNumber("0"));
        assertTrue(parser.isNumber("115"));
        assertTrue(parser.isNumber("112.5"));
    }

    @Test
    public void isNumber_incorrectDigit_returnsFalse(){
        assertFalse(parser.isNumber("15z"));
        assertFalse(parser.isNumber(""));
        //assertFalse(parser.isNumber(null));
    }

    @Test
    public void parse_correctInput_correctResult() {
        ArrayList expected = new ArrayList();
        fillTokenList(expected);
        parser = new PostfixParser(expected);

        assertEquals(10, parser.parse(), DELTA);
    }

    private void fillTokenList(ArrayList<String> tokens) {
        tokens.add("12");
        tokens.add("2");
        tokens.add("3");
        tokens.add("4");
        tokens.add("-");
        tokens.add("*");
        tokens.add("+");
    }

    @Test
    public void parse_correctInputWithMathFunction_correctResult() {
        ArrayList expected = new ArrayList();
        fillTokenListWithMathFunction(expected);
        parser = new PostfixParser(expected);
        double actual = parser.parse();
        assertEquals(14.07, actual, DELTA);
    }

    private void fillTokenListWithMathFunction(ArrayList<String> tokens) {
        tokens.add("5");
        tokens.add("3");
        tokens.add("*");
        tokens.add("2");
        tokens.add("7");
        tokens.add("3");
        tokens.add("/");
        tokens.add("+");
        tokens.add("sin");
        tokens.add("+");
    }

}
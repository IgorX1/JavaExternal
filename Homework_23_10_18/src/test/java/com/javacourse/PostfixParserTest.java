package com.javacourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PostfixParserTest {
    PostfixParser parser;
    public static final double DELTA = 0.001;

    @Before
    public void setUp() throws Exception {
        parser = new PostfixParser(new ArrayList<String>());
    }

    @Test
    public void isInteger_correctDigit_returnsTrue(){
        assertTrue(parser.isInteger("-112"));
        assertTrue(parser.isInteger("0"));
        assertTrue(parser.isInteger("115"));
    }

    @Test
    public void isInteger_incorrectDigit_returnsFalse(){
        assertFalse(parser.isInteger("-112.5"));
        assertFalse(parser.isInteger("15z"));
        assertFalse(parser.isInteger(""));
        assertFalse(parser.isInteger(null));
    }

    @Test
    public void parse_correctInput_correctResult() {
        ArrayList expected = new ArrayList();
        fillTokenList(expected);
        parser = new PostfixParser(expected);

        assertEquals(10, parser.parse(), DELTA);
    }

    private void fillTokenList(ArrayList tokens) {
        tokens.add("12");
        tokens.add("2");
        tokens.add("3");
        tokens.add("4");
        tokens.add("-");
        tokens.add("*");
        tokens.add("+");
    }
}
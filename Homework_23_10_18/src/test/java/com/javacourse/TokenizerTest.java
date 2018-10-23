package com.javacourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TokenizerTest {
    Tokenizer tokenizer;

    @Before
    public void setUp() throws Exception {
        tokenizer = new Tokenizer();
    }

    @Test
    public void tokenizeReturnsCorrectTokenList(){
        tokenizer = new Tokenizer();
        ArrayList<String> actual = tokenizer.tokenize("12+2*(3-7)");
        String[] expected = new String[]{"12", "+", "2", "*", "(", "3", "-", "7", ")"};
        assertArrayEquals(actual.toArray(), expected);
    }

    @Test(expected = TokenNotSupportedException.class)
    public void tokenize_wrongToken_excetyion(){
        tokenizer = new Tokenizer();
        tokenizer.tokenize("122$12");
    }

    @Test
    public void isOperatorDistinguishesOperatorsFromOtherTokens(){
        assertTrue(tokenizer.isOperator('+'));
        assertFalse(tokenizer.isOperator('a'));
    }
}
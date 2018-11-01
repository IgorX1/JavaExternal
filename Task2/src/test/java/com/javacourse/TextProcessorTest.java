package com.javacourse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import com.javacourse.text.NotPunctuationSymbolException;
import com.javacourse.text.PunctuationSymbol;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TextProcessorTest {

    TextProcessor textProcessor;

    @Before
    public void setUp() throws Exception {
        textProcessor = new TextProcessor();
    }

    private static final Object[] getWrongLetter(){
        return $(
                "1", Character.MIN_VALUE, "-", "{", "+", "?"
        );
    }

    @Test
    @Parameters(method = "getWrongLetter")
    public void isLetter_notLetter_false(char c){
        assertFalse(textProcessor.isLetter(c));
    }

    private static final Object[] getCorrectLetter(){
        return $(
                'a', 'b', 'c', 'd', 'e', 'f', 'z', 'A', 'B', 'C', 'U'
        );

    }

    @Test
    @Parameters(method = "getCorrectLetter")
    public void isLetter_realLetter_true(char c){
        assertTrue(textProcessor.isLetter(c));
    }


    @Parameters
    public static Collection<Object[]> dataVovel() {
        return Arrays.asList(new Object[][]{
                {'a'},
                {'Я'},
                {'я'},
                {'i'}
        });
    }

    @Test
    @Parameters(method = "dataVovel")
    public void isVovel(char c) {
        assertTrue(textProcessor.isVovel(c));
    }

    @Parameters
    public static Collection<Object[]> dataConsonant() {
        return Arrays.asList(new Object[][]{
                {'w'},
                {'Z'},
                {'В'},
                {'г'}
        });
    }

    @Test
    @Parameters(method = "dataConsonant")
    public void isConsonant(char c) {
        assertTrue(textProcessor.isConsonant(c));
    }
}
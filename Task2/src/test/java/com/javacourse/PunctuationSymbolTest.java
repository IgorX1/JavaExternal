package com.javacourse;

import com.javacourse.text.NotPunctuationSymbolException;
import com.javacourse.text.PunctuationSymbol;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class PunctuationSymbolTest {

    @Parameters
    public static Collection<Object[]> dataWrongPunctuationSymbol() {
        return Arrays.asList(new Object[][]{
                {'a'},
                {'+'},
                {'0'},
                {' '}
        });
    }

    @Test(expected = NotPunctuationSymbolException.class)
    @Parameters(method = "dataWrongPunctuationSymbol")
    public void acceptOnlyPunctuationSymbols(char symbol) {
        PunctuationSymbol punctuationSymbol = new PunctuationSymbol(symbol);
    }
}
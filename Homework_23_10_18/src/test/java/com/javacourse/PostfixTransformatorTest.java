package com.javacourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PostfixTransformatorTest {
    private PostfixTransformator transformator;

    @Before
    public void setUp() throws Exception {
        transformator = new PostfixTransformator("12+5*7");
    }

    @Test
    public void transformCorrectInput(){
        Object[] actual = transformator.transform().toArray();
        Object[] expected = new String[]{
                "12", "5", "7", "*", "+"
        };
        assertArrayEquals(expected, actual);
    }

}
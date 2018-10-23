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
    public void transform(){

    }

}
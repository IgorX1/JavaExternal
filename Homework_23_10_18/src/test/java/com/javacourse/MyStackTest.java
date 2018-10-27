package com.javacourse;

import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class MyStackTest {
    MyStack stack;

    @Before
    public void setUp() throws Exception {
        stack = new MyStack();
    }

    @Test
    public void isEmpty_empty_true() {
        assertTrue(stack.isEmpty());

        stack.add("1");
        assertFalse(stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void peek_empty_throwsException() {
        stack.peek();
    }

    @Test(expected = EmptyStackException.class)
    public void pop_empty_throwsException() {
        stack.pop();
    }
}
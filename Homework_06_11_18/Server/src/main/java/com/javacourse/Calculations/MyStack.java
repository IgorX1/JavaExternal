package com.javacourse.Calculations;

import java.util.*;

public class MyStack extends AbstractCollection implements Collection {
    private int maxSize;
    private String[] stackArray;
    private int top;
    private static final int DEFAULT_SIZE;

    static {
        DEFAULT_SIZE = 20;
    }

    public MyStack() {
        this.maxSize = DEFAULT_SIZE;
        this.stackArray = new String[maxSize];
        this.top = -1;
    }

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new String[maxSize];
        this.top = -1;
    }

    public void push(String token){
        ensureCapacity();
        stackArray[++top] = token;
    }

    private void ensureCapacity(){
        if(stackArray.length==top+1)
            stackArray = Arrays.copyOf(stackArray, top*2+1);
    }

    public String pop(){
        if(isEmpty())
            throw new EmptyStackException();
        String elem = stackArray[top--];
        stackArray[top+1] = null;
        return elem;
    }

    public boolean isEmpty(){
        return (top==-1);
    }

    public String peek(){
        if(isEmpty())
            throw new EmptyStackException();
        return stackArray[top];
    }

    @Override
    public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<String>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return !isEmpty() && currentIndex<=top;
            }

            @Override
            public String next() {
                return stackArray[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public int size() {
        return top+1;
    }

    @Override
    public boolean add(Object token) {
        push((String)token);
        return true;
    }


}

package com.javacourse;

public class Number {
    private int value;
    private boolean isEven;

    public Number(int v){
        value = v;
        isEven = this.isEven(v);
    }

    public static boolean isEven(int n){
        return n%2==0 ? true : false;
    }

    public boolean isEven(){
        return isEven;
    }

    public int getValue(){
        return value;
    }
}

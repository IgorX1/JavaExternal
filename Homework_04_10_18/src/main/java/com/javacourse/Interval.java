package com.javacourse;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interval {
    private int start;
    private int end;

    public Interval(int s, int e){
        if(isBGreaterOrEqualToA(s,e))
        {
            start = e;
            end = s;
        }
        else{
            start = s;
            end = e;
        }
    }

    public static Interval getInstanceFromConsole(){
        final int DEFAULT = 0;
        Scanner scanner = new Scanner(System.in);
        int a, b;//boundaries of the interval

        String errorMsg = "Can't initialize this variable with a non-integer value. Used zero as default value";
        System.out.print("Enter start of the interval:");
        try {
            a = scanner.nextInt();
        }catch (InputMismatchException exc){
            System.out.println(errorMsg);
            a=DEFAULT;
        }
        System.out.print("Enter end of the interval:");
        try
        {
            b = scanner.nextInt();
        }catch (InputMismatchException exc){
            System.out.println(errorMsg);
            b=DEFAULT;
        }

        return new Interval(a,b);
    }

    private boolean isBGreaterOrEqualToA(int a, int b){
        if(a>b)
            return false;
        else return true;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public int getLength(){
        if(start>=0 && end>=0)
            return end-start+1;
        else return Math.abs(start)+Math.abs(end)+1;
    }
}

package com.javacourse;

import java.util.ArrayList;
import java.util.Scanner;

public class OddAndEvenNumbers {
    private static Number[] numbers;

    public static void showOddAndEvenNumbersInConsole(){
        Interval interval = Interval.getInstanceFromConsole();
        fillNumberArrayAndCheckIfEven(interval);
        showEvenFromStart();
        showOddFromEnd();
        showSumOfEvenNumbers();
        showSumOfOddNumbers();
    }

    private static Number[] fillNumberArrayAndCheckIfEven(Interval interval){
        numbers = new Number[interval.getLength()];
        for(int i=interval.getStart(), j=0; j<numbers.length; ++i, ++j){
            numbers[j] = new Number(i);
        }
        return numbers;
    }

    private static void showEvenFromStart(){
        System.out.println("Even numbers:");
        for(Number n: numbers){
            if(n.isEven())
                System.out.print(n.getValue()+" ");
        }
        if(numbers.length>0) System.out.println();
    }

    private static void showOddFromEnd(){
        System.out.println("Odd numbers:");
        for(Number n: numbers){
            if(!n.isEven())
                System.out.print(n.getValue()+" ");
        }
        if(numbers.length>0) System.out.println();
    }

    private static int showSumOfEvenNumbers(){
        int evenSum = 0;
        for(Number n: numbers){
            if(n.isEven())
                evenSum += n.getValue();
        }
        if(numbers.length>0){
            System.out.println("Even sum:"+evenSum);
        }
        return evenSum;
    }

    private static int showSumOfOddNumbers() {
        int oddSum = 0;
        for(Number n: numbers){
            if(!n.isEven())
                oddSum += n.getValue();
        }
        if(numbers.length>0){
            System.out.println("Odd sum:"+oddSum);
        }
        return oddSum;
    }
}

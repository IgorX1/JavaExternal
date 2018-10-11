package com.javacourse;

import java.util.ArrayList;
import java.util.Scanner;

public class Fibonacci {
    private ArrayList<Integer> fib = new ArrayList<Integer>();

    public void showResultsOfCalculations(){
        fillFibArray();
        showMaxOddAndEvenFib();
        showPercentageOfOddAndEven();
    }

    private void fillFibArray(){
        int f0 = 0, f1 = 1, next = 0;
        int n = getNumOfFibonacciNumbers();
        for(int i=0; i<n; ++i){
            if(i==0){
                fib.add(f0);
                continue;
            }

            if(i==1){
                fib.add(f1);
                continue;
            }

            next = f0 + f1;
            f0 = f1;
            f1 = next;

            fib.add(next);
        }
    }

    private int getNumOfFibonacciNumbers(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of fibonacci numbers to generate:");
        return scanner.nextInt();
    }

    private void showMaxOddAndEvenFib(){
        for(int i=fib.size()-1; i>=0; --i){
            if(Number.isEven(fib.get(i))){
                System.out.println("Maximal even fibonacci number:"+ fib.get(i));
                break;
            }
        }
        for(int i=fib.size()-1; i>=0; --i){
            if(!Number.isEven(fib.get(i))){
                System.out.println("Maximal odd fibonacci number:"+ fib.get(i));
                break;
            }
        }
    }

    private void showPercentageOfOddAndEven(){
        int numOfOdd = 0, numOfEven = 0;
        for(int i=0; i<fib.size(); ++i){
            if(Number.isEven(fib.get(i))){
                numOfEven++;
            }else numOfOdd++;
        }

        double oddPercentage = (double)numOfOdd/fib.size()*100;
        double evenPercentage = (double)numOfEven/fib.size()*100;
        System.out.println("Percentage of odd:"+oddPercentage);
        System.out.println("Percentage of even:"+evenPercentage);
    }

}

package com.javacourse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OddAndEvenNumbers oddAndEvenNumbers = new OddAndEvenNumbers();
        oddAndEvenNumbers.showOddAndEvenNumbersInConsole();

        Fibonacci fibonacci = new Fibonacci();
        fibonacci.showResultsOfCalculations();
    }
}

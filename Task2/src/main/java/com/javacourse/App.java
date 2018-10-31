package com.javacourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("This program allows you to remove all words of given length, which start on consonant letter");
        int lengthParam = getLengthFromConsole();
        TextProcessor textProcessor = new TextProcessor();
        textProcessor.processRequest();
    }

    public static int getLengthFromConsole(){

        do {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Enter length:");
                //reader.
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Not an integer.Repeat, please!");
            }
        } while (true);
    }
}

package com.javacourse;

import com.javacourse.text.Word;

import java.io.*;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("This program allows you to remove all words of given length, which start on consonant letter");
        int lengthParam = getLengthFromConsole();
        TextProcessor textProcessor = new TextProcessor();
        List<Word> res = textProcessor.processRequest(lengthParam);
        try {
            saveFilteredResult(res);
        } catch (IOException e) {
            System.out.println("Could not write to the resulting file");
        }
        System.out.println("Result was printed to data/result.txt");
    }

    public static int getLengthFromConsole(){

        do {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Enter length:");
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Not an integer.Repeat, please!");
            }
        } while (true);
    }

    public static void saveFilteredResult(List<Word> words) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data/result.txt"))){
            for(Word w : words){
                writer.write(w.toString() + " ");
            }
        }
    }

}

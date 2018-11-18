package com.javacourse.queryController;

import java.util.Scanner;

public class ConsoleInputManager {
    public static int getKeyFromUser(){
        final int DEFAULT_INPUT = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID value:");
        String input = scanner.nextLine();
        System.out.println();
        int result;
        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            result = DEFAULT_INPUT;
        }
        return result;
    }

    public static String getStringKeyFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID value:");
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }
}

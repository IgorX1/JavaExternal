package com.javacourse;

import com.javacourse.queryController.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Scanner;

public class App
{
    private static Scanner scanner = new Scanner(System.in);
    public static final Logger logger;

    static {
        logger = Logger.getLogger(App.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main(String[] args )
    {
        MenuController menuController = ConsoleMenuFactory.getMenuController();
        processUser(menuController);
    }

    public static void processUser(MenuController menuController){
        while (true) {
            System.out.println(menuController.toString());
            menuController.menuItemPickedUp(getMenuItemChoice());
        }
    }

    private static int getMenuItemChoice(){
        System.out.print("Choose menu item:");
        return scanner.nextInt();
    }
}

package com.javacourse;

public class App 
{
    public static void main( String[] args )
    {
        ClientController clientController = new ClientController(new ClientConsoleView());
        clientController.processUser();
    }
}

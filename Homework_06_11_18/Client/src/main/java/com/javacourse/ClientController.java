package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientController {

    private ClientView view;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);
    private static final String IP_ADRESS = "127.0.0.1";
    private static final int PORT_NUMBER = 8080;
    private static final Logger logger;

    static {
        logger = Logger.getLogger(ClientController.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public ClientController(ClientView view) {
        this.view = view;
    }

    public void processUser(){
        view.showMessage("Welcome to RPN calculator!");

        try {
            initSocket();
        } catch (IOException e) {
            view.showMessage("Unable to establish the connection.");
            return;
        }

        String expression;
        do{
            expression = getExpression();
            processExpressionOnServer(expression);

        }while (true);
    }

    String getExpression(){
        view.showMessage("Enter the expression to calculate");
        view.showMessage("Allowed operations:+ - / * sin lg ()");
        String expr = scanner.nextLine();
        return expr;
    }

    void initSocket() throws IOException {
        try {
            InetAddress address = InetAddress.getByName(null);
            socket = new Socket(address, PORT_NUMBER);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private void processExpressionOnServer(String expression) {

    }
}

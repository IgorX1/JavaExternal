package com.javacourse;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.*;
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
        String expression, result;
        PrintWriter out;
        BufferedReader in;
        try {
            initSocket();
            out = initOutputStream();
            in = initInputStream();
            do{
                expression = getExpression();
                result = processExpressionOnServer(expression, out, in);
                showResult(result);
            }while (true);
        } catch (IOException e) {
            view.showMessage("Unable to establish the connection.");
            return;
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

    }

    String getExpression(){//TODO:  implement exiting from infinite loop by typing some phrase
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

    PrintWriter initOutputStream() throws IOException{
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream()
        )), true);
        return out;
    }

    BufferedReader initInputStream() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()
        ));
        return in;
    }

    String processExpressionOnServer(String expression, PrintWriter out, BufferedReader in) throws IOException {
        out.print(expression);
        return readFileFromServer(in);
    }

    String readFileFromServer(BufferedReader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = in.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Will parse the resulting string and, in the long run, will show the plot
     * @param result is an XML-file which is to be parsed in helper methods
     */
    void showResult(String result){

    }
}

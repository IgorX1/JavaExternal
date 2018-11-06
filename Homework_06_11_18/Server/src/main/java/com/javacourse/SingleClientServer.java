package com.javacourse;

import com.javacourse.Calculations.CalculationController;

import java.io.*;
import java.net.Socket;
import static com.javacourse.App.logger;

/**
 * Independently processes a single clients.
 * Implements multithreading functionality
 */
public class SingleClientServer extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;


    public SingleClientServer(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream()
        )), true);
        logger.info("New client connected");
        start();
    }

    @Override
    public void run() {
        while (true) {
            logger.info("New thread is running");
            CalculationController controller = new CalculationController();
            String request, result = null;
            try {
                logger.info("New calc");
                request = readAllTextFromInputStream(in);
                System.out.printf("New request "+request);
                logger.info("New request:"+request);
                result = controller.processClient(request);
            } catch (IOException e) {
                logger.error(e.getMessage());
                System.out.println("Can't get input from client");
            }
            sendResponseToClient(result);
        }
    }

    String readAllTextFromInputStream(BufferedReader in) throws IOException {
        /*StringBuilder sb = new StringBuilder();
        String line;
        while((line = in.readLine())!=null){
            sb.append(line);
        }
        return sb.toString();*/
        return in.readLine();
    }

    void sendResponseToClient(String result){
        out.print(result);
    }
}

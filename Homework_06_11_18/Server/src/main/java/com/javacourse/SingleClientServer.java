package com.javacourse;

import com.javacourse.Calculations.CalculationController;
import org.w3c.dom.Document;

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
    private ObjectOutputStream out;

    public SingleClientServer(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new ObjectOutputStream(socket.getOutputStream());

        logger.info("New client connected");
        start();
    }

    @Override
    public void run() {
        while (true) {
            CalculationController controller = new CalculationController();
            String request;
            Document result;
            try {
                request = readAllTextFromInputStream(in);
                result = controller.processClient(request);
            } catch (IOException e) {
                logger.error(e.getMessage());
                System.out.println("Client either disconnected or some problems with network occurred");
                System.out.println("Server is still working...");
                return;
            }
            sendResponseToClient(result);
        }
    }

    String readAllTextFromInputStream(BufferedReader in) throws IOException {
        return in.readLine();
    }

    void sendResponseToClient(Document result){
        try {
            out.writeObject(result);
        } catch (IOException e) {
            logger.error("Could not respond user due to IO problems");
            System.out.println("Could not respond user due to IO problems");
        }
    }
}

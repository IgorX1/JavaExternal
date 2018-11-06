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
        //super.run();
        CalculationController controller = new CalculationController();
        controller.processClient(in, out);
    }
}

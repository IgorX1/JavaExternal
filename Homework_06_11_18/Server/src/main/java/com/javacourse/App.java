package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App
{
    private static final int PORT_NUMBER = 8080;
    public static final Logger logger;

    static {
        logger = Logger.getLogger(RespondXMLFormatter.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main( String[] args )
    {
        ServerSocket s = null;
        try {
            s = new ServerSocket(PORT_NUMBER);
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.out.println("Can't run the server.");
            return;
        }
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = s.accept();
                try {
                    new SingleClientServer(socket);
                }
                catch (IOException e) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        logger.error(e1.getMessage());
                    }
                }
            }
        }catch (IOException e){
            logger.error(e.getMessage());
        }
        finally {
            try {
                s.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}

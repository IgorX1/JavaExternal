package com.javacourse.Calculations;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.net.Socket;

public class CalculationController {
    private static final Logger logger;
    private Socket socket;

    static {
        logger = Logger.getLogger(CalculationController.class);
        DOMConfigurator.configure("log/log4j.xml");
    }
}

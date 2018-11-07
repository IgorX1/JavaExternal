package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
        String expression;
        Document result;
        PrintWriter out;
        ObjectInputStream in;
        try {
            initSocket();
            out = initOutputStream();
            in = initInputStream();
            do{
                try {
                    expression = getExpressionOrExit();
                } catch (Exception e) {
                    return;
                }
                result = processExpressionOnServer(expression, out, in);
                showResult(result);
            }while (true);
        } catch (IOException | NullPointerException | ClassNotFoundException e) {
            view.showMessage("Unable to establish the connection.");
            return;
        }finally {
            try {
                socket.close();
            } catch (IOException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }

    }

    String getExpressionOrExit(){//TODO:  implement exiting from infinite loop by typing some phrase
        view.showMessage("Enter the expression to calculate or type END");
        view.showMessage("Allowed operations:+ - / * sin lg ()");
        String expr = getInputFromConsole();
        if(expr.equalsIgnoreCase("END"))
            throw new ProgramShouldBeTerminatedException();
        return expr;
    }

    private String getInputFromConsole(){
        return scanner.nextLine();
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

    ObjectInputStream initInputStream() throws IOException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return in;
    }

    Document processExpressionOnServer(String expression, PrintWriter out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        out.println(expression);
        logger.info("Put "+expression+" to the server");
        return readFileFromServer(in);
    }

    Document readFileFromServer(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Document res = (Document) in.readObject();
        logger.debug(res);
        return res;
    }

    /**
     * Will parse the resulting string and, in the long run, will show the plot.
     * In case server couldn't process the request, null is returned, so this method
     * can cope with that as well.
     * @param result is an XML-file which is to be parsed in helper methods
     */
    void showResult(Document result){
        List<Point> points = getPlottingPoints(result);
        showPlotByPoints(points);
    }

    List<Point> getPlottingPoints(Document xmlDoc){
        List<Point> points = new ArrayList<>();
        String x, y;
        Element pointsElem = (Element) xmlDoc.getElementsByTagName("points").item(0);
        NodeList xmlPoints = pointsElem.getElementsByTagName("point");
        for(int i=0; i<xmlPoints.getLength(); ++i){
            Element point = (Element) xmlPoints.item(i);
            x = point.getAttribute("x");
            y = point.getAttribute("y");
            points.add(new Point(x,y));
        }
        return points;
    }

    private void showPlotByPoints(List<Point> points) {
        XYSeries series = new XYSeries("Resulting plot");

        for(Point p : points){
            series.add(Double.parseDouble(p.getX()), Double.parseDouble(p.getY()));
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("Resulting plot", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame frame = new JFrame("MinimalStaticChart");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(400,300);
        frame.setVisible(true);
    }
}

package com.javacourse;

import org.w3c.dom.Document;

import java.util.List;

public class ClientConsoleView implements ClientView {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showPlotFromXml(String xml){
        //create DOM model of xml
        Document xmlDoc = createXmlFromString();
        //get plotting points
        List<Point> points = getPlottingPoints(xmlDoc);
        //print plot
        showPlotByPoints();
    }

    //Move these methods to controller

    //TODO: get rid of these functions, as they were moved to controller

    private Document createXmlFromString() {
        return null;
    }

    List<Point>  getPlottingPoints(Document xmlDoc){
        return null;
    }

    private void showPlotByPoints() {

    }
}

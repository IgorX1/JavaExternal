package com.javacourse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.List;

public class PlotVisualizer {
    public static void showPlotByPoints(List<Point> points) throws NumberFormatException {
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

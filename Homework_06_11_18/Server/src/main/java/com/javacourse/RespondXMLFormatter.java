package com.javacourse;

import com.javacourse.Calculations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RespondXMLFormatter {
    private String expression;
    private boolean isExpressionParametrized;
    private static final Logger logger;
    private static final int  LOWER_BOUND_FOR_PLOT = 0;
    private static final int HIGHER_BOUND_FOR_PLOT = 10;

    static {
        logger = Logger.getLogger(RespondXMLFormatter.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public RespondXMLFormatter(String expression) {
        this.expression = expression;
    }

    public Document processClient(){
        Document result = getXmlWithResult();
        return result;
    }

    Document getXmlWithResult(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
        Document doc = db.newDocument();

        Element errors = doc.createElement("errors");
        String  result = null;
        ExpressionProcessor processor = new ExpressionProcessor(expression);
        try {
            result = processor.calculateExpression(expression);
        } catch (UnsuccessfulExpressionProcessingException e) {
            Element error = doc.createElement("error");
            errors.appendChild(error);
        }
        Element root = doc.createElement("result");
        root.setAttribute("value", result);
        doc.appendChild(root);
        root.appendChild(errors);

        Element points = doc.createElement("points");
        root.appendChild(points);

        for(int i=LOWER_BOUND_FOR_PLOT; i<=HIGHER_BOUND_FOR_PLOT; ++i){
            Element point = doc.createElement("point");
            point.setAttribute("x", Integer.toString(i));
            try {
                point.setAttribute("y",
                        processor.calculateExpressionReplacingVariableWithNumber(expression, i));
            } catch (UnsuccessfulExpressionProcessingException e) {
                logger.error("Unable to process parametrized expression");
            }
            points.appendChild(point);
        }

        Element boundaries = doc.createElement("boundaries");
        boundaries.setAttribute("low",  Integer.toString(LOWER_BOUND_FOR_PLOT));
        boundaries.setAttribute("high", Integer.toString(HIGHER_BOUND_FOR_PLOT));
        root.appendChild(boundaries);
        return doc;
    }
}

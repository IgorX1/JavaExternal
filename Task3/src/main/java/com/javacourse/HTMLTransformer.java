package com.javacourse;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

import static com.javacourse.App.logger;

public class HTMLTransformer {
    public static boolean transformToHTML(String stylePath, String inputPath, String outputPath){
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xsl = new StreamSource(new File(stylePath));
            Transformer transformer = factory.newTransformer(xsl);

            Source xmlText = new StreamSource(new File(inputPath));
            transformer.transform(xmlText, new StreamResult(new File(outputPath)));
        } catch (TransformerException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}

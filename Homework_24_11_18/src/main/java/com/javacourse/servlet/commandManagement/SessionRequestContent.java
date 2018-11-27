package com.javacourse.servlet.commandManagement;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Container for HttpRequest attributes
 */
public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public SessionRequestContent(HttpServletRequest request) {
        Enumeration<String> attributes = request.getAttributeNames();
        Enumeration<String> parameters = request.getParameterNames();
        Enumeration<String> sAttributes = request.getSession().getAttributeNames();

        while(attributes.hasMoreElements()){
            String key = attributes.nextElement();
            Object value = request.getAttribute(key);
            requestAttributes.put(key, value);
        }

        //TODO finish it
    }
}

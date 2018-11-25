package com.javacourse.listeners;

import com.javacourse.dbConnectionPool.DatabaseConnectionPoolResource;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private Logger logger;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        logger = Logger.getLogger(DatabaseConnectionPoolResource.class);
        DOMConfigurator.configure("log/log4j.xml");
        servletContext.setAttribute("logger", logger);
    }
}

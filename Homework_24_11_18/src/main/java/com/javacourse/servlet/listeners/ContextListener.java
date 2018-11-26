package com.javacourse.servlet.listeners;

import com.javacourse.dao.UserDAO;
import com.javacourse.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {

    private AtomicReference<UserDAO> dao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dao = new AtomicReference<>(new UserDAO());
        fillBasicSetOfUsers();
        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userDao", dao);
    }

    private void fillBasicSetOfUsers(){
        dao.get().add(new User(1, "Igor", "root", User.ROLE.ADMIN));
        dao.get().add(new User(2, "Molly", "root", User.ROLE.USER));
    }
}

package com.testing.demo.controller.listener;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.model.entity.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       sce.getServletContext().addListener(new SessionListener());
        sce.getServletContext().setAttribute(
                AttributesResourceManager.getProperty("attribute.servlet.context.logged.users"),
                new HashMap<User, HttpSession>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

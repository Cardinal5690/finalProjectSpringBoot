package com.testing.demo.controller.listener;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = (User) session.getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        ServletContext servletContext = session.getServletContext();
        Map<User, HttpSession> loggedUsers =
                (HashMap<User, HttpSession>) servletContext.getAttribute(
                        AttributesResourceManager.getProperty("attribute.servlet.context.logged.users"));
        loggedUsers.remove(user);
    }
}

package com.testing.demo.controller.util;

import com.testing.demo.model.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ContextUtil {
    private static Map<User, HttpSession> loggedUsers;

    public static boolean isUserInContext(HttpSession session, User user) {
        log.info("Check user in Context");
        getLoggedUsers(session);
        return loggedUsers.containsKey(user);
    }

    public static void logoutUser(User user) {
        HttpSession oldSession = loggedUsers.get(user);
        try {
            oldSession.invalidate();
        } catch (IllegalArgumentException e){
            log.error("Session already invalidated", e);
        }
    }

    public static void setAttributesToContext(HttpSession session, User user) {
        getLoggedUsers(session);
        loggedUsers.put(user, session);
    }

    @SuppressWarnings("unchecked")
    private static void getLoggedUsers(HttpSession session) {
        loggedUsers = (HashMap<User, HttpSession>) session.getServletContext().getAttribute(
                AttributesResourceManager.getProperty("attribute.servlet.context.logged.users"));
    }
}

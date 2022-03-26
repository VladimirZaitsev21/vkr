package ru.zvo.walkingroutesgh.adminpanel.listeners;

import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.UserDAO;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static final int MAX_INACTIVE_INTERVAL = 600;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user.setOnline(false);
            SpringContext.getBean(UserDAO.class).saveExistingUser(user);
        }
    }
}

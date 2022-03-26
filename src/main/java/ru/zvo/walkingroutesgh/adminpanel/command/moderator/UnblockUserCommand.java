package ru.zvo.walkingroutesgh.adminpanel.command.moderator;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnblockUserCommand implements Command {

    private UserDAO userDAO;

    public UnblockUserCommand() {
        userDAO = SpringContext.getBean(UserDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        userDAO.setUserBlocked(login, false);
        req.getSession().setAttribute("users", userDAO.getAllUsers());
        new UsersPageCommand().execute(req, resp);
    }

}
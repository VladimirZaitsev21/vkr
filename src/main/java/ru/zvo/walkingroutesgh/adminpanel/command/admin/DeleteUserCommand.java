package ru.zvo.walkingroutesgh.adminpanel.command.admin;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand implements Command {

    private UserDAO userDAO;

    public DeleteUserCommand() {
        userDAO = SpringContext.getBean(UserDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println(userDAO.getUserById(Long.parseLong(req.getParameter("user_id"))));
        userDAO.deleteUser(Long.parseLong(req.getParameter("user_id")));
        req.getSession().setAttribute("users", userDAO.getAllUsers());
        new AdminMainPageCommand().execute(req, resp);
    }

}

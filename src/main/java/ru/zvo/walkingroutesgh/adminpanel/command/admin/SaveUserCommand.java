package ru.zvo.walkingroutesgh.adminpanel.command.admin;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.UserDAO;
import ru.zvo.walkingroutesgh.dto.Role;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class SaveUserCommand implements Command {

    private UserDAO userDAO;

    public SaveUserCommand() {
        userDAO = SpringContext.getBean(UserDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getParameter("user_id") == null) {
            User user = new User(
                    req.getParameter("user_login"),
                    req.getParameter("user_password"),
                    Role.valueOf(req.getParameter("user_role").toUpperCase(Locale.ROOT)),
                    false,
                    false
            );
            System.out.println(user);
            userDAO.saveNewUser(user);
        } else {
            User user = userDAO.getUserById(Long.parseLong(req.getParameter("user_id")));
            user.setLogin(req.getParameter("user_login"));
            user.setPassword(req.getParameter("user_password"));
            user.setRole(Role.valueOf(req.getParameter("user_role").toUpperCase(Locale.ROOT)));
            user.setBlocked(Boolean.parseBoolean(req.getParameter("user_blocked")));
            userDAO.saveExistingUser(user);
        }
        req.getSession().setAttribute("users", userDAO.getAllUsers());
//        resp.sendRedirect("/admin-main");
        new AdminMainPageCommand().execute(req, resp);
    }
}

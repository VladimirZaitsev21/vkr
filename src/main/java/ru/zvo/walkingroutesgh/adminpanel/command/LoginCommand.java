package ru.zvo.walkingroutesgh.adminpanel.command;

import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.SightsDAO;
import ru.zvo.walkingroutesgh.dao.UserDAO;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private UserDAO userDAO = SpringContext.getBean(UserDAO.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = userDAO.getUserByLogin(req.getParameter("login"));
        if (user == null || !user.getPassword().equals(req.getParameter("password"))) {
            req.getSession().setAttribute("reason", "Указанной пары логина и пароля не существует!");
            new LoginPageCommand().execute(req, resp);
        } else {
            req.getSession().removeAttribute("reason");
            req.getSession().setAttribute("user", user);
            user.setOnline(true);
            userDAO.saveExistingUser(user);
            switch (user.getRole()) {
                case ADMIN:
                    req.getSession().setAttribute("users", userDAO.getAllUsers());
                    resp.sendRedirect(req.getContextPath() + "/frontController?command=admin_main_page");
                    break;
                case MODERATOR:
                    resp.sendRedirect(req.getContextPath() + "/frontController?command=moderator_main_page");
                    break;
                case HISTORIAN:
                    resp.sendRedirect(req.getContextPath() + "/frontController?command=historian_main_page");
                    break;
            }
        }
    }

}

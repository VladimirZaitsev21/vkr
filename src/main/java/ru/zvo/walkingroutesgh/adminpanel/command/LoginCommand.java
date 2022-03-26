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
        if (!user.getPassword().equals(req.getParameter("password"))) {
            req.getSession().setAttribute("reason", "Указанной пары логина и пароля не существует!");
            resp.sendRedirect("/login");
        } else {
            req.getSession().removeAttribute("reason");
            req.getSession().setAttribute("user", user);
//            req.getSession().setAttribute("sightsDAO", SpringContext.getBean(SightsDAO.class));
            user.setOnline(true);
            userDAO.saveExistingUser(user);
            switch (user.getRole()) {
                case ADMIN:
                    req.getSession().setAttribute("users", userDAO.getAllUsers());
//                    resp.sendRedirect("/admin-main");
                    resp.sendRedirect(req.getContextPath() + "/frontController?command=admin_main_page");
                    break;
                case MODERATOR:
//                    resp.sendRedirect("/moderator-main");
                    resp.sendRedirect(req.getContextPath() + "/frontController?command=moderator_main_page");
                    break;
                case HISTORIAN:
//                    resp.sendRedirect("/historic-main");
                    resp.sendRedirect(req.getContextPath() + "/frontController?command=historian_main_page");
                    break;
            }
        }
    }

}

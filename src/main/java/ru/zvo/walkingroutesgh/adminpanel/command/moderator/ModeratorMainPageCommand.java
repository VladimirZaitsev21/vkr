package ru.zvo.walkingroutesgh.adminpanel.command.moderator;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModeratorMainPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/jsp/moderator-main-page.jsp").forward(req, resp);
    }
}

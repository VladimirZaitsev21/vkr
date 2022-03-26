package ru.zvo.walkingroutesgh.adminpanel.command.historian;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.SightsDAO;
import ru.zvo.walkingroutesgh.dto.Sight;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditSightPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Sight> sights = SpringContext.getBean(SightsDAO.class).getAllSights();
        System.out.println(sights.size());
        req.setAttribute("sights", sights);
        req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/jsp/edit-sights.jsp").forward(req, resp);
    }
}

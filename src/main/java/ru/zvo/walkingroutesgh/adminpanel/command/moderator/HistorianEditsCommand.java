package ru.zvo.walkingroutesgh.adminpanel.command.moderator;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.EditsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistorianEditsCommand implements Command {

    private EditsDAO editsDAO;

    public HistorianEditsCommand() {
        editsDAO = SpringContext.getBean(EditsDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("edits", editsDAO.getAllEdits());
        req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/jsp/historian-edits.jsp").forward(req, resp);
    }
}

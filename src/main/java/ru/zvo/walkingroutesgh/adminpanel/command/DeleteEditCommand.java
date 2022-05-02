package ru.zvo.walkingroutesgh.adminpanel.command;

import ru.zvo.walkingroutesgh.adminpanel.command.historian.MyEditsCommand;
import ru.zvo.walkingroutesgh.adminpanel.command.moderator.HistorianEditsCommand;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.EditsDAO;
import ru.zvo.walkingroutesgh.dto.Role;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEditCommand implements Command {

    private EditsDAO editsDAO;

    public DeleteEditCommand() {
        editsDAO = SpringContext.getBean(EditsDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        editsDAO.deleteEdit(Long.parseLong(req.getParameter("edit_id")));
        String url = null;
        if (((User) req.getSession().getAttribute("user")).getRole().equals(Role.HISTORIAN)) {
            req.getSession().setAttribute("edits", editsDAO.getUserEdits((User) req.getSession().getAttribute("user")));
//            url = "/my-edits";
//            new MyEditsCommand().execute(req, resp);
            resp.sendRedirect(req.getContextPath() + "/frontController?command=my_edits");
        }
        if (((User) req.getSession().getAttribute("user")).getRole().equals(Role.MODERATOR)) {
//            url = "/historian-edits";
            req.getSession().setAttribute("edits", editsDAO.getAllEdits());
            new HistorianEditsCommand().execute(req, resp);
        }
//        resp.sendRedirect(url);
    }
}

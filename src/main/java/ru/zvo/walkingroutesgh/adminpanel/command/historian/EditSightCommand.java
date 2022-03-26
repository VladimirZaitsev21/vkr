package ru.zvo.walkingroutesgh.adminpanel.command.historian;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.EditsDAO;
import ru.zvo.walkingroutesgh.dao.SightsDAO;
import ru.zvo.walkingroutesgh.dto.Edit;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditSightCommand implements Command {

    private EditsDAO editsDAO;

    public EditSightCommand() {
        editsDAO = SpringContext.getBean(EditsDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("EDIT_SIGHT_COMMAND");
        User user = (User) req.getSession().getAttribute("user");
        if (user.isBlocked()) {
            resp.sendRedirect("/edit-sights");
            return;
        }
        SightsDAO sightsDAO = SpringContext.getBean(SightsDAO.class);
        String editIdString = req.getParameter("edit_id");
        Edit edit = null;
        if (editIdString != null) {
            edit = new Edit(
                    Long.parseLong(req.getParameter("edit_id")),
                    user,
                    sightsDAO.getSightById(Long.parseLong(req.getParameter("osmId"))),
                    req.getParameter("name"),
                    req.getParameter("description")
            );
            req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/jsp/my-edits.jsp").forward(req, resp);
        } else {
            System.out.println("EDIT_SIGHT_COMMAND else");
            edit = new Edit(
                    user,
                    sightsDAO.getSightById(Long.parseLong(req.getParameter("osmId"))),
                    req.getParameter("name"),
                    req.getParameter("description")
            );
            new EditSightPageCommand().execute(req, resp);
        }
        editsDAO.save(edit);
//        resp.sendRedirect("/edit-sights");
    }

}

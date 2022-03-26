package ru.zvo.walkingroutesgh.adminpanel.command.moderator;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.EditsDAO;
import ru.zvo.walkingroutesgh.dao.SightsDAO;
import ru.zvo.walkingroutesgh.dto.Edit;
import ru.zvo.walkingroutesgh.dto.Sight;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveEditCommand implements Command {

    private EditsDAO editsDAO;
    private SightsDAO sightsDAO;

    public SaveEditCommand() {
        editsDAO = SpringContext.getBean(EditsDAO.class);
        sightsDAO = SpringContext.getBean(SightsDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Edit edit = editsDAO.getEditById(Long.parseLong(req.getParameter("edit_id")));
        Sight sight = edit.getSight();
        sight.setDescription(req.getParameter("new_description"));
        sight.setName(req.getParameter("new_name"));
        sightsDAO.saveSight(sight);
        editsDAO.deleteEdit(edit.getId());
        req.getSession().setAttribute("edits", editsDAO.getAllEdits());
        new HistorianEditsCommand().execute(req, resp);
    }
}

package ru.zvo.walkingroutesgh.adminpanel.command.historian;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.config.SpringContext;
import ru.zvo.walkingroutesgh.dao.EditsDAO;
import ru.zvo.walkingroutesgh.dto.Edit;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyEditsCommand implements Command {

    private EditsDAO editsDAO;

    public MyEditsCommand() {
        this.editsDAO = SpringContext.getBean(EditsDAO.class);
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        List<Edit> userEdits = editsDAO.getUserEdits(user);
        req.getSession().setAttribute("edits", userEdits);
        req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/jsp/my-edits.jsp").forward(req, resp);
    }
}

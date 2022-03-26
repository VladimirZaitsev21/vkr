package ru.zvo.walkingroutesgh.adminpanel.servlets;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.adminpanel.command.CommandType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = defineCommand(req);
        command.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = defineCommand(req);
        command.execute(req, resp);
    }

    private Command defineCommand(HttpServletRequest req) {
        return CommandType.getTypeByName(req.getParameter("command")).getCommand();
    }

}

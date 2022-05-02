package ru.zvo.walkingroutesgh.adminpanel.servlets;

import ru.zvo.walkingroutesgh.adminpanel.command.Command;
import ru.zvo.walkingroutesgh.adminpanel.command.CommandType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Combines all request processing by passing requests through a single handler object - itself.
 * After processing the request, the controller accesses a specific object to work out a specific behavior.
 *
 * @author Vladimir Zaitsev
 */
public class FrontController extends HttpServlet {

    /**
     * Processes an HTTP request with the GET method.
     *
     * @param req GET HTTP request
     * @param resp response to the client sent request
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty
     * @throws IOException Input/Output exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = defineCommand(req);
        command.execute(req, resp);
    }

    /**
     * Processes an HTTP request with the POST method.
     *
     * @param req GET HTTP request
     * @param resp response to the client sent request
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty
     * @throws IOException Input/Output exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = defineCommand(req);
        command.execute(req, resp);
    }

    /**
     * Defines command which should be executed by 'command' request parameter.
     * @param req GET HTTP request
     * @return command which should be executed
     */
    private Command defineCommand(HttpServletRequest req) {
        return CommandType.getTypeByName(req.getParameter("command")).getCommand();
    }

}

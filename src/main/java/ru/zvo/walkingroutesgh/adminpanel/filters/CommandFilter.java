package ru.zvo.walkingroutesgh.adminpanel.filters;

import ru.zvo.walkingroutesgh.adminpanel.command.CommandType;
import ru.zvo.walkingroutesgh.dto.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CommandFilter implements Filter {

    public static final String REASON_MESSAGE = "У вас недостаточно прав для выполнения этого действия!";
    private Set<CommandType> publicCommands = new HashSet<>();

    public CommandFilter() {
        publicCommands.add(CommandType.LOGIN);
        publicCommands.add(CommandType.LOGIN_PAGE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String commandName = req.getParameter("command");
        CommandType commandType = CommandType.getTypeByName(commandName.toUpperCase(Locale.ROOT));
        User user = (User) req.getSession().getAttribute("user");
        if (publicCommands.contains(commandType) || assertAction(user, commandType)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.setAttribute("reason", REASON_MESSAGE);
            req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/jsp/login.jsp").forward(servletRequest, servletResponse);
        }
    }

    private boolean assertAction(User user, CommandType commandType) {
        return user != null && user.isOnline() && commandType.canRoleInvokeCommand(user.getRole());
    }

}

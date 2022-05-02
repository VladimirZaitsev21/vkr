<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
    <link rel="stylesheet" href="../../static/bootstrap.min.css">
</head>
<body>
<%--    <c:forEach var="user" items="${users}">--%>
<%--        <c:if test="${user.blocked  == false and user.role == 'HISTORIAN'}">--%>
<%--            <form action="frontController" method="post">--%>
<%--                <input type="hidden" name="command" value="block_user">--%>
<%--                <input type="text" readonly name="login" value="${user.login}">--%>
<%--                <input type="submit" value="Заблокировать">--%>
<%--            </form>--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-uppercase mb-0">Пользователи системы</h5>
                </div>
                <div class="table-responsive">
                    <%--                    <form id="user_form" method="post" action="frontController"></form>--%>
                    <table class="table no-wrap user-table mb-0">
                        <thead>
                        <tr>
                            <th scope="col" class="border-0 text-uppercase font-medium">Логин</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Действие</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="user" items="${users}">
                            <c:if test="${user.blocked  == false and user.role == 'HISTORIAN'}">
                            <tr>
                                <td>
                                    <form id="${user.login}" method="post" action="frontController">
                                        <input type="text" readonly class="form-control input" name="login" value="${user.login}" form="${user.login}"/>
                                    </form>
                                </td>
                                <td>
                                    <input type="hidden" id="servlet_command" class="command_input" name="command" value="block_user" form="${user.login}"/>
                                    <input type="submit" class="btn btn-primary btn-block mb-4" value="Заблокировать" form="${user.login}"/>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%--    <c:forEach var="user" items="${users}">--%>
<%--        <c:if test="${user.blocked  == true and user.role == 'HISTORIAN'}">--%>
<%--            <form action="frontController" method="post">--%>
<%--                <input type="hidden" name="command" value="unblock_user">--%>
<%--                <input type="text" readonly name="login" value="${user.login}">--%>
<%--                <input type="submit" value="Разблокировать">--%>
<%--            </form>--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-uppercase mb-0">Заблокированные пользователи системы</h5>
                </div>
                <div class="table-responsive">
                    <%--                    <form id="user_form" method="post" action="frontController"></form>--%>
                    <table class="table no-wrap user-table mb-0">
                        <thead>
                        <tr>
                            <th scope="col" class="border-0 text-uppercase font-medium">Логин</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Действие</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="user" items="${users}">
                            <c:if test="${user.blocked  == true and user.role == 'HISTORIAN'}">
                                <tr>
                                    <td>
                                        <form id="${user.login}" method="post" action="frontController">
                                            <input type="text" readonly class="form-control input" name="login" value="${user.login}" form="${user.login}"/>
                                        </form>
                                    </td>
                                    <td>
                                        <input type="hidden" class="command_input" name="command" value="unblock_user" form="${user.login}"/>
                                        <input type="submit" class="btn btn-primary btn-block mb-4" value="Разблокировать" form="${user.login}"/>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
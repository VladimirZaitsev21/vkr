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
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="../../static/bootstrap.min.css">
</head>
<body>
<h2>Здравствуйте, администратор ${user.login}!</h2>
<p>Пользователи системы:</p>
    <button id="edit_users" onclick="makeEditable()">Редактировать</button>
    <form action="frontController" method="get">
        <input type="hidden" name="command" value="add_user_page">
        <input type="submit" value="Создать нового">
    </form>
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
                            <th scope="col" class="border-0 text-uppercase font-medium">id</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Логин</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Пароль</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Роль</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Управление</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>
                                    <form id="${user.id}" method="post" action="frontController"><input type="hidden" id="user_id" name="user_id" value="${user.id}" form="${user.id}"/></form>
                                </td>
                                <td>
                                    <input type="hidden" id="servlet_command" class="command_input" name="command" value="delete_user" form="${user.id}"/>
                                    <input  readonly id="user_login" class="form-control input" name="user_login" value="${user.login}" form="${user.id}"/>
                                </td>
                                <td>
                                    <input readonly id="user_password" class="form-control input" name="user_password" value="${user.password}" form="${user.id}"/>
                                </td>
                                <td>


                                    <c:choose>

                                        <c:when test="${user.role == 'ADMIN'}">
                                            <select disabled name="user_role" class="form-control category-select" id="roles" form="${user.id}">
                                                <option selected value="admin">Администратор</option>
                                                <option value="moderator">Модератор</option>
                                                <option value="historian">Краевед</option>
                                            </select>
                                        </c:when>

                                        <c:when test="${user.role == 'MODERATOR'}">
                                            <select disabled name="user_role" class="form-control category-select" id="roles" form="${user.id}">
                                                <option value="admin">Администратор</option>
                                                <option selected value="moderator">Модератор</option>
                                                <option value="historian">Краевед</option>
                                            </select>
                                        </c:when>

                                        <c:when test="${user.role == 'HISTORIAN'}">
                                            <select disabled name="user_role" class="form-control category-select" id="roles" form="${user.id}">
                                                <option value="admin">Администратор</option>
                                                <option value="moderator">Модератор</option>
                                                <option selected value="historian">Краевед</option>
                                            </select>
                                        </c:when>

                                    </c:choose>

                                </td>
                                <td>
                                    <input name="submit_input" type="submit" class="btn btn-danger btn-block mb-4" form="${user.id}" value="Удалить"/>
                                </td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

    <p>Пользователи онлайн:</p>
    <ul>
        <c:forEach var="user" items="${users}">
            <c:if test="${user.online == 'true'}">
                <li>${user.login}</li>
            </c:if>
        </c:forEach>
    </ul>
</body>
<script>
    function makeEditable() {
        let commands = document.getElementsByName('command');
        let logins = document.getElementsByName('user_login');
        let passwords = document.getElementsByName('user_password');
        let roles = document.getElementsByName('user_role');

        let buttons = document.getElementsByName('submit_input');
        for (let i = 0; i < commands.length; i++) {
            commands[i].value = 'save_user';
            logins[i].removeAttribute('readonly');
            passwords[i].removeAttribute('readonly');
            roles[i].disabled = false;
            buttons[i].value = 'Сохранить';
            buttons[i].className = 'btn btn-success btn-block mb-4';
        }
    }
</script>
</html>
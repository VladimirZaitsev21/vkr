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
</head>
<body>
<h2>Здравствуйте, администратор ${user.login}!</h2>
<p>Пользователи системы:</p>
    <button id="edit_users" onclick="makeEditable()">Редактировать</button>
    <form action="frontController" method="get">
        <input type="hidden" name="command" value="add_user_page">
        <input type="submit" value="Создать нового">
    </form>
    <c:forEach var="user" items="${users}">
        <form method="post" action="frontController">
            <input type="hidden" id="servlet_command" class="command_input" name="command" value="delete_user">
            <label for="user_id">id: </label>
            <input readonly id="user_id" name="user_id" value="${user.id}"/>
            <label for="user_login">login: </label>
            <input readonly id="user_login" class="login_input" name="user_login" value="${user.login}"/>
            <label for="user_password">password: </label>
            <input readonly id="user_password" class="password_input" name="user_password" value="${user.password}"/>
            <label for="roles">Роль: </label>
            <c:choose>

                <c:when test="${user.role == 'ADMIN'}">
                    <select disabled name="user_role" class="roles_input" id="roles">
                        <option selected value="admin">Администратор</option>
                        <option value="moderator">Модератор</option>
                        <option value="historian">Краевед</option>
                    </select>
                </c:when>

                <c:when test="${user.role == 'MODERATOR'}">
                    <select disabled name="user_role" class="roles_input" id="roles">
                        <option value="admin">Администратор</option>
                        <option selected value="moderator">Модератор</option>
                        <option value="historian">Краевед</option>
                    </select>
                </c:when>

                <c:when test="${user.role == 'HISTORIAN'}">
                    <select disabled name="user_role" class="roles_input" id="roles">
                        <option value="admin">Администратор</option>
                        <option value="moderator">Модератор</option>
                        <option selected value="historian">Краевед</option>
                    </select>
                </c:when>

            </c:choose>
            <input id="submit_button" type="submit" class="submit_input" name="delete" value="Удалить">
        </form>
    </c:forEach>

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
        let commands = document.getElementsByClassName('command_input');
        let logins = document.getElementsByClassName('login_input');
        let passwords = document.getElementsByClassName('password_input');
        let roles = document.getElementsByClassName('roles_input');
        let buttons = document.getElementsByClassName('submit_input');
        for (let i = 0; i < commands.length; i++) {
            commands[i].value = 'save_user';
            logins[i].removeAttribute('readonly');
            passwords[i].removeAttribute('readonly');
            roles[i].disabled = false;
            buttons[i].value = 'Сохранить';
        }
    }
</script>
</html>
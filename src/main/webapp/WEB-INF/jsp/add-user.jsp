<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>Добавление пользователя</h2>
    <form action="frontController" method="post">
        <input type="hidden" name="command" value="save_user">
        <label for="user_login">Логин: </label>
        <input type="text" name="user_login" id="user_login">
        <label for="user_password">Пароль: </label>
        <input type="text" name="user_password" id="user_password">
        <label for="roles">Роль:</label>
        <select name="user_role" id="roles">
            <option value="admin">Администратор</option>
            <option value="moderator">Модератор</option>
            <option selected value="historian">Краевед</option>
        </select>
        <input type="submit" value="Сохранить">
    </form>
</body>
</html>
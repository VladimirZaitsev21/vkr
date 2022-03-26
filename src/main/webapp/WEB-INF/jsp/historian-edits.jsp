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
    <p>Имеющиеся правки:</p>
    <c:forEach var="edit" items="${edits}">
        <form method="post" action="frontController">
            <input id="edit_id" type="hidden" name="edit_id" value="${edit.id}">
            <label for="osmId">OSM ID: </label>
            <input id="osmId" type="text" readonly name="osmId" value="${edit.sight.osmId}">
            <label for="old_name">Название объекта: </label>
            <input id="old_name" type="text" readonly name="old_name" value="${edit.sight.name}">
            <label for="old_description">Описание объекта: </label>
            <input id="old_description" type="text" readonly name="old_description" value="${edit.sight.description}">
            <label for="new_name">Предлагаемое название: </label>
            <input id="new_name" type="text" name="new_name" value="${edit.sightName}">
            <label for="new_description">Предлагаемое описание: </label>
            <input id="new_description" type="text" name="new_description" value="${edit.sightDescription}">
            <label for="user_login">Краевед: </label>
            <input id="user_login" type="text" readonly name="user_login" value="${edit.user.login}">
            <button type="submit" name="command" value="delete_edit">Удалить</button>
            <button type="submit" name="command" value="save_edit">Сохранить</button>
        </form>
<%--        <a href="frontController"--%>
        <br>
    </c:forEach>
</body>
</html>
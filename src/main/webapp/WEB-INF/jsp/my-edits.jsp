<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>Внесенные вами правки</p>
<c:forEach var="edit" items="${edits}">
    <form action="frontController" method="post">
        <input type="hidden" name="command" value="delete_edit">
        <label for="id">id: </label>
        <input id="id" type="text" name="edit_id" value="${edit.id}">
        <label for="sight_id">id достопримечательности: </label>
        <input id="sight_id" type="text" value="${edit.sight.osmId}">
        <label for="osm_name">Название: </label>
        <input id="osm_name" type="text" name="name" value="${edit.sightName}">
        <label for="description">Описание: </label>
        <input id="description" type="text" name="description" value="${edit.sightDescription}">
        <input id="submit" type="submit" value="Удалить">
    </form>
    <br>
</c:forEach>
</body>
</html>
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
<p>Доступные к редактированию достопримечательности</p>
<c:forEach var="sight" items="${sights}">
    <form action="frontController" method="post">
        <input type="hidden" name="command" value="edit_sight">
        <label for="osm_id">OSM id: </label>
        <input id="osm_id" type="text" name="osmId" value="${sight.osmId}">
        <label for="osm_name">Название: </label>
        <input id="osm_name" type="text" name="name" value="${sight.name}">
        <label for="description">Описание: </label>
        <input id="description" type="text" name="description" value="${sight.description}">
        <input id="submit" type="submit" value="Сохранить">
    </form>
    <br>
</c:forEach>
</body>
</html>
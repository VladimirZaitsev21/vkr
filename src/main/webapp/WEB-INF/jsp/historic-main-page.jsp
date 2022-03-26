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
    <h2>Здравствуйте, ${user.login}!</h2>
<%--    <a href="/edit-sights">К списку достопримечательностей</a>--%>
    <form method="get" action="frontController">
        <input type="hidden" name="command" value="edit_sights_page">
        <input type="submit" value="К списку достопримечательностей">
    </form>
    <form method="get" action="frontController">
        <input type="hidden" name="command" value="my_edits">
        <input type="submit" value="Мои правки">
    </form>
</body>
</html>
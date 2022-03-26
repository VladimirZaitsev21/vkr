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
<h2>Здравствуйте, модератор ${user.login}!</h2>
<form action="frontController" method="get">
    <input type="hidden" name="command" value="users_page">
    <input type="submit" value="Пользователи">
</form>

<form action="frontController" method="get">
    <input type="hidden" name="command" value="historian_edits">
    <input type="submit" value="Правки краеведов">
</form>
</body>
</html>
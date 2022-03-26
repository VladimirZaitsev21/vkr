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
<p>Пользователи системы:</p>
    <c:forEach var="user" items="${users}">
        <c:if test="${user.blocked  == false and user.role == 'HISTORIAN'}">
            <form action="frontController" method="post">
                <input type="hidden" name="command" value="block_user">
                <input type="text" readonly name="login" value="${user.login}">
                <input type="submit" value="Заблокировать">
            </form>
        </c:if>
    </c:forEach>

<p>Заблокированные пользователи системы:</p>
    <c:forEach var="user" items="${users}">
        <c:if test="${user.blocked  == true and user.role == 'HISTORIAN'}">
            <form action="frontController" method="post">
                <input type="hidden" name="command" value="unblock_user">
                <input type="text" readonly name="login" value="${user.login}">
                <input type="submit" value="Разблокировать">
            </form>
        </c:if>
    </c:forEach>
</body>
</html>
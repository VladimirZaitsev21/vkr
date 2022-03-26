<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script type="text/javascript">
    <c:if test="${reason != null}">
        alert('${reason}');
    </c:if>
</script>
<body>
    <h1>Вход в учетную запись</h1>
    <form action="/frontController" method="post">
        <input type="hidden" name="command" value="login">
        <label>Логин:
            <input type="text" name="login"/>
        </label>
        <label>Пароль:
            <input type="password" name="password"/>
        </label>
        <input type="submit">
    </form>
<a href="/">Назад</a>
</body>
</html>
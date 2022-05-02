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
    <link rel="stylesheet" href="../../static/bootstrap.min.css">
</head>
<body>
<center><h2>Здравствуйте, модератор ${user.login}!</h2></center>
<form action="frontController" method="get" style="width: 30%; margin-left: 35%; margin-top: 5%">
    <input type="hidden" name="command" value="users_page">
    <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%">Пользователи</button>
<%--    <input type="submit" value="Пользователи">--%>
</form>

<form action="frontController" method="get" style="width: 30%; margin-left: 35%;">
    <input type="hidden" name="command" value="historian_edits">
    <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%">Правки краеведов</button>
<%--    <input type="submit" value="Правки краеведов">--%>
</form>
</body>
</html>

<%--<form action="/frontController" method="post" style="width: 30%; margin-left: 35%; margin-top: 5%">--%>
<%--    <input type="hidden" name="command" value="login">--%>
<%--    <div class="form-outline mb-4">--%>
<%--        <input type="text" name="login" id="form2Example1" class="form-control" />--%>
<%--        <label class="form-label" for="form2Example1">Логин</label>--%>
<%--    </div>--%>


<%--    <div class="form-outline mb-4">--%>
<%--        <input name="password" type="password" id="form2Example2" class="form-control" />--%>
<%--        <label class="form-label" for="form2Example2">Пароль</label>--%>
<%--    </div>--%>

<%--    <!-- Submit button -->--%>
<%--    <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%">Войти</button>--%>
<%--</form>--%>
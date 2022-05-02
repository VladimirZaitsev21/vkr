<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="../../static/bootstrap.min.css">
</head>
<script type="text/javascript">
    <c:if test="${reason != null}">
        alert('${reason}');
    </c:if>
</script>
<body>
    <h1 style="width: 30%; margin-left: 35%; position: center">Вход в учетную запись</h1>
    <form action="/frontController" method="post" style="width: 30%; margin-left: 35%; margin-top: 5%">
        <input type="hidden" name="command" value="login">
        <div class="form-outline mb-4">
            <input type="text" name="login" id="form2Example1" class="form-control" />
            <label class="form-label" for="form2Example1">Логин</label>
        </div>


        <div class="form-outline mb-4">
            <input name="password" type="password" id="form2Example2" class="form-control" />
            <label class="form-label" for="form2Example2">Пароль</label>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%">Войти</button>
    </form>
<%--    <form action="/frontController" method="post">--%>
<%--        <input type="hidden" name="command" value="login">--%>
<%--        <label>Логин:--%>
<%--            <input type="text" name="login"/>--%>
<%--        </label>--%>
<%--        <label>Пароль:--%>
<%--            <input type="password" name="password"/>--%>
<%--        </label>--%>
<%--        <input type="submit">--%>
<%--    </form>--%>
<a href="/" style="margin-left: 35%; position: center">Назад</a>
</body>
</html>
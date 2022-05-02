<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="../../static/bootstrap.min.css">
</head>
<body>
    <center><h2>Добавление пользователя</h2></center>

    <form action="frontController" method="post" style="width: 30%; margin-left: 35%; margin-top: 5%">
        <input type="hidden" name="command" value="save_user">
        <div class="form-outline mb-4">
            <input type="text" name="user_login" id="form2Example1" class="form-control" />
            <label class="form-label" for="form2Example1">Логин</label>
        </div>


        <div class="form-outline mb-4">
            <input name="user_password" type="text" id="form2Example2" class="form-control" />
            <label class="form-label" for="form2Example2">Пароль</label>
        </div>

        <select name="user_role" class="form-control category-select" id="roles">
            <option value="admin">Администратор</option>
            <option value="moderator">Модератор</option>
            <option selected value="historian">Краевед</option>
        </select>
        <label class="form-label" for="roles">Роль</label>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%; margin-top: 10px">Добавить</button>
    </form>
</body>
</html>
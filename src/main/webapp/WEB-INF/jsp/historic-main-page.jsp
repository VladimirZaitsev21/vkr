<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../static/bootstrap.min.css">
</head>
<body>
    <center><h2>Здравствуйте, ${user.login}!</h2></center>
<%--    <form method="get" action="frontController">--%>
<%--        <input type="hidden" name="command" value="edit_sights_page">--%>
<%--        <input type="submit" value="К списку достопримечательностей">--%>
<%--    </form>--%>
<%--    <form method="get" action="frontController">--%>
<%--        <input type="hidden" name="command" value="my_edits">--%>
<%--        <input type="submit" value="Мои правки">--%>
<%--    </form>--%>

    <form action="frontController" method="get" style="width: 30%; margin-left: 35%; margin-top: 5%">
        <input type="hidden" name="command" value="edit_sights_page">
        <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%">К списку достопримечательностей</button>
    </form>

    <form action="frontController" method="get" style="width: 30%; margin-left: 35%;">
        <input type="hidden" name="command" value="my_edits">
        <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%">Мои правки</button>
    </form>
</body>
</html>
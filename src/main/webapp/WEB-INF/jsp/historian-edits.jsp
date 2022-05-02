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
<%--    <p>Имеющиеся правки:</p>--%>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title text-uppercase mb-0">Имеющиеся правки</h5>
                    </div>
                    <div class="table-responsive">
                        <table class="table no-wrap user-table mb-0">
                            <thead>
                            <tr>
                                <th scope="col" class="border-0 text-uppercase font-medium">OSM ID</th>
                                <th scope="col" class="border-0 text-uppercase font-medium">Название объекта</th>
                                <th scope="col" class="border-0 text-uppercase font-medium">Описание объекта</th>
                                <th scope="col" class="border-0 text-uppercase font-medium">Предлагаемое название объекта</th>
                                <th scope="col" class="border-0 text-uppercase font-medium">Предлагаемое описание объекта</th>
                                <th scope="col" class="border-0 text-uppercase font-medium">Краевед</th>
                                <th scope="col" class="border-0 text-uppercase font-medium">Действие</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="edit" items="${edits}">
                                <tr>
                                    <td>
                                        <form id="${edit.id}" method="post" action="frontController">
                                            <input id="edit_id" type="hidden" name="edit_id" value="${edit.id}" form="${edit.id}">
                                            <input id="osmId" type="text" class="form-control input" readonly name="osmId" value="${edit.sight.osmId}" form="${edit.id}">
                                        </form>
                                    </td>
                                    <td>
                                        <input id="old_name" type="text" class="form-control input" readonly name="old_name" value="${edit.sight.name}" form="${edit.id}">
                                    </td>
                                    <td>
                                        <input id="old_description" type="text" class="form-control input" readonly name="old_description" value="${edit.sight.description}" form="${edit.id}">
                                    </td>
                                    <td>
                                        <input id="new_name" type="text" class="form-control input" name="new_name" value="${edit.sightName}" form="${edit.id}">
                                    </td>
                                    <td>
                                        <input id="new_description" type="text" class="form-control input" name="new_description" value="${edit.sightDescription}" form="${edit.id}">
                                    </td>
                                    <td>
                                        <input id="user_login" type="text" class="form-control input" readonly name="user_login" value="${edit.user.login}" form="${edit.id}">
                                    </td>
                                    <td class="d-flex">
                                        <button type="submit" class="btn btn-danger btn-block mb-4" name="command" value="delete_edit" form="${edit.id}">Отклонить</button>
                                        <button type="submit" class="btn btn-success btn-block mb-4" style="margin-left: 5px;" name="command" value="save_edit" form="${edit.id}">Принять</button>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--    <c:forEach var="edit" items="${edits}">--%>
<%--        <form method="post" action="frontController">--%>
<%--            <input id="edit_id" type="hidden" name="edit_id" value="${edit.id}">--%>
<%--            <label for="osmId">OSM ID: </label>--%>
<%--            <input id="osmId" type="text" readonly name="osmId" value="${edit.sight.osmId}">--%>
<%--            <label for="old_name">Название объекта: </label>--%>
<%--            <input id="old_name" type="text" readonly name="old_name" value="${edit.sight.name}">--%>
<%--            <label for="old_description">Описание объекта: </label>--%>
<%--            <input id="old_description" type="text" readonly name="old_description" value="${edit.sight.description}">--%>
<%--            <label for="new_name">Предлагаемое название: </label>--%>
<%--            <input id="new_name" type="text" name="new_name" value="${edit.sightName}">--%>
<%--            <label for="new_description">Предлагаемое описание: </label>--%>
<%--            <input id="new_description" type="text" name="new_description" value="${edit.sightDescription}">--%>
<%--            <label for="user_login">Краевед: </label>--%>
<%--            <input id="user_login" type="text" readonly name="user_login" value="${edit.user.login}">--%>
<%--            <button type="submit" name="command" value="delete_edit">Отклонить</button>--%>
<%--            <button type="submit" name="command" value="save_edit">Принять</button>--%>
<%--        </form>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>
</body>
</html>
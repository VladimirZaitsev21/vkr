<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<%--<p>Внесенные вами правки</p>--%>
<%--<c:forEach var="edit" items="${edits}">--%>
<%--    <form action="frontController" method="post">--%>
<%--        <input type="hidden" name="command" value="delete_edit">--%>
<%--        <label for="id">id: </label>--%>
<%--        <input id="id" type="text" name="edit_id" value="${edit.id}">--%>
<%--        <label for="sight_id">OSM id достопримечательности: </label>--%>
<%--        <input id="sight_id" type="text" value="${edit.sight.osmId}">--%>
<%--        <label for="osm_name">Название: </label>--%>
<%--        <input id="osm_name" type="text" name="name" value="${edit.sightName}">--%>
<%--        <label for="description">Описание: </label>--%>
<%--        <input id="description" type="text" name="description" value="${edit.sightDescription}">--%>
<%--        <input id="submit" type="submit" value="Удалить">--%>
<%--    </form>--%>
<%--    <br>--%>
<%--</c:forEach>--%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-uppercase mb-0">Внесенные вами правки</h5>
                </div>
                <div class="table-responsive">
                    <%--                    <form id="user_form" method="post" action="frontController"></form>--%>
                    <table class="table no-wrap user-table mb-0">
                        <thead>
                        <tr>
                            <th scope="col" class="border-0 text-uppercase font-medium">OSM id достопримечательности</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Название</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Описание</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Действие</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="edit" items="${edits}">
                            <tr>
                                <td>
                                    <form id="${edit.id}" method="post" action="frontController">
                                        <input id="id" readonly type="hidden" name="edit_id" value="${edit.id}" form="${edit.id}"/>
                                        <input type="hidden" name="command" value="delete_edit" form="${edit.id}"/>
                                        <input id="sight_id" readonly class="form-control input" type="text" value="${edit.sight.osmId}" form="${edit.id}"/>
                                    </form>
                                </td>
                                <td>
                                    <input id="osm_name" readonly class="form-control input" type="text" name="name" value="${edit.sightName}" form="${edit.id}"/>
                                </td>
                                <td>
                                    <input id="description" readonly class="form-control input" type="text" name="description" value="${edit.sightDescription}" form="${edit.id}"/>
                                </td>
                                <td>
                                    <input type="submit" class="btn btn-danger btn-block mb-4" name="submit" value="Удалить" form="${edit.id}"/>
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


</body>
</html>
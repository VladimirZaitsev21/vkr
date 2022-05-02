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
<%--<p>Доступные к редактированию достопримечательности</p>--%>
<%--<c:forEach var="sight" items="${sights}">--%>
<%--    <form action="frontController" method="post">--%>
<%--        <input type="hidden" name="command" value="edit_sight">--%>
<%--        <label for="osm_id">OSM id: </label>--%>
<%--        <input id="osm_id" type="text" name="osmId" value="${sight.osmId}">--%>
<%--        <label for="osm_name">Название: </label>--%>
<%--        <input id="osm_name" type="text" name="name" value="${sight.name}">--%>
<%--        <label for="description">Описание: </label>--%>
<%--        <input id="description" type="text" name="description" value="${sight.description}">--%>
<%--        <input id="submit" type="submit" value="Сохранить">--%>
<%--    </form>--%>
<%--    <br>--%>
<%--</c:forEach>--%>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-uppercase mb-0">Доступные к редактированию достопримечательности</h5>
                </div>
                <div class="table-responsive">
                    <table class="table no-wrap user-table mb-0">
                        <thead>
                        <tr>
                            <th scope="col" class="border-0 text-uppercase font-medium">OSM id</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Название</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Описание</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Действие</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="sight" items="${sights}">
                                <tr>
                                    <td>
                                        <form id="${sight.osmId}" method="post" action="frontController">
                                            <input id="osm_id" readonly type="text" name="osmId" value="${sight.osmId}" form="${sight.osmId}"/>
                                        </form>
                                    </td>
                                    <td>
                                        <input type="hidden" name="command" value="edit_sight" form="${sight.osmId}"/>
                                        <input id="osm_name" type="text" class="form-control input" name="name" value="${sight.name}" form="${sight.osmId}"/>
                                    </td>
                                    <td>
                                        <input id="description" type="text" class="form-control input" name="description" value="${sight.description}" form="${sight.osmId}"/>
                                    </td>
                                    <td>
                                        <input name="submit_input" type="submit" class="btn btn-success btn-block mb-4" value="Сохранить" form="${sight.osmId}"/>
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
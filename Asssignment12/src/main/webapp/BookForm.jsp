<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<center>
    <h1>Books Management</h1>
    <h2>
        <a href="/cityNew">Add New City</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Cities</a>

    </h2>
</center>
<div align="center">
    <c:if test="${city != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${city == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${city != null}">
                            Edit Book
                        </c:if>
                        <c:if test="${city == null}">
                            Add New Book
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${city != null}">
                    <input type="hidden" name="id" value="<c:out value='${city.cityId}' />" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="cityName" size="45"
                               value="<c:out value='${city.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Latitude </th>
                    <td>
                        <input type="text" name="latitude" size="45"
                               value="<c:out value='${city.latitude}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Longitude </th>
                    <td>
                        <input type="text" name="longitude" size="5"
                               value="<c:out value='${city.longitude}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
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
        <a href="/flightNew">Add New Flight</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/flight">List All Flights</a>

    </h2>
</center>
<div align="center">
    <c:if test="${flight != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${flight == null}">
        <form action="insertFlight" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${flight != null}">
                            Edit Book
                        </c:if>
                        <c:if test="${flight == null}">
                            Add New Book
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${flight != null}">
                    <input type="hidden" name="id" value="<c:out value='${flight.flightId}' />" />
                </c:if>
                <tr>
                    <th>AirplaneType: </th>
                    <td>
                        <input type="text" name="airplaneType" size="45"
                               value="<c:out value='${flight.airplaneType}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>DepartureDate: </th>
                    <td>
                        <input type="text" name="departureDate" size="45"
                               value="<c:out value='${flight.departureDate}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>DepartureCity: </th>
                    <td>
                        <input type="text" name="departureCity" size="5"
                               value="<c:out value='${flight.departureCity.cityId}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>ArrivalDate: </th>
                    <td>
                        <input type="text" name="arrivalDate" size="45"
                               value="<c:out value='${flight.arrivalDate}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>ArrivalCity: </th>
                    <td>
                        <input type="text" name="arrivalCity" size="5"
                               value="<c:out value='${flight.arrivalCity.cityId}' />"
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
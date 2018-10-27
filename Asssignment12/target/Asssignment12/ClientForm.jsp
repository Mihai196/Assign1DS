<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Client Page</title>
    <style>
        td {
            width: 200px;
        }

        th {
            text-align: left;
        }

        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<center>
    <h1>Client Page</h1>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Flights</h2></caption>
        <tr>
            <th>FlightId</th>
            <th>AirplaneType</th>
            <th>DepartureTime</th>
            <th>DepartureCity</th>
            <th>ArrivalTime</th>
            <th>ArrivalCity</th>
        </tr>
        <c:forEach var="flight" items="${flights}">
            <tr>
                <td><c:out value="${flight.flightId}" /></td>
                <td><c:out value="${flight.airplaneType}" /></td>
                <td><c:out value="${flight.departureDate}" /></td>
                <td><c:out value="${flight.departureCity.name}" /></td>
                <td><c:out value="${flight.arrivalDate}" /></td>
                <td><c:out value="${flight.arrivalCity.name}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

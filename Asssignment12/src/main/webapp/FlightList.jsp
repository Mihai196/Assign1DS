<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Page</title>
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
    <h1>Admin Page</h1>
    <h2>
        <a href="/flightNew">Add New Flight</a>
        &nbsp;&nbsp;&nbsp;

    </h2>
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
            <th>Actions</th>
        </tr>
        <c:forEach var="flight" items="${flights}">
            <tr>
                <td><c:out value="${flight.flightId}" /></td>
                <td><c:out value="${flight.airplaneType}" /></td>
                <td><c:out value="${flight.departureDate}" /></td>
                <td><c:out value="${flight.departureCity.name}" /></td>
                <td><c:out value="${flight.arrivalDate}" /></td>
                <td><c:out value="${flight.arrivalCity.name}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${flight.flightId}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/deleteFlight?flightId=<c:out value='${flight.flightId}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table border="1" cellpadding="5">
        <caption><h2>List of Cities</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Latitude</th>
            <th>Longitude</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="city" items="${cities}">
            <tr>
                <td><c:out value="${city.cityId}" /></td>
                <td><c:out value="${city.name}" /></td>
                <td><c:out value="${city.latitude}" /></td>
                <td><c:out value="${city.longitude}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${city.cityId}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${city.cityId}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
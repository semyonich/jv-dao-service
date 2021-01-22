<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All cars</title>
</head>
<body>
<h1>List of all cars in DB:</h1><br>
<table border="1">
    <tr>
        <th>Car Id</th>
        <th>Car Model</th>
        <th>Manufacturer</th>
        <th>Country</th>
        <th>Drivers</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <c:out value="${car.getId()}"/>
            </td>
            <td>
                <c:out value="${car.getModel()}"/>
            </td>
            <td>
                <c:out value="${car.getManufacturer().getName()}"/>
            </td>
            <td>
                <c:out value="${car.getManufacturer().getCountry()}"/>
            </td>
            <td>
                <c:out value="${car.getDrivers()}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

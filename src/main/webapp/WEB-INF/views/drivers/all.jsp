<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All drivers</title>
</head>
<body>
<h1>List of all drivers in DB:</h1><br>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Driver Name</th>
        <th>License Number</th>
        <th>Login</th>
    </tr>
    <c:forEach var="driver" items="${drivers}">
        <tr>
            <td>
                <c:out value="${driver.getId()}"/>
            </td>
            <td>
                <c:out value="${driver.getName()}"/>
            </td>
            <td>
                <c:out value="${driver.getLicenseNumber()}"/>
            </td>
            <td>
                <c:out value="${driver.getLogin()}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All manufacturers</title>
</head>
<body>
<h1>List of all manufacturers in DB:</h1><br>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Manufacturer Name</th>
        <th>Country</th>
    </tr>
    <c:forEach var="manufacturer" items="${manufacturers}">
        <tr>
            <td>
                <c:out value="${manufacturer.getId()}"/>
            </td>
            <td>
                <c:out value="${manufacturer.getName()}"/>
            </td>
            <td>
                <c:out value="${manufacturer.getCountry()}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

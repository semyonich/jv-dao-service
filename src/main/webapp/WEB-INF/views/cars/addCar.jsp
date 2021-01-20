<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Car to DB</title>
</head>
<body>
<h2>Please enter Car model and license number</h2><br>
<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Car model:<input type="text" name="carModel"><br><br>
    Car manufacturer ID:<input type="number" name="manufacturerId"><br><br>
    <button type="submit">Add Car</button>
</form>
</body>
</html>

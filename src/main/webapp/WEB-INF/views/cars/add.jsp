<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Car to DB</title>
</head>
<body>
<h2>Please enter Car model and Manufacturer ID</h2><br>
<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Car model:<input type="text" name="car_model" minlength="1" required><br><br>
    Car manufacturer ID:<input type="number" name="manufacturer_id" minlength="1" required><br><br>
    <button type="submit">Add Car</button>
</form>
</body>
</html>

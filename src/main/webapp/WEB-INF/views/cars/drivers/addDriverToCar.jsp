<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver to Car in DB</title>
</head>
<body>
<h2>Please enter Car ID and Driver ID</h2><br>
<form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
    Car ID:<input type="number" name="carId" minlength="1" required><br><br>
    Driver ID:<input type="number" name="driverId" minlength="1" required><br><br>
    <button type="submit">Add Driver to Car</button>
</form>
</body>
</html>

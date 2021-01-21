<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Driver to DB</title>
</head>
<body>
<h2>Please enter driver name and license number</h2><br>
<form method="post" action="${pageContext.request.contextPath}/drivers/add">
    Driver name:<input type="text" name="driver_name" minlength="1" required><br><br>
    Driver license number:<input type="text" name="license_number" minlength="1" required><br><br>
    <button type="submit">Add Driver</button>
</form>
</body>
</html>

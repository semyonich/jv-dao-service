<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver registration page</title>
</head>
<body>
<h2>Please enter driver name, license number, login and password:</h2><br>
<h4 style="color: red">${errorMessage}</h4><br>
<form method="post" action="${pageContext.request.contextPath}/drivers/add">
    Driver name:<input type="text" name="driver_name" minlength="1" required><br><br>
    Driver license number:<input type="text" name="license_number" minlength="1" required><br><br>
    Driver login:<input type="text" name="driver_login" minlength="1" required><br><br>
    Password:<input type="password" name="driver_password" minlength="1" required><br><br>
    Repeat password:<input type="password" name="repeat_password" minlength="1" required><br><br>
    <button type="submit">Add Driver</button><br>
    <a href="/drivers/login">Driver Login</a><br>
</form>
</body>
</html>

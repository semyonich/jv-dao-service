<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1>PLease, enter your login and password!</h1>
<h4 style="color: red">${errorMessage}</h4>
<form method="post" action="${pageContext.request.contextPath}/drivers/login">
    Driver login:<input type="text" name="driver_login" minlength="1" required><br><br>
    Password:<input type="password" name="driver_password" minlength="1" required><br><br>
    <button type="submit">Login</button><br>
    <a href="/drivers/add">Driver Registration</a><br>
</form>
</body>
</html>

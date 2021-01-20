<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Manufacturer to DB</title>
</head>
<body>
<h2>Please enter Manufacturer Name and Country</h2><br>
<form method="post" action="${pageContext.request.contextPath}/manufacturers/add">
    Manufacturer name:<input type="text" name="manufacturerName" minlength="1" required><br><br>
    Manufacturer country:<input type="text" name="country" minlength="1" required><br><br>
    <button type="submit">Add Manufacturer</button>
</form>
</body>
</html>

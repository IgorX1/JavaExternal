<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 25.11.2018
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action="LoginServlet" method="post">
    <label for="login">Login</label>
    <input type="text" name="login" id="login"/>
    <br/>
    <label for="password">Password</label>
    <input type="password" name="password" id="password"/>
    <br/>
    <input type="submit" value="Log in"/>
</form>
</body>
</html>
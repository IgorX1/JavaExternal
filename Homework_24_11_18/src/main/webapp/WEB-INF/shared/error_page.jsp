<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 25.11.2018
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<% response.setStatus(404); %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1 style="color:red;">Error occured while processing your request</h1>
<p><a href="index.jsp">HOME PAGE</a></p>
</body>
</html>

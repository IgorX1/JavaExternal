<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 26.11.2018
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>Printers</title>
</head>
<body>
<table class="menu">
    <tr>
        <td><a href="#">Home</a></td>
        <td><a href="#">Laptops</a></td>
        <td><a href="#">Printers</a></td>
        <td><a href="#">PCs</a></td>
        <td><a href="/logout">Log out</a></td>
    </tr>
</table>
<h1>All printer list -- Home Page (ADMIN)</h1>
<c:forEach var="pc" items="${requestScope.printers}">
    <ul>
        <li>Model: ${pc.model}</li>
        <li>Color: ${pc.color}</li>
        <li>Type: ${pc.type}</li>
        <li>Price: ${pc.price}</li>
    </ul><br/>
</c:forEach>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 26.11.2018
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 26.11.2018
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>Laptops</title>
</head>
<body>
<table class="menu">
    <tr>
        <td><a href="${pageContext.request.contextPath}/ProductServlet">Home</a></td>
        <td><a href="${pageContext.request.contextPath}/LaptopServlet">Laptops</a></td>
        <td><a href="${pageContext.request.contextPath}/PrinterServlet">Printers</a></td>
        <td><a href="${pageContext.request.contextPath}/PcServlet">PCs</a></td>
        <td><a href="${pageContext.request.contextPath}/logout">Log out</a></td>
    </tr>
</table>
<h1>All laptop list -- Home Page (USER)</h1>
<c:forEach var="pc" items="${requestScope.laptops}">
    <ul>
        <li>Model: ${pc.model}</li>
        <li>Speed: ${pc.speed}</li>
        <li>Hd: ${pc.hd}</li>
        <li>Price: ${pc.price}</li>
        <li>Screen: ${pc.screen}</li>
    </ul><br/>
</c:forEach>
</body>

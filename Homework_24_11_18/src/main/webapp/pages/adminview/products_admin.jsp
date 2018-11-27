<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 25.11.2018
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>Products</title>
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
<h1>All product list -- Home Page</h1>
<h3>Hello ${sessionScope.login} (${sessionScope.role})</h3>
<c:forEach var="product" items="${requestScope.products}">
    <ul>
        <li>Model: ${product.model}</li>
        <li>Maker: ${product.maker}</li>
        <li>Type: ${product.type}</li>
    </ul><br/>
</c:forEach>
</body>
</html>


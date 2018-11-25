<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 25.11.2018
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h1>Product list</h1><br />
    <c:forEach var="product" items="${requestScope.products}">
        <ul>
            <li>Model: ${product.model}</li>
            <li>Maker: ${product.maker}</li>
            <li>Type: ${product.type}</li>
        </ul><br/>
    </c:forEach>
</body>
</html>

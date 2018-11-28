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
        <td><a href="${pageContext.request.contextPath}/ProductServlet">Home</a></td>
        <td><a href="${pageContext.request.contextPath}/LaptopServlet">Laptops</a></td>
        <td><a href="${pageContext.request.contextPath}/PrinterServlet">Printers</a></td>
        <td><a href="${pageContext.request.contextPath}/PcServlet">PCs</a></td>
        <td><a href="${pageContext.request.contextPath}/logout">Log out</a></td>
    </tr>
</table>
<h1>All printer list --(ADMIN)</h1>
<div>
    <h4><a>Add new item</a></h4>
    <form method="post" action="${pageContext.request.contextPath}/PrinterServlet">
        <input type="hidden" name="command" value="add" />
        <input type="text" name="model" id="model" required/>
        <label for="model">Model</label>
        <br/>
        <input type="text" name="color" id="color" required/>
        <label for="color">Color</label>
        <br/>
        <input type="text" name="type" id="type" required/>
        <label for="type">Type</label>
        <br/>
        <input type="text" name="price" id="price" required/>
        <label for="price">Price</label>
        <br/><br/>
        <input type="submit" value="Add item"/>
    </form>
    ${requestScope.error}
</div>
<c:forEach var="pc" items="${requestScope.printers}">
    <ul>
        <li>Model: ${pc.model}</li>
        <li>Color: ${pc.color}</li>
        <li>Type: ${pc.type}</li>
        <li>Price: ${pc.price}</li>
        <form action="${pageContext.request.contextPath}/PrinterServlet" method="post">
            <input type="hidden" name="command" value="delete" />
            <input type="hidden" name="id" value="${pc.model}" />
            <input type="submit" value="Delete"/>
        </form>
    </ul><br/>
</c:forEach>
</body>
</html>
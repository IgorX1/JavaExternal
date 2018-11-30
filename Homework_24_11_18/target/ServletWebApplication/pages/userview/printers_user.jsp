<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 26.11.2018
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: kois
  Date: 26.11.2018
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>Printers</title>
</head>
<body>
<table class="menu">
    <tr>
        <td><a href="${pageContext.request.contextPath}/ProductServlet"><fmt:message key="msg.menu.home"/> </a></td>
        <td><a href="${pageContext.request.contextPath}/LaptopServlet"><fmt:message key="msg.menu.laptops"/> </a></td>
        <td><a href="${pageContext.request.contextPath}/PrinterServlet"><fmt:message key="msg.menu.printers"/> </a></td>
        <td><a href="${pageContext.request.contextPath}/PcServlet"><fmt:message key="msg.menu.pcs"/> </a></td>
        <td><a href="${pageContext.request.contextPath}/logout"><fmt:message key="msg.menu.logout"/> </a></td>
    </tr>
</table>
<ul style="list-style: none;">
    <li style="display: inline-block;"><a href="?sessionLocale=ukr" title="UA"><img width="30px;" src="../../images/ukraine.jpg"/></a></li>
    <li style="display: inline-block;"><a href="?sessionLocale=en" title="ENG"><img width="30px;" src="../../images/england.png"/></a></li>
</ul>
<h1>All printer list -- Home Page (USER)</h1>
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
<%--
  Created by IntelliJ IDEA.
  User: Icarus
  Date: 7/24/2021
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<body>
<h1>${msg}</h1>
<c:out value="${msg}"/>
<c:forEach items="${paymentMethodList}" var="pm">
    <p>ID: ${pm.id}</p>
    <p>Account: ${pm.account}</p>
    <p>Mobile: ${pm.mobile}</p>
    <p>Issue Date: ${pm.issueDate}</p>
    <p>Expire Date: ${pm.expireDate}</p>

    <hr>
</c:forEach>

</body>
</html>

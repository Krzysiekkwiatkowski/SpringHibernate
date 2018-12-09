<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 09.12.18
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty violations}">
<c:forEach items="${violations}" var="violation">
    ${violation.propertyPath} : ${violation.message}</br>
</c:forEach>
</c:if>
<c:if test="${empty violations}">
    Brak błędów.
</c:if>
</body>
</html>

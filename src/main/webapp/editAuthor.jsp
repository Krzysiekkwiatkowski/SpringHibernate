<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 06.12.18
  Time: 06:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="author">
    <form:hidden path="id" />
    Firstname: <form:input path="firstName" /></br>
    Lastname: <form:input path="lastName" /></br>
    <input type="submit" value="Edit author">
</form:form>
</body>
</html>

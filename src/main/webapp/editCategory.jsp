<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 06.12.18
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="category">
    <form:hidden path="id" />
    Name: <form:input path="name"/><form:errors path="name" cssClass="error" /></br>
    Description: <form:input path="description" /><form:errors path="description" cssClass="error" /></br>
    <input type="submit" value="Edit category">
</form:form>
</body>
</html>

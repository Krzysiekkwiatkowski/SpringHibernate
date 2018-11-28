<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 28.11.18
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="publisher">
    <form:hidden path="id"/>
    Name: <form:input path="name" />
    <input type="submit" value="Edit publisher">
</form:form>
</body>
</html>
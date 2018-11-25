<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 24.11.18
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="person">
    Login: <form:input path="login" /></br>
    Password: <form:input path="password" /></br>
    Email: <form:input path="email" /></br>
    <input type="submit" value="Add person">
</form:form>
</body>
</html>

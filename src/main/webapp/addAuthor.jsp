<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 05.12.18
  Time: 22:50
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
    Firstname: <form:input path="firstName" /><form:errors path="firstName" cssClass="error" /></br>
    Lastname: <form:input path="lastName" /><form:errors path="lastName" cssClass="error" /></br>
    Birth: <form:input path="yearOfBirth" /><form:errors path="yearOfBirth" cssClass="error" /></br>
    Pesel: <form:input path="pesel" type="number"/><form:errors path="pesel" cssClass="error" /></br>
    Email: <form:input path="email" /><form:errors path="email" cssClass="error" /></br>
    <input type="submit" value="Add author">
</form:form>
</body>
</html>
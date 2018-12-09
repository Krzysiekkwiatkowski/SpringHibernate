<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 06.12.18
  Time: 07:06
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
    <form:hidden path="id" />
    Name: <form:input path="name" /><form:errors path="name" cssClass="error" /></br>
    Nip: <form:input path="nip" type="number"/><form:errors path="nip" cssClass="error" /></br>
    Regon: <form:input path="regon" type="number"/><form:errors path="regon" cssClass="error" /></br>
    <input type="submit" value="Edit publisher">
</form:form>
</body>
</html>

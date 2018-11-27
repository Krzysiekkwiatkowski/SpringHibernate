<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 26.11.18
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="book">
    Title: <form:input path="title" /></br>
    Authors: <form:select path="authors" items="${authors}" itemLabel="firstName" itemValue="id"/></br>
    Rating: <form:input path="rating" /></br>
    <input type="submit" value="Add book">
</form:form>
</body>
</html>

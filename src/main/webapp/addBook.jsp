<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 04.12.18
  Time: 22:46
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
    Authors: <form:select path="authors" items="${authors}" itemLabel="lastName" itemValue="id" multiple="true"/></br>
    Rating: <form:input path="rating" type="number" min="0.0" step="0.1" max="10.0" /></br>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/></br>
    Description: <form:input path="description" /></br>
    <input type="submit" value="Add book">
</form:form>
</body>
</html>

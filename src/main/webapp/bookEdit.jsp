<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 28.11.18
  Time: 18:31
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
    <form:hidden path="id" />
    Title: <form:input path="title" /></br>
    Authors: <form:select path="authors" items="${authors}"/></br>
    Rating: <form:input type="number" step="0.1" min="0.1" path="rating" /></br>
    Publisher: <form:select path="publisher" items="${publishers}"/></br>
    Description: <form:input path="description" /></br>
    <input type="submit" value="Edit book">
</form:form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 06.12.18
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="article">
    Title: <form:input path="title" /></br>
    Creator: <form:select path="creator.id" items="${creators}" itemLabel="lastName" itemValue="id" /></br>
    Categories: <form:select path="categories" items="${categoryList}" itemLabel="name" itemValue="id" /></br>
    Content: <form:input path="content" /></br>
    <input type="submit" value="Add article">
</form:form>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 11.12.18
  Time: 12:19
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
    Title: <form:input path="title" /><form:errors path="title" cssClass="error" /></br>
    Creator: <form:select path="creator.id" items="${creators}" itemLabel="lastName" itemValue="id" /></br>
    Categories: <form:select path="categories" items="${categoryList}" itemLabel="name" itemValue="id" /></br>
    Content: <form:input path="content" /><form:errors path="content" cssClass="error" /></br>
    Draft: <form:checkbox path="draft" /><form:errors path="draft" cssClass="error" /></br>
    <input type="submit" value="Add draft">
</form:form>
</body>
</html>

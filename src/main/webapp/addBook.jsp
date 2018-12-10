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
    Title: <form:input path="title" /><form:errors path="title" cssClass="error" /></br>
    Authors:<form:select itemValue="id" itemLabel="lastName" path="authors" items="${allAuthors}" /><form:errors path="authors" cssClass="error" /></br>
    Rating: <form:input path="rating" type="number" /><form:errors path="rating" cssClass="error" /></br>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/><form:errors path="publisher" cssClass="error" /></br>
    Description: <form:input path="description" /><form:errors path="description" cssClass="error" /></br>
    Pages: <form:input path="pages" type="number"/><form:errors path="pages" cssClass="error" /></br>
    <button type="submit">Add book</button>
</form:form>
</body>
</html>

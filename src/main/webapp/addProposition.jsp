<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 10.12.18
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
<form:form method="post" modelAttribute="book">
    Title: <form:input path="title" /><form:errors path="title" cssClass="error" /></br>
    Authors:<form:select itemValue="id" itemLabel="lastName" path="authors" items="${allAuthors}" /></br>
    Rating: <form:input path="rating" type="number" /></br>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name" itemValue="id"/></br>
    Description: <form:input path="description" /><form:errors path="description" cssClass="error" /></br>
    Pages: <form:input path="pages" type="number" /></br>
    Proposition: <form:checkbox path="proposition" /><form:errors path="proposition" cssClass="error" /></br>
    <button type="submit">Add proposition</button>
</form:form>
</body>
</html>

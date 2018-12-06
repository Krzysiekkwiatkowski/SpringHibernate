<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 06.12.18
  Time: 06:56
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
    Name: <form:input path="name" /></br>
    <input type="submit" value="Add publisher">
</form:form>
</body>
</html>

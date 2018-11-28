<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 28.11.18
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input:hidden path="id"/>
    <a href="http://localhost:8080/book/delete?id=${id}&confirm=yes"> Yes </a> | <a href="http://localhost:8080/book/delete?id=${id}&confirm=no"> No </a>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 08.12.18
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="get" modelAttribute="book">
    Jesteś pewna/pewny:</br>
    <form:hidden path="id" />
    <form:button value="Tak" name="confirm"> Tak </form:button>
    <form:button value="Nie" name="confirm"> Nie </form:button>
</form:form>
</body>
</html>

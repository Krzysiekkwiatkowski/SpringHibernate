<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 03.12.18
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Kategorie:</p>
<c:forEach items="${categories}" var="category">
    <a href="http://localhost:8080/homework/category/${category.id}"> ${category.name} </a>
</c:forEach>
<p>Artykuły:</p>
<c:forEach items="${articles}" var="article">
    <p> ${article.title} ${article.created} ${article.content} </p>
</c:forEach>
</body>
</html>

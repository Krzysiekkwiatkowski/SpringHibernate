<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 04.12.18
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="personDTO">
    Login: <form:input path="login" /></br>
    Password: <form:input path="password" /></br>
    Email: <form:input path="email" /></br>
    Firstname: <form:input path="firstName" /></br>
    Lastname: <form:input path="lastName" /></br>
    Male: <form:radiobutton path="gender" value="M"/>
    Female: <form:radiobutton path="gender" value="F"/></br>
    Country: <form:select path="country" items="${countries}" /></br>
    Notes: <form:textarea path="notes" rows="3" cols="20" /></br>
    MailingList: <form:checkbox path="mailingList" /></br>
    Programming skills: <form:select path="programmingSkills" items="${programmingSkills}" multiple="true" /></br>
    Hobbies: <form:checkboxes path="hobbies" items="${hobbies}" /></br>
    <input type="submit" value="Add">
</form:form>
</body>
</html>

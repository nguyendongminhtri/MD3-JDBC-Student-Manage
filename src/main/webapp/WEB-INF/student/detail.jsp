<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/28/2022
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test='${requestScope["message"]!=null}'>
    <span style="color: blue">${requestScope["message"]}</span>
</c:if>
<form method="post">
    <lable>ID</lable>
    <br>
    <input disabled value="${requestScope["student"].getId()}"><br>
    <label >NAME</label><br>
    <input value="${requestScope["student"].getName()}" ><br>
    <label >AGE</label><br>
    <input value="${requestScope["student"].getAge()}" ><br>
</form>
<a href="/">Back Menu</a>
</body>
</html>

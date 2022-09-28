<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "------------STUDENT MANAGE--------------" %>
</h1>
<br/>
<a href="students?action=create">Create Student</a>
<form method="post">
    <input type="text" name="search">
<button type="submit">Search</button>
</form>
<table border="1" style="width: 100%">
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>AGE</th>
        <th>DELETE</th>
        <th>EDIT</th>
    </tr>
    <c:forEach var="st" items='${requestScope["listStudent"]}'>
        <tr>
            <td><a href="students?action=detail&id=${st.id}">${st.id}</a></td>
            <td>${st.name}</td>
            <td>${st.age}</td>
            <td>
                <a href="students?action=edit&id=${st.id}">Edit</a>
            </td>
            <td><a href="students?action=delete&id=${st.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
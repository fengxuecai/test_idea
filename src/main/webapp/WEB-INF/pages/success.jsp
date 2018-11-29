<%--
  Created by IntelliJ IDEA.
  User: 冯
  Date: 2018/11/28
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生表</title>
</head>
<body>

<table border="1" bgcolor="aliceblue" align="center" width="50%">
    <caption>
        <h1>学员信息</h1></caption>

    <tr bgcolor="aqua">
        <th>id</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>住址</th>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr >
            <th>${user.id}</th>
            <th>${user.name}</th>
            <th>${user.age}</th>
            <th>${user.address}</th>
        </tr>
    </c:forEach>
</table>


</body>
</html>

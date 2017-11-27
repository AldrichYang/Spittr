<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/11/24
  Time: 下午3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittr</title>
</head>
<body>

<H1>Register</H1>
<form method="post" ">
    First Name: <input type="text" name="firstName"/><br>
    Last Name: <input type="text" name="lastName"/><br>
    Username: <input type="text" name="username"/><br>
    Password: <input type="password" name="password"/><br>
    <input type="submit" value="register">
    <%--这里的<form>标签中并没有设置action属性。在这种情况下,当表单提交时, 它会提交到与展现时相同的URL路径上。也就是说,它会提交到“/spitter/register”上--%>
</form>
</body>
</html>

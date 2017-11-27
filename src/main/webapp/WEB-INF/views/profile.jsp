<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/11/24
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Your Profile</h1>
<c:out value="${spitter.username}"/><br>
<c:out value="${spitter.firstName}"/>  --
<c:out value="${spitter.lastName}"/><br>
</body>
</html>

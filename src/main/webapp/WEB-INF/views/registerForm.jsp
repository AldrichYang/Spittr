<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/11/24
  Time: 下午3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Spittr</title>
</head>
<body>

<H1>Register</H1>
<%--<form method="post">--%>
    <%--First Name: <input type="text" name="firstName"/><br>--%>
    <%--Last Name: <input type="text" name="lastName"/><br>--%>
    <%--Username: <input type="text" name="username"/><br>--%>
    <%--Password: <input type="password" name="password"/><br>--%>
    <%--<input type="submit" value="register">--%>
    <%--&lt;%&ndash;这里的<form>标签中并没有设置action属性。在这种情况下,当表单提交时, 它会提交到与展现时相同的URL路径上。也就是说,它会提交到“/spitter/register”上&ndash;%&gt;--%>
<%--</form>--%>
<%--使用Spring提供的标签库--%>
<%--通过commandName属性构建针对某个模型对象的上下文信息--%>
<sf:form method="post" commandName="spitter">
    <%--value属性值将会设置为模型对象中path属性所对应的值--%>
    First Name: <sf:input path="firstName"/>
    <%--它的path属性设置成了firstName,也就是指定了要 显示Spitter模型对象中哪个属性的错误。如果firstName属性没有错误的话,那 么<sf:errors>不会渲染任何内容。
    但如果有校验错误的话,那么它将会在一个HTML <span>标签中显示错误信息--%>
    <sf:errors path="firstName" cssClass="error"/><br>
    Last Name: <sf:input path="lastName"/><br>
    Email : <sf:input path="email"/><br>
    Username : <sf:input path="username"/><br>
    Password: <sf:password path="password"/><br>
    <input type="submit" value="Register">
</sf:form>
</body>
</html>

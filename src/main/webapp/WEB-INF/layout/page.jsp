<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/11/29
  Time: 下午3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@page session="false" %>
<html>
<head>
    <title>Spittr</title>
</head>
<body>

<div id="header">
    <%--插入头部--%>
    <t:insertAttribute name="header"/>
</div>
<div id="content">
    <%--插入主体内容--%>
    <t:insertAttribute name="body"/>
</div>
<div id="footer">
    <%--插入底部--%>
    <t:insertAttribute name="footer"/>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/5/10
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme( )
            + "://"
            + request.getServerName( )
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath",basePath);
%>


<!-- 写 base 标签，永远固定相对路径跳转的结果 -->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/jquery-1.7.2.js"></script>
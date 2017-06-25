<%@page import="practice.first.domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<%
/*
  获取session中的User对象
   * 如果为null，说明没有登录
     > 向request域中保存错误信息（您还没有登录，不要瞎遛达！）
     > 转发到login.jsp
   * 如果不为null，什么也不做！
*/
User user = (User)session.getAttribute("user");
if(user == null) {
	request.setAttribute("msg", "您还没有登录，不要瞎遛达！");
	request.getRequestDispatcher("/login.jsp").forward(request, response);
}
%>
<frameset rows="24%, *">
  <frame src="<c:url value='/jsp/top.jsp'/>" name="topFrame" />
  <frame src="<c:url value='/jsp/main.jsp' />" name="mainFrame" />
</frameset>
</html>

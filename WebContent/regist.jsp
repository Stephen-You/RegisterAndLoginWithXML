<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 初步校验注册信息 -->
	<script type="text/javascript">
		window.onload = function(){
			//获取表单
			var form = document.getElementById("form");
			//表单提交时间
			form.onsubmit = function(){
				//对表单进行校验
				//设置变量记录,校验结果,将结果传递给submit,已决定是否提交表单,true提交,false不提交
				var flag = true;
				//用户名校验
				if (!form.username.value) {
					var error = document.getElementById("usernameError");
					error.innerHTML = "用户名不能为空!";
					//校验结果为false
					flag = false;
				} else if(form.username.value.length<6||form.username.value.length>15){
					var error = document.getElementById("usernameError");
					error.innerHTML = "用户名长度保证在6到15位!";
					flag = false;
				}
				//密码校验
				if (!form.password.value) {
					var error = document.getElementById("passwordError");
					error.innerHTML = "密码不能为空!";
					//校验结果为false
					flag = false;
				} else if(form.password.value.length<6||form.password.value.length>15){
					var error = document.getElementById("passwordError");
					error.innerHTML = "密码长度保证在6到15位!";
					flag = false;
				}
				//重复密码校验
				if(form.password.value!=form.repassword.value){
					var error = document.getElementById("repasswordError");
					error.innerHTML = "重复密码要和密码一致!";
					flag = false;
				}
				var email = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
				if(!email.test(form.email.value)){
					var error = document.getElementById("emailError");
					error.innerHTML = "邮箱不符合规范!";
					flag = false;
				}
				return flag;
			}
		}
		
		function _change(){
			/*
			1. 获取img元素
			2. 修改它的src属性为/day11_5/VCServlet
			*/
			var img = document.getElementById("img1");
			img.src = "/RegisterAndLogin/VerifyCodeServlet?a=" + new Date().getTime();
		}
		
	</script>
  </head>
  
  <body>
<h1>注册页面</h1>
<%-- 显示错误信息 --%>
<p style="color: red; font-size: 10pt; font-weight: 900;">
${msg }
</p>

<%-- 还要显示回显数据 --%>
<form action="<c:url value='/RegistServlet' />" method="post" id="form">
用户名：　<input type="text" name="username" value="${user.username }"/>
		<span id="usernameError" style="color: red; font-size: 10pt; font-weight: 900;">${map.username }</span>
<br/>
密码：　　<input type="password" name="password" value="${user.password }"/>
		<span id="passwordError" style="color: red; font-size: 10pt; font-weight: 900;"></span>
<br/>
确认密码：<input type="password" name="repassword" value="${user.repassword }"/><br/>
<span id="repasswordError" style="color: red; font-size: 10pt; font-weight: 900;"></span>
邮箱：　　<input type="text" name="email" value="${user.email }"/><br/>
<span id="emailError" style="color: red; font-size: 10pt; font-weight: 900;"></span>
验证码：　<input type="text" name="verifyCode" size="3" value="${user.verifyCode }"/><br/>
       <img src="<c:url value='/VerifyCodeServlet' />" id="img1"/>　
       <a href="javascript:_change()" style="font-size: 10pt">换一张</a>
<span id="codeError" style="color: red; font-size: 10pt; font-weight: 900;">${map.verifyCode }</span>
<input type="submit" value="注册"/>
</form>
  </body>
</html>

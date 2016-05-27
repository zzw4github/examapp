<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>


<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/login.css" rel="stylesheet" type="text/css" />


</head>

<body class="body_login">
<div class="div_bgtop"></div>
<div class="body_login_down"></div>
<div class="body_login_top">清大新洋图书馆</div>

<div class="div_login">

	<div class="login_left"><img src="/image/login_tu1.jpg"></div>

	<div class="login_right">
		<div class="div1"><img src="/image/login_tb.gif"><div class="div1_top">在线考试</div><div class="xian1"></div></div>
        <form:form  commandName="user" action="user" method="post">
        <div class="div2">账号：<input name="tmh" type="text" /></div>
        <div class="div2">密码：<input name="pwd" type="password" /></div>
        <div class="div3"><input type="image" src="/image/login.png" border=0></div>
        </form:form>
	</div>

</div>
<div id="timer" style="color:red"></div>
</body>
</html>
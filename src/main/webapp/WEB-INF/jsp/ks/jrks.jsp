<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/ks.css" rel="stylesheet" type="text/css" />
</head>

<body class="ks_bg">
<div class="ks_top">清大新洋图书馆在线考试系统</div>

<div class="top_xx">考号：${testDTO.userDTO.tmh}&nbsp;&nbsp;姓名：${testDTO.userDTO.name.trim()}&nbsp;&nbsp;<a href="/user/logout">退出</a></div>
<div class="ks1">
	<div class="ksk_left"><img src="/image/kstu1.jpg"/></div>
    <div class="ksk_right">
    	<div class="cjks"><a href="/test/user/${testDTO.userDTO.id}"><img src="/image/cjks.gif"/></a></div>
        <div><img src="/image/ksmc1.gif" /> <b>考试名称：${testDTO.examDTO.name}</b></div>
        <div>  <c:forEach var="questionType" items="${testDTO.testDefineDTO.types}">
            ${questionType.type.name}${questionType.amount}道，每道${questionType.score}分
        </c:forEach></div>
        <div>考试限时：${testDTO.examDTO.time}分钟&nbsp;&nbsp;总分：100分</div>
    </div>
</div>




</body>
</html>

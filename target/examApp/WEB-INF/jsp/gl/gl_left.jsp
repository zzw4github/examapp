<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/gl.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
function changeBackColor(obj)
{
for(var i=1;i<5;i++)
{
document.getElementById("w"+i).className="gl_left1";
}
document.getElementById(obj).className="gl_left1c";
}
</script>
<body class="gl_left">

<div class="gl_left_top">后台管理</div>
<div class="gl_left1" id="w1" onclick="changeBackColor('w1')"><img src="/image/stgl.gif"/><a href="stgl"  target="mainFrame" >试题管理</a></div>
<div class="gl_left1"><img src="/image/ksgl.gif"/><a href="#">考生管理</a></div>
	<div class="gl_left1" id="w2" onclick="changeBackColor('w2')"><a href="/gl/ksgl" target="mainFrame" class="gl_left1_a">考生查询</a></div>
    <div class="gl_left1" id="w3" onclick="changeBackColor('w3')"><a href="/gl/cjgl" target="mainFrame" class="gl_left1_a">成绩管理</a></div>
<div class="gl_left1" id="w4" onclick="changeBackColor('w4')"><img src="/image/kssz.gif"/><a href="/gl/kssz" target="mainFrame">考试设置</a></div>
<div class="gl_left1"><img src="/image/tcxt.gif"/><a href="/user/logout" target="_parent">退出系统</a> </div>
</body>
</html>

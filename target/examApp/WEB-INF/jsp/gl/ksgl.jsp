<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考生管理</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/gl.css" rel="stylesheet" type="text/css" />

   <script src="/js/jquery-2.2.3.js"></script>
   <script src="/js/bootstrap.min.js"></script>
</head>
<script>
  function qdmiankao(){
   return confirm('确定要免考吗？');
  }
</script>
    
<body class="gl_right">
<div class="main_yinying"></div>
<div class="main_bt1">考生人数：2157人</div>
<div class="chaxun1">
<div class="chaxun1_left"><ul>
							<li><label>考&nbsp;&nbsp;生&nbsp;&nbsp;号：</label><input type="text" /></li>
                            <li><label>考生姓名：</label><input type="text" /></li>
                            <li><label>考生类型：</label><select>
                              							<option>大专</option>
                              							<option>本科</option>
                            						  </select>
                            </li>
                            <li><label>一级单位：</label><select>
                              							<option>2015界</option>
                              							<option>2016界</option>
                            						  </select>
                            </li>
                            <li><label>二级单位：</label><select>
                              							<option>1班</option>
                              							<option>2班</option>
                            						  </select>
                            </li><div class="chaxun1_right"><img src="../image/chazhao.gif"></div>
                            </ul>
</div>
</div>
<div class="hide_width"></div>
<div class="main">
	<table class="table1">
    <tr class="tablehead1"><td>考生号</td><td>姓名</td><td>类型</td><td>一级单位</td><td>二级单位</td><td>办证单位</td><td>状态</td><td>&nbsp;</td></tr>
    <tr class="tabletd1">
      <td nowrap>110108198705046581</td><td>王瑞</td><td>本科</td><td>2016界</td><td>1班</td><td>清大新洋图书馆</td><td><font color="#52c0a3">已通过</font></td><td class="a_anniu"><a class="miankao" onclick="qdmiankao();" >免考</a><a onclick="javascript:window.open('dzxx.html');">详情</a></td></tr>
    <tr class="tabletd1">
      <td nowrap>110108198705046581</td><td>王瑞</td><td>本科</td><td>2016界</td><td>1班</td><td>清大新洋图书馆</td><td><font color="#FF3366">未通过</font></td><td class="a_anniu"><a class="miankao" onclick="qdmiankao();">免考</a><a onclick="javascript:window.open('dzxx.html');">详情</a></td></tr>
    <tr class="tabletd1">
      <td nowrap>110108198705046581</td><td>王瑞</td><td>本科</td><td>2016界</td><td>1班</td><td>清大新洋图书馆</td><td>免考</td><td class="a_anniu"><a class="miankao" onclick="qdmiankao();">取消免考</a><a onclick="javascript:window.open('dzxx.html');">详情</a></td></tr>
    <tr class="tabletd1">
      <td nowrap>110108198705046581</td><td>王瑞</td><td>本科</td><td>2016界</td><td>1班</td><td>清大新洋图书馆</td><td>未考</td><td class="a_anniu"><a class="miankao" onclick="qdmiankao();">免考</a><a onclick="javascript:window.open('dzxx.html');">详情</a></td></tr>
  </table>
  
  <div align="center">
  <ul class="pagination">
  <li><a href="#">&laquo;</a></li>
  <li class="active"><a href="#">1</a></li>
  <li><a href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#">&raquo;</a></li>
  </ul>
</div>
</div>
</body>
</html>

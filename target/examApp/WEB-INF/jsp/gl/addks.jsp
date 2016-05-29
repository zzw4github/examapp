<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考试设置</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/gl.css" rel="stylesheet" type="text/css" />
</head>
<script l type="text/javascript">
window.onload = function() 
{ 
document.getElementById('txta1').onkeydown = function() 
{ 
if(this.value.length >= 100) 
event.returnValue = false; 
} 
} 
</script>
<body class="gl_right">
<div class="main_yinying"></div>
<div class="main_bt1">考试设置>>编辑考试</div>
<div class="main2">
	<div class="main_st">
		<div class="main_stt1">考试管理</div>
        <ul><li>考试名称：</li><input name="" type="text" class="input3" /></ul>
        <ul><li>考试限时：</li><input name="" type="text"/> 分钟</ul>
        <ul><li>考试说明：
        <br><font size="2">(限100字以内)</font></li><textarea onpropertychange="if(value.length>100) value=value.substr(0,100)" cols="60" name="txta" id="txta1" rows="3" class="st_input2"></textarea><br></ul>
        <ul><li>考试题形：</li><div>单选题 <select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select> 道&nbsp;&nbsp;
                                    <select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select>分</div>
             				  <div>多选题 <select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select> 道&nbsp;&nbsp;
                                    <select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select>分</div>
                                    
                              <div class="margin1">
                              判断题 <select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select> 道&nbsp;&nbsp;
                                    <select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select>分</div></ul>
                                    
                                    
        <ul><div>最大重考次数：</div>
        	<div class="margin1 left1"><input name="" type="checkbox" value="" /> 专科&nbsp;&nbsp;<select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select>次
            </div>
            <div class="margin1 left1"><input name="" type="checkbox" value="" /> 本科&nbsp;&nbsp;<select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select>次
            </div>
            <div class="margin1 left1"><input name="" type="checkbox" value="" /> 教师&nbsp;&nbsp;<select class="input4">
                              			<option>1</option>
                              			<option>2</option>
                            		</select>次
            </div>
            
        </ul>
        <ul><li>备&nbsp;&nbsp;注：</li><textarea name="" cols="" rows="3" class="st_input2"></textarea></ul>	 

        <div class="st_bc"><input type="image" src="/image/baocun.gif" border=0></div>

	</div>
</div>
</body>
</html>

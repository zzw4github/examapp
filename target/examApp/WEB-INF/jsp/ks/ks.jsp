<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考试页面</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/ks.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-2.2.3.js"></script>
    <script type="text/javascript" src="/js/jquery.countdown.js"></script>

</head>

<body class="ks_bg">

<div class="min-w1" >

<div class="ks_top">清大新洋图书馆在线考试系统</div>

<div class="ks_left">
	<div class="ks_left_top">
    	<div>账号：${testDTO.userDTO.tmh}&nbsp;&nbsp;姓名：${testDTO.userDTO.name.trim()}	</div>
        <div>考试名称：${testDTO.examDTO.name}</div>
        <div>考试说明：${testDTO.examDTO.desc}</div>
        <div>题型：
            <c:forEach var="questionType" items="${testDTO.testDefineDTO.types}">
                ${questionType.type.name}${questionType.amount}道，每道${questionType.score}分
            </c:forEach>
        </div>
        <div >考试限时：<span id="time">${testDTO.examDTO.time}</span>分钟</div>
        <div class="red" id="remainTime"></div>
    </div>
  <div class="ks_an"><input type="image" src="/image/jiaojuan.gif" border=0 id="submit"><input type="image" src="/image/tuichu.gif" border=0></div>
    <div class="ks_left_down">
    	<div class="xuanti">
            <c:forEach var="pageNo" begin="1" end="${testDTO.pageBean.rowsCount}" step="1">
                <c:if test="${pageNo == testDTO.pageBean.curPage}">
                    <a href="/test/user/${testDTO.userDTO.id}/pageNo/${pageNo}" class="a_green" id="q${testDTO.ids[pageNo-1]}">${pageNo}</a>
                </c:if>
                <c:if test="${pageNo != testDTO.pageBean.curPage}">
                    <a href="/test/user/${testDTO.userDTO.id}/pageNo/${pageNo}" class="a_red" id="q${testDTO.ids[pageNo-1]}">${pageNo}</a>
                </c:if>
            </c:forEach>

    	</div> 

    </div>
</div>

<div class="ks_right">
    <form id="form" method="POST">
    <c:forEach var="subject" items="${testDTO.pageBean.objects}">
	<div class="ksr_1">${subject.question.type.name}</div>
	<div class="ksr_2">

    <p id="content">${testDTO.pageBean.curPage}.${subject.question.content}</p>
        <c:forEach var="option" items="${subject.question.option.split(\" \")}" varStatus="status" >
            <c:if test="${subject.question.type.name.equals(\"多选题\")}">
                <p> <input type="checkbox" name="${subject.question.id}" value="${option.substring(0,1)}"  /> ${option}
            </c:if>
            <c:if test="${!subject.question.type.name.equals(\"多选题\")}">
                <p> <input type="radio" name="${subject.question.id}" value="${option.substring(0,1)}"  /> ${option}
            </c:if>
         </c:forEach>
    </div>
    </c:forEach>
    <div class="ks_an"><input type="image" src="/image/syt.gif" align="上一题" id="pre"><input type="image" src="/image/xyt.gif" alt="下一题" id="next"></div>
        <a href="/test/user/${testDTO.userDTO.id}/pageNo/${testDTO.pageBean.curPage-1}" style="display: none" id="preUrl"></a>
        <a href="/test/user/${testDTO.userDTO.id}/pageNo/${testDTO.pageBean.curPage+1}" style="display: none" id="nextUrl"></a>
    </form>
</div>

</div>
<script type="text/javascript" src="/js/ks.js"></script>
<script type="text/javascript">

    $(document).ready(

            function () {
                var mcountdown = function(beginTime, endTime, nowTime){
                    var timeDiv = $("#remainTime");
                    if(nowTime  > endTime){

                    }else {
                        var testTime= new Date(endTime);

                        timeDiv.countdown(testTime ,function(event){
                            var format = event.strftime('剩余时间：%M分 %S秒' );
                            timeDiv.html(format);
                        });
                    }
                };
                   $.get("/test/time/now" , {} , function (result) {
                        if(result && result['success']){
                            var startTime = ${testDTO.testPaperDTO.beginTime};
                            var endTime = ${testDTO.testPaperDTO.maxEndTime};
                            var nowTime = result['nowTime'];
                            mcountdown(startTime, endTime, nowTime);
                        }else{

                        }
                   })
                   $(".a_green , .a_white ,.a_red ,#pre ,#next").click(function(){
                       var url ="";
                       var questionId="";
                       var questionAnswer="";
                       var data;
                       var tMinute = $("#tMinute").text();
                       var tSecond = $("#tSecond").text();
                       if($(this).attr("class")==="a_green"||$(this).attr("class")==="a_red"){
                            url = $(this).attr("href");
                       }
                       if($(this).attr("id")==="pre"){
                           url = $("#preUrl").attr("href");
                           if(url.indexOf("/0")>-1){
                               url = url.replace("/0","/1");
                           }
                       } else if($(this).attr("id")==="next"){
                           url=$("#nextUrl").attr("href");
                       }
                       if($("input[type=radio]").length!==0){
                           questionId =$("input[type=radio]:checked").attr("name");
                           questionAnswer = $("input[type=radio]:checked").val();
                       }if($("input[type=checkbox]").length!==0){
                           questionId =$("input[type=checkbox]:checked").attr("name");
                           questionAnswer = $("input[type=checkbox]:checked").val();
                       }
                       data ={questionId:questionId,questionAnswer:questionAnswer,tMinute:tMinute,tSecond:tSecond};
//显示问题页面
                        $.post(url,data,function(testDTO){
                            var question = testDTO.pageBean.objects[0].question;
                            $(".ksr_1").html(question.type.name);
                            $("#content").html( testDTO.pageBean.curPage+"."+question.content);
                            $(".ksr_2").remove("radio");
                            $(".ksr_2").remove("checkbox");
                            $(":radio,:checkbox").parent("p").remove();
                            var options = "";
                            if (question.type.name !== "多选题") {

                                $.each(question.option.split(" "), function (i, item) {
                                    options+="<p><input type='radio' name='"+question.id+"' value='"+item.substr(0,1)+"' />"+item+"</p>";
                                });
                            }
                            if (question.type.name === "多选题") {
                                $.each(question.option.split(" "), function (i, item) {
                                    options+= "<p><input type='checkbox' name='"+question.id+"' value='"+item.substr(0,1)+"' />"+ item+"</p>";
                                });
                            }
                            $("#content").append(options);
                            $("#preUrl").attr("href","/test/user/"+testDTO.userDTO.id+"/pageNo/"+(testDTO.pageBean.curPage-1));
                            if(testDTO.pageBean.curPage === testDTO.pageBean.rowsCount) {
                                $("#nextUrl").attr("href", "/test/user/" + testDTO.userDTO.id + "/pageNo/1" );
                            }else{
                                $("#nextUrl").attr("href", "/test/user/" + testDTO.userDTO.id + "/pageNo/" + (testDTO.pageBean.curPage+1));
                            }
                            $.each(testDTO.answeredIds,function (index,value) {
                                $("#"+value).attr("class","a_green");
                            });
                            if(testDTO.answerMap[question.id+""]!==undefined){
                                    $.each(testDTO.answerMap[question.id+""].split(""),function (index,value) {
                                        $("input[name='" + question.id + "'][ value='" + value + "']").attr("checked", true);
                                    })

                            }
                          //  $.each(${answerMap}, function(key, value) {
                          //     $(value.split("")).each(val){
                          //          $("input[name='"+key+"'][ value='"+val+"']").attr("checked", true);
                         //       }
                         //   });

                        },"json").error(function(xhr,errorText,errorType){
                         alert('错误'+errorText);
                     });
                        return false;
                    });
                $("#submit").click(function(){
                    //
                    $("form").attr("action","/test/user/${testDTO.userDTO.id}/commit");
                    $("form").attr("method","POST");
                    $("form").submit();
            });
            }
    );
</script>
</body>
</html>

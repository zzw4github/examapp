<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/ks.css" rel="stylesheet" type="text/css" />
</head>

<body class="ks_bg">

<div class="min-w1" >

<div class="ks_top">清大新洋图书馆在线考试系统</div>

<div class="ks_left">
	<div class="ks_left_top">
    	<div>账号：${testDTO.userDTO.tmh}&nbsp;&nbsp;姓名：${testDTO.userDTO.name}	</div>
        <div>考试名称：${testDTO.examDTO.name}</div>
        <div>考试说明：${testDTO.examDTO.desc}</div>
        <div>题型：
            <c:forEach var="questionType" items="${testDTO.testDefineDTO.types}">
                ${questionType.type.name}${questionType.amount}道，每道${questionType.score}分
            </c:forEach>
        </div>
        <fmt:formatNumber var="castNumber" value="${resultDTO.costTime/60}" pattern="##"/>
        <div>考试用时： ${castNumber} 分钟${resultDTO.costTime%60}秒</div>
        <div class="red">考试成绩：${resultDTO.score}分</div>
        <div>说明：点击下方红色题号，可查看错题</div>
    </div>
  <div class="ks_an"><input type="image" src="/image/tuichu.gif" border=0></div>
    <div class="ks_left_down">
    	<div class="xuanti">
            <c:forEach var="pageNo" begin="1" end="${fn:length(resultDTO.questionDTOs)}" step="1">
                <c:if test="${resultDTO.questionDTOs[pageNo-1].tof == 0}">
                   <a href="/test/user/${resultDTO.userId}/question/${testDTO.ids[pageNo-1]}" class="a_red" id="q${testDTO.ids[pageNo-1]}">${pageNo}</a>
                </c:if>
                <c:if test="${resultDTO.questionDTOs[pageNo-1].tof == 1}">
                    <a href="/test/user/${resultDTO.userId}/question/${testDTO.ids[pageNo-1]}" class="a_green" id="q${testDTO.ids[pageNo-1]}">${pageNo}</a>
                </c:if>
            </c:forEach>
    	</div> 

    </div>
</div>

<div class="ks_right">
	<div class="ksr_1">${resultDTO.questionDTOs[0].type}：</div>
	<div class="ksr_2">
    <p id="content">${resultDTO.questionDTOs[0].content}</p>
    <c:forEach var="option" items="${resultDTO.questionDTOs[0].option.split(\" \")}" varStatus="status" >
        <c:if test="${resultDTO.questionDTOs[0].type.equals(\"多选题\")}">
            <p> <input type="checkbox" name="${resultDTO.questionDTOs[0].id}" value="${option.substring(0,1)}"  /> ${option}</p>
        </c:if>
        <c:if test="${!resultDTO.questionDTOs[0].type.equals(\"多选题\")}">
            <p> <input type="radio" name="${resultDTO.questionDTOs[0].id}" value="${option.substring(0,1)}"  /> ${option}</p>
        </c:if>
    </c:forEach>
    <div class="right_an"><br>所选答案<b id="answer">${resultDTO.questionDTOs[0].answer}</b>，正确答案<b id="trueAnswer">${resultDTO.questionDTOs[0].trueAnswer}</b></div>
    </div>
    <div class="ks_an"><input type="image" src="/image/syt.gif" id="pre" ><input type="image" src="/image/xyt.gif" id="next"></div>
    <a href="/test/user/${resultDTO.userId}/question/${resultDTO.questionDTOs[0].id}" style="display: none" id="preUrl"></a>
    <a href="/test/user/${resultDTO.userId}/question/${resultDTO.questionDTOs[1].id}" style="display: none" id="nextUrl"></a>
</div>


</div>
<script type="text/javascript" src="/js/jquery-2.2.3.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
      $(".a_red , .a_green, #pre ,#next").click(function(event){
          var url="";
//          event.preventDefault();
          if($(this).attr("id")=== "pre"){
              url = $("#preUrl").attr("href");
          }
          else if($(this).attr("id")=== "next"){
              url = $("#nextUrl").attr("href");
          }else{
            url = $(this).attr("href");
          }

          $.get(
                  url,function(result) {
                      $(".ksr_1").html(result.type);
                      $("#content").html(result.content);
//                      {
//                          "id": 0,
//                              "tof": 0,
//                              "trueAnswer": "D",
//                              "answer": "A",
//                              "content": "以下关于入馆须知说法错误的是（）。",
//                              "option": "A、保持馆内卫生、整洁；不随地吐痰、乱扔杂物；不在馆内进餐、吃零食；不带宠物入馆。 B、图书馆是重点防火单位，严禁携带易燃易爆物品入馆，严禁在馆内任何地方吸烟。 C、爱护馆内书刊、设备及其他设施，禁止涂抹刻画等污损、破坏行为；馆内公物（包括书刊、设备等）未办理借出手续，严禁私自带出； D、维护馆内秩序，不随意挪动阅览桌椅，但可以用物品占阅览座位。",
//                              "type": "单选题"
//                      }
                      $(".ksr_2").remove("radio");
                      $(".ksr_2").remove("checkbox");
                      $(":radio,:checkbox").parent("p").remove();
                      var options = "";
                      if (result.type !== "多选题") {

                      $.each(result.option.split(" "), function (i, item) {
                          options+="<p><input type='radio' name='"+result.id+"' value='"+item.substr(0,1)+"' />"+ item+"</p>";
                      });
                    }
                      if (result.type === "多选题") {
                          $.each(result.option.split(" "), function (i, item) {
                              options+= "<p><input type='checkbox' name='"+result.id+"' value='"+item.substr(0,1)+"' />"+ item+"</p>";
                          });
                      }
                      $("#content").append(options);
                      $("#answer").text(result.answer);
                      $("#trueAnswer").text(result.trueAnswer);
                      $("#preUrl").attr("href","/test/user/"+result.userId+"/question/"+result.preId);
                      $("#nextUrl").attr("href","/test/user/"+result.userId+"/question/"+result.nextId);
                  }

          );
          return false ;
      });
    });
</script>
</body>
</html>

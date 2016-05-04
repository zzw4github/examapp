<%@ page import="com.infosea.examApp.vo.PageBean" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/icon/favicon.ico">
    <title>EXAM-APP</title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/jquery.flipcountdown.css">
    <script src="/js/jquery-2.2.3.js"></script>
    <script src="/js/bootstrap.min.js" defer></script>
    <script src="/js/eModal.min.js"></script>
    <script src="/js/jquery.flipcountdown.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <script src="/js/highlight.min.js"></script>
    <style type="text/css">
        .pagination > li > input{
            display: inline;
            position: relative;
            float: left;
            padding: 6px 12px;
            margin-left: -1px;
            line-height: 1.42857143;
            color: #337ab7;
            text-decoration: none;
            background-color: #fff;
            border: 1px solid #ddd;
        }

        .pagination > li:last-child > input{
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
        }
    </style>
    <![endif]-->

</head>
<body>
<div class="container">
    <div class="row text-center">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <input type="hidden" id="time" value="${exam.time}"/>

            <label>计时：</label>
            <span id="t_h">00时</span>
            <span id="t_m">00分</span>
            <span id="t_s">00秒</span>
            <input type="button" id="btn" value="开始测试" class="btn btn-primary"/>
        </div>

        <div class="col-lg-3"></div>
    </div>
    <form role="form" method="post" action="/main/single">
        <c:forEach items="${questions}" var="question" varStatus="status">
        <div class="row ">
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                <h4 class="text-danger">${question.questionType.desc} ( 每题 ${question.questionType.score} 分 共${question.questionType.amount} 题)</h4>
                <h4>${pageBean.curPage}.${question.question}
                    <small>${question.desc}</small>
                </h4>

                <div>
                    <c:if test="${question.questionType.id<=2}">
                        <c:forEach items="${question.option.value.split(\" \")}" var="opt">
                            <div class="radio">
                                <label>
                                    <input type="radio" name=${question.id}-${status.index} id="${opt.substring(0,1)}"
                                           value="${opt.substring(0,1)}"><small class="text-danger">${opt.substring(0,2)}</small>${opt.substring(2)}
                                </label>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${question.questionType.id==3}">
                        <c:forEach items="${question.option.value.split(\" \")}" var="opt">
                            <div class="checkbox">
                                <div>
                                    <label>
                                    <input type="checkbox" name=${question.id}-${status.index}
                                           id="${opt.substring(0,1)}"
                                           value="${opt.substring(0,1)}"><small class="text-danger">${opt.substring(0,2)}</small>${opt.substring(2)}
                                    </label>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
a
                </c:forEach>
                <button type="submit" class="btn btn-primary">提交</button>
                <input type="button" class="btn btn-danger" id="tijiao" value="完成测试"/>
                </div>
                </div>
            <div class="col-lg-3"></div>
        </div>

        <div class="row text-center">
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                <ul class="pagination">
                    <c:if test="${pageBean.curPage>1}">
                        <li><a href="/main/${pageBean.curPage-1}">&laquo;</a></li>
                    </c:if>
                    <c:if test="${pageBean.curPage+4<=pageBean.pageCount}">
                        <c:forEach var="i" begin="${pageBean.curPage}" end="${pageBean.curPage+4}" step="1">
                            <li class="active"><a href="/main/${i}"><c:out value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageBean.curPage+4>pageBean.pageCount}">
                        <c:forEach var="i" begin="${pageBean.pageCount-4}" end="${pageBean.pageCount}" step="1">
                            <li class="active"><a href="/main/${i}"><c:out value="${i}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${pageBean.curPage<pageBean.pageCount}">
                        <li><a href="/main/${pageBean.curPage+1}">&raquo;</a></li>
                    </c:if>
                    <li>
                        <input placeholder="跳到第 页" type="text" id="goto" name="page" size="5" style="padding :5px">
                    </li>
                </ul>
            </div>
            <div class="col-lg-3"></div>
        </div>
    </form>
    <footer>
        <p class="text-center text-muted">&copy; Vasily A., 2014&ndash;2016</p>
    </footer>
</div>
<%--temp2 保存 做过的题的答案--%>
<c:forEach var="answermap" items="${temp2}">
    <c:forEach var="answerval" items="${answermap.value.toCharArray()}">
        <script type="text/javascript">
            $("input[name='${answermap.key}'][ value='${answerval}']").attr("checked", true);
        </script>
    </c:forEach>
</c:forEach>

<script type="text/javascript">
    $(".pagination > li > a").each(
            function () {
                if ($(this).html() === '${pageBean.curPage}') {
                    $(this).parent().addClass("disabled");
                }
            }
    );
    $("#tijiao").click(
            function () {
                eModal.ajax('flash', '测试成绩');
            }
    );
    $(document).ready(
            function () {
                $("#btn").click(function () {
                    $(this).attr('disabled', "true");
                    var period = parseInt($("#time").val());
                    var t=period*60;
                    function GetRTime() {
                        if(t>=0) {
                            var h = Math.floor(t / 3600);
                            var m = Math.floor(t / 60);
                            var s = Math.floor(t % 60);
                            if(t<=5*60){
                                $("#t_h").css("color","red");
                                $("#t_m").css("color","red");
                                $("#t_s").css("color","red");
                            }
                            $("#t_h").html(h + "时");
                            $("#t_m").html(m + "分");
                            $("#t_s").html (s + "秒");
                            --t;
                        }else{
                            clearInterval(timer);
                            eModal.ajax('main/flash', '考试时间到，你的成绩是：');
                        }
                    }
                   timer = setInterval(GetRTime, 1000);
                });
                $('#goto').keydown(function (e) {
                    if (e.keyCode == 13) {
                        $("form").submit();
                    }
                });
            });
</script>
</body>
</html>
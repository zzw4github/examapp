<%@ page import="com.infosea.examApp.vo.PageBean" %>
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
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/pagination.css" rel="stylesheet">
    <script src="/js/jquery-2.2.3.js"></script>
    <script src="/js/bootstrap.min.js" ></script>
    <script src="/js/pagination.js" ></script>
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
    <![endif]-->
    <script href="/js/jquery.tabledit.min.js"></script>
    <title>成绩管理</title>
    <style type="text/css">
        #result #pagination-container {
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <form role="form" action="/score/all" data-toggle="result">
                <input type="text" id="username" name="username" placeholder="用户名">
                <button type="submit" class="btn btn-primary" value="提交"></button>
            </form>
        </div>

    </div>
    <div id="result">
        <div class="col-md-6 ">
            <table class="table table-striped table-hover" id="tbl">
                <caption>考试成绩</caption>
                <thead>
                <tr id="h">
                    <th>id</th>
                    <th>描述</th>
                    <th>成绩</th>
                    <th>学生姓名</th>
                </tr>
                </thead>
                <tbody id ="data-container">
                <%--<c:forEach items="${examList}" var="exam">--%>
                <%--<tr>--%>
                <%--<td>${exam.id}</td>--%>
                <%--<td>${exam.examdesc}</td>--%>
                <%--<td>${exam.score}</td>--%>
                <%--<td>${user.name}</td>--%>
                <%--</tr>--%>
                <%--</c:forEach>--%>
                </tbody>
            </table>
            <div id="pagination-container">
            </div>
        </div>
    </div>

</div>
<c:if test="${exams!=null}">
    <script type="text/javascript">

        $('#pagination-container').pagination({
            dataSource: ${exams},
            pageSize: 5,
            showGoInput: true,
            showGoButton: true,
            className: 'paginationjs-theme-blue',
            callback: function (data, pagination) {
                var html = simpleTemplating(data);
                $('#data-container').html(html);
            }
        })
        function simpleTemplating(data) {
            var html ='';
            $.each(data, function (index, item) {
                html += '<tr>';
                html += '<td>' + item.id + '</td>';
                html += '<td>' + item.desc + '</td>';
                html += '<td>' + item.score + '</td>';
                html += '<td>' + item.userName + '</td>';
                html += '</tr>';
            });
            return html;
        }
        $("#pagination-container").show();
        $("#data-container").show();
    </script>
</c:if>
</body>
</html>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/icon/favicon.ico">
    <title>EXAM-APP</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/bootstrap-submenu.min.css" rel="stylesheet">
    <script src="/js/jquery-2.2.3.js"></script>
    <script src="/js/bootstrap.min.js" defer></script>
    <script src="/js/bootstrap-submenu.min.js" defer></script>
    <script src="/js/jquery.tabledit.min.js"></script>
    <script src="/js/eModal.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="container">
    <table class="table table-striped" id="tbl">
        <caption>info</caption>
        <thead>
        <tr id="h">
            <th>id</th>
            <th>name</th>
            <th>date</th>
            <th>valid</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${testPaper}" var="testPaper">
            <tr>
                <td>${testPaper.id}</td>
                <td>${testPaper.desc}</td>
                <td>${testPaper.date}</td>
                <td>${testPaper.validFlag}</td>
                    <%--<td><a href="/testPaper/update/${testPaper.id}" data-toggle="modal" data-target="#updExam">修改试卷</a></td>--%>
                    <%--<td> <a href="/testPaper/delete/${testPaper.id}" data-toggle="modal" data-target="#delExam">删除试卷</a></td>--%>
                    <%--<td><button id="upd">修改试卷</button></td>--%>
                    <%--<td>--%>
                    <%--<button id="del">删除试卷</button>--%>
                    <%--</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    function upd() {
        eModal.confirm(message, null)
                .then(callback, callbackCancel);
    }
    $("#del").click(
            function () {
                var vtd =$(this).parents("td").siblings().first();
                var eid = vtd.html();
                if (eModal.confirm('确认删除这条信息?', '删除信息')) {
                    eModal.ajax('/testPaper/del/' + eid + '', '删除信息')
                            .then(function () {
                                vtd.parent("tr").remove();
                            });
                }
            }
    );
    $('#tbl').Tabledit({
        url: '/testPaper/table',
        columns: {
            identifier: [0, 'id'],
            editable: [[1, 'name'], [2, 'date'], [3, 'valid']]
        },
        onDraw: function() {
            console.log('onDraw()');
        },
        onSuccess: function(data, textStatus, jqXHR) {
            eModal.alert(data);
        },
        onFail: function(jqXHR, textStatus, errorThrown) {
            console.log('onFail(jqXHR, textStatus, errorThrown)');
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        },
        onAlways: function() {
            console.log('onAlways()');
        },
        onAjax: function(action, serialize) {
            console.log('onAjax(action, serialize)');
            console.log(action);
            console.log(serialize);
        }
    });
</script>
</body>
</html>

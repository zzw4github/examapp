<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/icon/favicon.ico">

    <title>试题上传</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/fileinput.min.css">
    <link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/js/jquery-2.2.3.js"></script>
    <script type="text/javascript" src=/js/fileinput.min.js"></script>
    <script type="text/javascript" src="/js/fileinput_locale_zh.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        i = 1;
        j = 1;
        $(document).ready(function () {
            $("#btn_add1").click(function () {
                document.getElementById("newUpload1").innerHTML += '<div id="div_' + i + '"><input  name="file" type="file"  /><input type="button" value="删除"  onclick="del_1(' + i + ')"/></div>';
                i = i + 1;
            });
            $("#btn_add2").click(function () {
                document.getElementById("newUpload2").innerHTML += '<div id="div_' + j + '"><input  name="file_' + j + '" type="file"  /><input type="button" value="删除"  onclick="del_2(' + j + ')"/></div>';
                j = j + 1;
            });
        });
        function del_1(o) {
            document.getElementById("newUpload1").removeChild(document.getElementById("div_" + o));
        }

        function del_2(o) {
            document.getElementById("newUpload2").removeChild(document.getElementById("div_" + o));
        }

    </script>
</head>
<body>


<div class="container">

    <div class="row text-center">
        <div class="col-3"></div>
        <div class="col-3">
        <h1>上传试题文件</h1>

        <form class="form" name="userForm1" action="/file/upload" enctype="multipart/form-data" method="post">
            <div class="form-group " id="newUpload1">
                <label for="f">试题文件</label>
                <input type="file" name="file" id="f" class="file">
                <p class="help-block">DIC类型的文件</p>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-info" id="btn_add1">增加一行</button>
                <button type="submit" class="btn btn-primary">上传</button>
            </div>
        </form>
        </div>
        <div class="col-3"></div>
        </div>
</div>

<br>
<br>
<br>
<br>
</form>
</body>
</html>
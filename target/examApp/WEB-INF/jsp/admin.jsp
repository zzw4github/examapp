<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="ch">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="左右结构项目，属于大人员的社交工具">
    <meta name="keywords" content="左右结构项目 社交 占座 ">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title>管理员界面！</title>
    <script src="/js/jquery-2.2.3.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <script src ="/js/eModal.min.js"></script>
    <script>
        $(function () {
            $(".meun-item").click(function () {
                $(".meun-item").removeClass("meun-item-active");
                $(this).addClass("meun-item-active");
                var itmeObj = $(".meun-item").find("img");
                itmeObj.each(function () {
                    var items = $(this).attr("src");
                    items = items.replace("_grey.png", ".png");
                    items = items.replace(".png", "_grey.png")
                    $(this).attr("src", items);
                });
                var attrObj = $(this).find("img").attr("src");
                ;
                attrObj = attrObj.replace("_grey.png", ".png");
                $(this).find("img").attr("src", attrObj);
            });

        })
    </script>
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/flCommon.css"/>
    <link rel="stylesheet" type="text/css" href="/css/flSlide.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/flat-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.nouislider.css">
</head>

<body>
<div id="wrap">
    <!-- 左侧菜单栏目块 -->
    <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
            <p id="logoP"><img id="logo" alt="图书馆入学考试系统" src="/images/logo.png"><span>管理员</span></p>
        </div>
        <div id="personInfor">
            <p id="userName">${user.name} </p>

            <p><span></span></p>

            <p>
                <a id="logout">退出登录</a>
            </p>
        </div>
        <div class="meun-title">学生管理</div>
        <div class="meun-item meun-item-active" href="#user" aria-controls="user" role="tab" data-toggle="tab">
            <img src="/images/icon_source.png">学生管理
        </div>
        <div class="meun-title">试卷管理</div>
        <div class="meun-item" href="#testPaper" aria-controls="testPaper" role="tab" data-toggle="tab"><img
                src="/images/icon_house_grey.png">试卷管理
        </div>
        <div class="meun-item" href="#stud" aria-controls="stud" role="tab" data-toggle="tab"><img
                src="/images/icon_card_grey.png">上传试卷
        </div>
        <div class="meun-title">试题管理</div>
        <div class="meun-item" href="#questions" aria-controls="question" role="tab" data-toggle="tab"><img
                src="/images/icon_house_grey.png">试题管理
        </div>
    </div>
    <!-- 右侧具体内容栏目 -->
    <div id="rightContent">
        <a class="toggle-btn" id="nimei">
            <i class="glyphicon glyphicon-align-justify"></i>
        </a>
        <!-- Tab panes -->
        <div class="tab-content">
            <!--用户管理模块-->
            <div role="tabpanel" class="tab-pane" id="user">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addUser">添加用户
                        </button>
                    </div>
                    <div class="col-sm-3 ">
                            <input type="text" class="form-control input-sm" id="usrInp" placeholder="输入文字搜索">
                            <button class="btn btn-white btn-xs " id="usrBtn">查 询</button>
                   </div>
                    <div class="col-lg-3 col-lg-offset-2 col-xs-4"
                         style=" padding-right: 40px;text-align: right;">
                        <label for="usrOrd">排序:&nbsp;</label>
                        <select class=" form-control" id="usrOrd">
                            <option value="userName">用户名</option>
                        </select>
                    </div>
                </div>
                <div class="data-div" id="usrDiv">
                    <div class="row tableHeader">
                        <div class="col-xs-2 ">
                            用户名
                        </div>
                        <div class="col-xs-2">
                            真实姓名
                        </div>
                        <div class="col-xs-2">
                            电话
                        </div>
                        <div class="col-xs-2">
                            状态
                        </div>
                        <div class="col-xs-2">
                            操作
                        </div>
                    </div>
                </div>
                <!--页码块-->


                <!--弹出添加用户窗口-->
                <div class="modal fade" id="addUser" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel_user">添加用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group ">
                                            <label for="sName" class="col-xs-3 control-label">用户名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="email" class="form-control input-sm duiqi"
                                                       id="sName_user" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">真实姓名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sLink_user"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电话：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sOrd_user"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电子邮箱：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sKnot_user"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">地区：</label>

                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">国际关系地区</option>
                                                    <option value="">北京大学</option>
                                                    <option value="">天津大学</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">权限：</label>

                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">管理员</option>
                                                    <option value="">普通</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="situation" class="col-xs-3 control-label">状态：</label>

                                            <div class="col-xs-8" id="situation">
                                                <label class="control-label" for="normal_user">
                                                    <input type="radio" name="situation"
                                                           id="normal_user">正常</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="control-label" for="forbid_user">
                                                    <input type="radio" name="situation" id="forbid_user">
                                                    禁用</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn btn-xs btn-green">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!--弹出修改用户窗口-->
                <div class="modal fade" id="reviseUser" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel_updateUser">修改用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal" id="updUsrForm">
                                        <div class="form-group ">
                                            <label for="sName" class="col-xs-3 control-label">用户名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi" name="name"
                                                       id="sName" placeholder="用户名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">真实姓名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi" id="sLink" name="xingming"
                                                       placeholder="真实姓名">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电子邮箱：</label>

                                            <div class="col-xs-8">
                                                <input type="email" class="form-control input-sm duiqi" id="sOrd" name="email"
                                                       placeholder="电子邮箱">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电话：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" name="tel"
                                                       id="tel_updateUser"
                                                       placeholder="电话">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">地区：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" name="area"
                                                       id="area_updateUser"
                                                       placeholder="地区">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">权限：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="sKnot" name="permission"
                                                       placeholder="权限">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="situation" class="col-xs-3 control-label">状态：</label>

                                            <div class="col-xs-8">
                                                <label class="control-label" for="normal_upd">
                                                    <input type="radio" name="situation"
                                                           id="normal_upd">正常</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="control-label" for="forbid_upd">
                                                    <input type="radio" name="situation" id="forbid_upd" >
                                                    禁用</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn btn-xs btn-green" id="savUpdUsrBtn">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->


            </div>
            <div role="tabpanel" class="tab-pane" id="testPaper">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addTestPaper">添加试卷
                        </button>
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control input-sm" placeholder="输入文字搜索">
                        <button class="btn btn-white btn-xs " id="tpBtn">查 询</button>
                    </div>
                    <div class="col-lg-3 col-lg-offset-2 col-xs-4"
                         style=" padding-right: 40px;text-align: right;">
                        <label for="queryCond_tp">查询条件:&nbsp;</label>
                        <select class=" form-control" id="queryCond_tp">
                            <option>试卷名称</option>
                        </select>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-2 ">
                            试卷名称
                        </div>
                        <div class="col-xs-2">
                            单选题IDs
                        </div>
                        <div class="col-xs-2">
                            多选题IDs
                        </div>
                        <div class="col-xs-2">
                            判断题IDs
                        </div>
                        <div class="col-xs-2">
                            操作
                        </div>
                    </div>
                    <div class="tablebody">

                        <div class="row">
                            <div class="col-xs-2 ">
                                goodmoning
                            </div>
                            <div class="col-xs-2">
                                国际关系地区
                            </div>
                            <div class="col-xs-2">
                                李豆豆
                            </div>
                            <div class="col-xs-2">
                                13688889999
                            </div>
                            <div class="col-xs-2">
                                状态
                            </div>
                            <div class="col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal"
                                        data-target="#reviseUser">修改
                                </button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal"
                                        data-target="#deleteUser">删除
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                <!--页码块-->
                <footer class="footer">
                    <ul class="pagination">
                        <li>
                            <select>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                                <option>7</option>
                                <option>8</option>
                                <option>9</option>
                                <option>10</option>
                            </select>
                            页
                        </li>
                        <li class="gray">
                            共20页
                        </li>
                        <li>
                            <i class="glyphicon glyphicon-menu-left">
                            </i>
                        </li>
                        <li>
                            <i class="glyphicon glyphicon-menu-right">
                            </i>
                        </li>
                    </ul>
                </footer>

                <!--弹出添加用户窗口-->
                <div class="modal fade" id="addTestPaper" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel_TP">添加用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group ">
                                            <label for="sName" class="col-xs-3 control-label">用户名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="email" class="form-control input-sm duiqi"
                                                       id="sName_TP" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">真实姓名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sLink_TP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电子邮箱：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sOrd_TP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电话：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sKnot_TP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">地区：</label>

                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">国际关系地区</option>
                                                    <option value="">北京大学</option>
                                                    <option value="">天津大学</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">权限：</label>

                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">管理员</option>
                                                    <option value="">普通用户</option>
                                                    <option value="">游客</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="situation" class="col-xs-3 control-label">状态：</label>

                                            <div class="col-xs-8" id="situation_TP">
                                                <label class="control-label" for="normal_TP">
                                                    <input type="radio" name="situation"
                                                           id="normal_TP">正常</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="control-label" for="forbid_TP">
                                                    <input type="radio" name="situation" id="forbid_TP">
                                                    禁用</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn btn-xs btn-green">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!--弹出修改用户窗口-->
                <div class="modal fade" id="reviseQuestion" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel_updateQ">修改用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group ">
                                            <label for="sName" class="col-xs-3 control-label">用户名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="email" class="form-control input-sm duiqi"
                                                       id="mName_Q" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">真实姓名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi" id="mLink_Q"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电子邮箱：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="MOrd_Q"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电话：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="tel_updateQ"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">地区：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="area_updateQ"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">权限：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="mKnot_Q"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="situation" class="col-xs-3 control-label">状态：</label>

                                            <div class="col-xs-8">
                                                <label class="control-label" for="mNormal_TP">
                                                    <input type="radio" name="situation"
                                                           id="mNormal_Q">正常</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="control-label" for="mforbid_TP">
                                                    <input type="radio" name="situation" id="mforbid_Q">
                                                    禁用</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn btn-xs btn-green">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!--弹出删除试卷警告窗口-->
                <div class="modal fade" id="deleteQuestion" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="mGridSystemModalLabel_Q">提示</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    确定要删除该用户？删除后不可恢复！
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn  btn-xs btn-danger">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
            </div>
            <div role="tabpanel" class="tab-pane" id="question">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addTestPaper">添加用户
                        </button>
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control input-sm" placeholder="输入文字搜索">
                        <button class="btn btn-white btn-xs ">查 询</button>
                    </div>
                    <div class="col-lg-3 col-lg-offset-2 col-xs-4"
                         style=" padding-right: 40px;text-align: right;">
                        <label for="paixu_question">排序:&nbsp;</label>
                        <select class=" form-control" id="paixu_question">
                            <option>用户名</option>
                        </select>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-2 ">
                            用户名
                        </div>
                        <div class="col-xs-2">
                            真实姓名
                        </div>
                        <div class="col-xs-2">
                            电话
                        </div>
                        <div class="col-xs-2">
                            状态
                        </div>
                        <div class="col-xs-2">
                            操作
                        </div>
                    </div>
                    <div class="tablebody">

                        <div class="row">
                            <div class="col-xs-2 ">
                                goodmoning
                            </div>
                            <div class="col-xs-2">
                                国际关系地区
                            </div>
                            <div class="col-xs-2">
                                李豆豆
                            </div>
                            <div class="col-xs-2">
                                13688889999
                            </div>
                            <div class="col-xs-2">
                                状态
                            </div>
                            <div class="col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal"
                                        data-target="#reviseQustion">修改
                                </button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal"
                                        data-target="#deleteQuestion">删除
                                </button>
                            </div>
                        </div>

                    </div>

                </div>
                <!--页码块-->
                <footer class="footer">
                    <ul class="pagination">
                        <li>
                            <select>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                                <option>7</option>
                                <option>8</option>
                                <option>9</option>
                                <option>10</option>
                            </select>
                            页
                        </li>
                        <li class="gray">
                            共20页
                        </li>
                        <li>
                            <i class="glyphicon glyphicon-menu-left">
                            </i>
                        </li>
                        <li>
                            <i class="glyphicon glyphicon-menu-right">
                            </i>
                        </li>
                    </ul>
                </footer>

                <!--弹出添加用户窗口-->
                <div class="modal fade" id="addQuestion" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel_Q">添加用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group ">
                                            <label for="sName" class="col-xs-3 control-label">用户名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="email" class="form-control input-sm duiqi"
                                                       id="sName_Q" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">真实姓名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sLink_Q"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电子邮箱：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sOrd_Q"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电话：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="sKnot_Q"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">地区：</label>

                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">国际关系地区</option>
                                                    <option value="">北京大学</option>
                                                    <option value="">天津大学</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">权限：</label>

                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">管理员</option>
                                                    <option value="">普通用户</option>
                                                    <option value="">游客</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="situation" class="col-xs-3 control-label">状态：</label>

                                            <div class="col-xs-8" id="situation_Q">
                                                <label class="control-label" for="normal_TP">
                                                    <input type="radio" name="situation"
                                                           id="normal_Q">正常</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="control-label" for="forbid_TP">
                                                    <input type="radio" name="situation" id="forbid_Q">
                                                    禁用</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn btn-xs btn-green">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!--弹出修改用户窗口-->
                <div class="modal fade" id="reviseTP" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel_updateTP">修改用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group ">
                                            <label for="sName" class="col-xs-3 control-label">用户名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="email" class="form-control input-sm duiqi"
                                                       id="mName_TP" placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">真实姓名：</label>

                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi" id="mLink_TP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电子邮箱：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="MOrd_TP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电话：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="tel_updateTP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">地区：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi"
                                                       id="area_updateTP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">权限：</label>

                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="mKnot_TP"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="situation" class="col-xs-3 control-label">状态：</label>

                                            <div class="col-xs-8">
                                                <label class="control-label" for="mNormal_TP">
                                                    <input type="radio" name="situation"
                                                           id="mNormal_TP">正常</label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="control-label" for="mforbid_TP">
                                                    <input type="radio" name="situation" id="mforbid_TP">
                                                    禁用</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn btn-xs btn-green">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!--弹出删除试卷警告窗口-->
                <div class="modal fade" id="deleteTP" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="mGridSystemModalLabel_TP">提示</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    确定要删除该用户？删除后不可恢复！
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消
                                </button>
                                <button type="button" class="btn  btn-xs btn-danger">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
            </div>
        </div>
    </div>
        </div>
    </div>
</div>
<script src="/js/jquery.nouislider.js"></script>
<script src ="/js/admin.js"></script>
<!-- this page specific inline scripts -->
<script>
    //min/max slider
    function huadong(my, unit, def, max) {
        $(my).noUiSlider({
            range: [0, max],
            start: [def],
            handles: 1,
            connect: 'upper',
            slide: function () {
                var val = Math.floor($(this).val());
                $(this).find(".noUi-handle").text(
                        val + unit
                );
                console.log($(this).find(".noUi-handle").parent().parent().html());
            },
            set: function () {
                var val = Math.floor($(this).val());
                $(this).find(".noUi-handle").text(
                        val + unit
                );
            }
        });
        $(my).val(def, true);
    }
    huadong('.slider-minmax1', "分钟", "5", 30);
    huadong('.slider-minmax2', "分钟", "6", 15);
    huadong('.slider-minmax3', "分钟", "10", 60);
    huadong('.slider-minmax4', "次", "2", 10);
    huadong('.slider-minmax5', "天", "3", 7);
    huadong('.slider-minmax6', "天", "8", 10);
</script>
</body>

</html>
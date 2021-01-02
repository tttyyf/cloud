<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- jstl标签库命名空间 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../app.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <title>管理员-展示专业信息</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet"
          href="${appPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${appPath}/assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${appPath}/assets/vendor/linearicons/style.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="${appPath}/assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="${appPath}/assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
            rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76"
          href="${appPath}/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96"
          href="${appPath}/assets/img/favicon.png">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand" style="padding: 16px 60px;">
            <a href="${appPath}/manager/ManagerTables.jsp"><img src="${appPath}/assets/img/logo.png"
                                      alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth">
                    <i class="lnr lnr-arrow-left-circle"></i>
                </button>
            </div>
            <form  id="form_query" action="${appPath}/SubjectQueryName" class="navbar-form navbar-left">
                <div class="input-group">
                    <input id="page" name="pageNo" type="hidden" value="1"/>
                    <input type="text" value="${SubjectQueryName}" name="su_name" class="form-control"
                           placeholder="根据名字进行搜索"> <span
                        class="input-group-btn"><button type="submit"
                                                        class="btn btn-primary">Go</button></span>
                </div>
            </form>
            <div class="navbar-btn navbar-btn-right">
                <a class="btn btn-success update-pro"
                   href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro"
                   title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i>
                    <span>UPGRADE TO PRO</span></a>
            </div>
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#"
                                            class="dropdown-toggle icon-menu" data-toggle="dropdown"> <i
                            class="lnr lnr-alarm"></i> <span class="badge bg-danger">5</span>
                    </a>
                        <ul class="dropdown-menu notifications">
                            <li><a href="#" class="notification-item"><span
                                    class="dot bg-warning"></span>System space is almost full</a></li>
                            <li><a href="#" class="notification-item"><span
                                    class="dot bg-danger"></span>You have 9 unfinished tasks</a></li>
                            <li><a href="#" class="notification-item"><span
                                    class="dot bg-success"></span>Monthly report is available</a></li>
                            <li><a href="#" class="notification-item"><span
                                    class="dot bg-warning"></span>Weekly meeting in 1 hour</a></li>
                            <li><a href="#" class="notification-item"><span
                                    class="dot bg-success"></span>Your request has been approved</a></li>
                            <li><a href="#" class="more">See all notifications</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown"><i class="lnr lnr-question-circle"></i>
                        <span>Help</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Basic Use</a></li>
                            <li><a href="#">Working With Data</a></li>
                            <li><a href="#">Security</a></li>
                            <li><a href="#">Troubleshooting</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown"><img src="${appPath}/assets/img/user.png"
                                                                        class="img-circle" alt="Avatar">
                        <span>${login_manager.e_name}</span> <i
                                class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="lnr lnr-user"></i> <span>My
										Profile</span></a></li>
                            <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
                            <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                            <li><a href="${appPath}/ManagerLogOut"><i class="lnr lnr-exit"></i>
                                <span>Logout</span></a></li>
                        </ul>
                    </li>
                    <!-- <li>
                            <a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
                        </li> -->
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li><a href="#substu" data-toggle="collapse" class="collapsed" style="margin-top: 10px"><i
                            class="lnr lnr-home"></i> <span>学生管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="substu" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/StudentQuery" class="">显示学生信息</a></li>
                                <li><a href="${appPath}/manager/addStudent.jsp" class="">添加学生</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subles" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-code"></i> <span>课程管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subles" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/LessonQuery" class="">显示课程信息</a></li>
                                <li><a href="${appPath}/manager/addLesson.jsp" class="">添加课程</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subcol" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-chart-bars"></i> <span>学院管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subcol" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/CollegeQuery" class="">显示学院信息</a></li>
                                <li><a href="${appPath}/manager/addCollege.jsp" class="">添加学院</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subsub" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-cog"></i> <span>专业管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subsub" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/SubjectQuery" class="">显示专业信息</a></li>
                                <li><a href="${appPath}/manager/addSubject.jsp" class="">添加专业</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subtea" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-chart-bars"></i> <span>教师管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subtea" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeacherQuery" class="">显示教师信息</a></li>
                                <li><a href="${appPath}/manager/addTeacher.jsp" class="">添加新教师</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subman" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-alarm"></i> <span>教务管理员</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subman" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/ManagerQuery" class="">显示管理员信息</a></li>
                                <li><a href="${appPath}/manager/addManager.jsp" class="">添加管理员</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subfile" data-toggle="collapse" class="collapsed"><i class="lnr lnr-alarm"></i> <span>教学文件管理</span>
                            <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subfile" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/FileQuery" class="">显示教学文件</a></li>
                                <li><a href="${appPath}/fileManage/fileUp.jsp" class="">上传教学文件</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <!-- TABLE STRIPED -->
        <div class="panel">
            <div class="panel-heading">
                <h3 class="panel-title">当前专业信息</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>专业号</th>
                        <th>专业名</th>
                        <th>描述</th>
                        <th>学院</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- items表示遍历的数据源          var表示的是一个临时变量 -->
                    <!--  el表达式      格式：${key} 比如说     ${StudentList} 获取保存在容器中的StudentList    -->
                    <c:forEach items="${SubjectList}" var="item">
                        <tr>
                            <td>${item.su_id}</td>
                            <td>${item.su_name}</td>
                            <td>${item.su_describe}</td>
                            <td>
                                <c:forEach items="${CollegeList}" var="colitem">
                                    <c:if test="${item.c_id==colitem.c_id}"> ${colitem.c_name} </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success">
                                    <a href="${appPath}/SubjectToUpdate?su_id=${item.su_id}">编辑</a>
                                </button>
                                <button type="button" class="btn btn-danger">
                                    <a href="${appPath}/SubjectDelete?su_id=${item.su_id}">删除</a>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
        <!-- END TABLE STRIPED -->
        <!-- 分页标签 -->
        <div class="page">
            【${pageInfo.pageIndex}/${pageInfo.totalPage}，共${pageInfo.totalRecordNum}条记录】
            <a href="javascript:jumpPage(1)" title="">首页</a>
            <a href="javascript:jumpPage(${pageInfo.prePage})" title="">上一页</a>
            <a href="javascript:jumpPage(${pageInfo.nextPage})" title="">下一页</a>
            <a href="javascript:jumpPage(${pageInfo.totalPage})" title="">尾页</a>
        </div>
        <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
    <div class="clearfix"></div>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="${appPath}/assets/vendor/jquery/jquery.min.js"></script>
<script src="${appPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${appPath}/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="${appPath}/assets/scripts/klorofil-common.js"></script>
<script type="application/javascript">
    function jumpPage(page) {
        //要修改访问的页码
        document.getElementById("page").value = page;
        document.getElementById("form_query").submit();
    }
</script>
</body>

</html>
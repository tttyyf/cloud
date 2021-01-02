<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../app.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <title>教师-修改课程信息</title>
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
            <a href="${appPath}/teacher/TeacherTables.jsp"><img src="${appPath}/assets/img/logo.png"
                                      alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth">
                    <i class="lnr lnr-arrow-left-circle"></i>
                </button>
            </div>
            <form action="${appPath}/TeaLessonQueryName"
                  class="navbar-form navbar-left">
                <div class="input-group">
                    <input type="text" value="${LessonQueryName}" name="l_name"
                           class="form-control" placeholder="根据名字进行搜索"> <span
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
                                            data-toggle="dropdown"><img src="${appPath}/assets/img/user3.png"
                                                                        class="img-circle" alt="Avatar">
                        <span>${login_teacher.t_name}</span>
                        <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="lnr lnr-user"></i> <span>My
										Profile</span></a></li>
                            <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
                            <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                            <li><a href="${appPath}/TeacherLogOut"><i
                                    class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll" style="height: 100%;">
            <nav>
                <ul class="nav">
                    <li><a href="#substu" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-home"></i> <span>学生管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="substu" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaStudentQuery" class="">显示学生信息</a></li>
                                <li><a href="${appPath}/teacher/addStudent.jsp" class="">添加学生</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subles" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-code"></i> <span>课程管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subles" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaLessonQuery" class="">显示课程信息</a></li>
                                <li><a href="${appPath}/teacher/addLesson.jsp" class="">添加课程</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subcol" data-toggle="collapse" class="collapsed"><i class="lnr lnr-chart-bars"></i>
                            <span>学院信息</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subcol" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaCollegeQuery" class="">显示学院信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subsub" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>专业信息</span>
                            <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subsub" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaSubjectQuery" class="">显示专业信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subtea" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-chart-bars"></i> <span>教师信息</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subtea" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaTeacherQuery" class="">显示教师信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#subper" data-toggle="collapse" class="collapsed"><i
                            class="lnr lnr-user"></i> <span>个人信息管理</span> <i
                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subper" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaPersonQuery" class="">显示个人信息</a></li>
                                <li><a href="${appPath}/TeaPersonToUpdate?t_id=${login_teacher.t_id}"
                                       class="">修改个人信息</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subfile" data-toggle="collapse" class="collapsed"><i class="lnr lnr-alarm"></i> <span>教学文件信息</span>
                            <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subfile" class="collapse ">
                            <ul class="nav">
                                <li><a href="${appPath}/TeaFileQuery" class="">显示教学文件</a></li>
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
        <div class="main-content">
            <div class="container-fluid">
                <h3 class="page-title">学生管理模块</h3>
                <div class="row">
                    <!-- INPUTS -->
                    <div class="panel">
                        <div class="panel-heading">
                            <h3 class="panel-title">修改学生信息</h3>
                        </div>
                        <!-- 表单用于提交数据  action="${appPath}/StudentUpdate" -->
                        <form action="${appPath}/TeaLessonUpdate">
                            <div class="panel-body">
                                <input type="hidden" name="l_id" value="${UpdateLesson.l_id}">
                                课程名：<input type="text" name="l_name" class="form-control"
                                           placeholder="请输入课程名" value="${UpdateLesson.l_name}"> <br>
                                学分：<input type="text" value="${UpdateLesson.l_score}"
                                          name="l_score" class="form-control" placeholder="请输入学分">
                                <br> 学时：<input type="text" name="l_time"
                                               value="${UpdateLesson.l_time}" class="form-control"
                                               placeholder="请输入学时"> <br> 所属学院：<select name="c_id" class="form-control">
                                <c:forEach items="${CollegeList}" var="item">
                                    <option value="${item.c_id}">${item.c_name}</option>
                                </c:forEach>
                            </select><br> 是否必修：
                                <div class="chooseSub"><label class="fancy-radio"> <input
                                        <c:if test="${UpdateLesson.l_require}">checked="checked"</c:if>
                                        name="l_require" value="1" type="radio"> <span><i></i>是</span>
                                </label> <label class="fancy-radio"> <input name="l_require"
                                                                            <c:if test="${!UpdateLesson.l_require}">checked="checked"</c:if>
                                                                            value="0" type="radio">
                                    <span><i></i>否</span>
                                </label></div>
                                <br> 描述： <input name="l_describe" class="form-control"
                                                value="${UpdateLesson.l_describe}"></input> <br>
                            </div>
                            <div class="row">
                                <div class="col-md-2"></div>

                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-success btn-block">提交</button>
                                </div>

                                <div class="col-md-4">
                                    <button type="reset" class="btn btn-danger btn-block">重置</button>
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                        </form>
                    </div>
                    <!-- END INPUTS -->
                </div>
            </div>
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
</body>

</html>
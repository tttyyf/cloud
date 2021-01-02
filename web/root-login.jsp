<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="app.jsp" %>

<html>
<head>
    <title>rootLogin</title>

    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <link href="${appPath}/LoginRegister/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="${appPath}/LoginRegister/css/snow.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${appPath}/LoginRegister/css/rootlogin.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="wrapper">
    <!--背景图片-->
    <div id="web_bg">
        <img src="LoginRegister/images/banner.jpg" alt="">
    </div>

    <div class="snow-container">
        <div class="snow foreground"></div>
        <div class="snow foreground layered"></div>
        <div class="snow middleground"></div>
        <div class="snow middleground layered"></div>
        <div class="snow background"></div>
        <div class="snow background layered"></div>
    </div>

    <h1>后台管理员
    </h1>
    <div class="main-agileits">
        <div class="form-w3-agile">
            <h2 class="sub-agileits-w3layouts">Sign Up</h2>
            <form action="/TeachingManagement/ManagerLogin">
                <input type="text" id="name" name="name" placeholder="用户" required=""/>
                <input type="password" id="password" name="password" placeholder="密码" required=""/>
                <a href="#" class="forgot-w3layouts">忘记密码 ?</a>
                <div class="submit-w3l">
                    <input type="submit" id="logBtu" value="登录">
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        var password = $("#password");
        var name = $("#name");

        //username失去焦点时，判断是否为空
        $("#name").blur(function () {
            if ($("#name").val().length > 0) {
            } else {
                alert("用户名不能为空！");
            }
        })

        password.blur(function () {
            if (password.val().length > 0) {
            } else {
                alert("密码不能为空！");
            }
        });

        $("#logBtu").click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/ManagerLoginServlet",
                data: {"name": name.val(), "password": password.val()},
                type: "GET",
                dataType: "json",
                success: function (data) {
                    if (data == 1) {
                        alert("该用户不存在！");
                    } else if (data == 2) {
                        alert("用户名或密码错误！")
                    }
                }
            });
        });
    });
</script>

</body>
</html>
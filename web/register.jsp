<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="app.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <title>register</title>

    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <link href="${appPath}/LoginRegister/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="${appPath}/LoginRegister/css/snow.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${appPath}/LoginRegister/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>

    <script>
        function validate() {
            var password = document.getElementById("password");
            //验证password
            if (password.value == "") {
                alert("密码不能为空！");
                return false;
            } else if (password.value.length != 6) {
                alert("密码长度不符合要求，请输入6位数密码!");
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>
<div class="wrapper">
    <!--背景图片-->
    <div id="web_bg">
        <img src="assets/img/login-bg.jpg" alt="">
    </div>

    <div class="top-buttons-agileinfo">
        <a href="login.jsp">登录</a><a href="register.jsp" class="active">注册</a>
    </div>
    <h1>教学管理系统</h1>
    <div class="main-agileits">
        <div class="form-w3-agile">
            <h2 class="sub-agileits-w3layouts">Register</h2>
            <form action="/TeachingManagement/ManagerRegister" method="get" onsubmit="return validate()">
                <div class="line">
                    <img class="smallImg" src="assets/img/icon3.png"/>
                    <input type="text" id="name" name="name" placeholder="用户名" required=""/>
                </div>
                <div class="line">
                    <img class="smallImg" src="assets/img/icon4.png"/>
                    <input type="password" id="password" name="password" placeholder="密码" required=""/>
                </div>
                <div class="line">
                    <img class="smallImg" src="assets/img/confirmpwd.png"/>
                    <input type="password" id="password1" name="password1" placeholder="确认密码" required=""/>
                </div>

                <div class="user-email">
                    <label for="email">邮箱：</label>
                    <div class="input-email">
                        <input type="text" name="email" id="email"/>
                        <label id="info"> </label>
                    </div>
                </div>

                <div class="submit-w3l">
                    <input id="rbutton" type="submit" value="注册">
                </div>
            </form>
        </div>
    </div>
</div>

<%--原生ajax方法--%>
<%--<script type="text/javascript">--%>
<%--    function validateEmail() {--%>
<%--        //创建XMLHttpRequest对象--%>
<%--        var xhr = new XMLHttpRequest();--%>

<%--        //客房端连接服务器--%>
<%--        //GET参数：--%>
<%--        var email = document.getElementById("email").value;--%>
<%--        var url = '${pageContext.request.contextPath}/ManagerRegisterServlet?email=' + email;--%>
<%--        xhr.open('GET', url, true);--%>

<%--        //发送请求 xhr.send()--%>
<%--        xhr.send();//Get--%>

<%--        //设置回调函数--%>
<%--        xhr.onreadystatechange = function () {--%>
<%--            if (xhr.readyState == 4 && xhr.status == 200) {--%>
<%--                //处理数据，实现局部刷新，js操作DOM--%>
<%--                var data = xhr.responseText;//返回的文本--%>
<%--                $('#info').html(data);--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>

<script type="text/javascript">
    $(function () {
        $("#rbutton").attr("disabled", true);
        var psw = $("#password");
        var psw1 = $("#password1");
        var name = $("#name");

        //username失去焦点时，判断是否为空
        $("#name").blur(function () {
            if (name.val().length <= 0) {
                alert("用户名不能为空！");
                $("#rbutton").attr("disabled", true);
            }
        })

        //判断密码是否一致
        $("#password1").blur(function () {
            if (psw.val().length <= 0 || psw1.val().length <= 0) {
                alert("密码不能为空");
                $("#rbutton").attr("disabled", true);
            } else if (psw.val() != psw1.val()) {
                alert("密码不一致");
                $("#rbutton").attr("disabled", true);
            }
        });
    });
</script>

<script>
    $("#email").blur(function () {
        $("#rbutton").attr("disabled", true);
        var email = document.getElementById("email").value;
        console.log(email);
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/ManagerRegisterServlet",
            data: {email: email},
            dataType: 'json',
            success: function (data) {
                if (data == 1) {
                    alert("邮箱已被注册！");
                    $("#rbutton").attr("disabled", true);
                } else if (data == 2) {
                    alert("邮箱不合法！");
                    $("#rbutton").attr("disabled", true);
                }else{
                    $("#rbutton").removeAttr("disabled");
                }
            }
        });
    });
</script>
</body>
</html>
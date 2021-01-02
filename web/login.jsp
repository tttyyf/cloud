<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="app.jsp" %>

<html>
<head>
    <title>login</title>

    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76"
          href="${appPath}/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96"
          href="${appPath}/assets/img/favicon.png">
    <link href="${appPath}/LoginRegister/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="${appPath}/LoginRegister/css/snow.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${appPath}/LoginRegister/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="wrapper">
    <!--背景图片-->
    <div id="web_bg">
        <img src="assets/img/login-bg.jpg" alt="">
    </div>

    <div class="top-buttons-agileinfo">
        <a href="login.jsp" class="active">登录</a><a href="register.jsp">注册</a>
    </div>
    <h1>教学管理系统
    </h1>
    <div class="main-agileits">
        <div class="form-w3-agile">
            <h2 class="sub-agileits-w3layouts">Sign Up</h2>
            <form action="${pageContext.request.contextPath}/TeacherLogin">
                <div class="line">
                    <img class="smallImg" src="assets/img/icon3.png"/>
                    <input type="text" id="name" name="name" placeholder="用户" required=""/>
                </div>
                <div class="line">
                    <img class="smallImg" src="assets/img/icon4.png"/>
                    <input type="password" id="password" name="password" placeholder="密码" required=""/>
                </div>

                <a href="#" class="forgot-w3layouts">忘记密码 ?</a>
                <div class="submit-w3l">
                    <input type="submit" id="logBtu" value="登录">
                </div>
                <p class="p-bottom-w3ls"><a href="register.jsp">点击注册</a>如果你没有一个帐户
                </p>
                <p class="login-count"></br></br>在线人数：${applicationScope.count}</p>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript">
</script>
<script>
    $(document).ready(function () {
        var password = $("#password");
        var name = $("#name");

        //username失去焦点时，判断是否为空
        $("#name").blur(function () {
            if ($("#name").val().length <= 0) {
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
                url: "${pageContext.request.contextPath}/TeacherLoginServlet",
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
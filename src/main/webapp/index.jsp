<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">

    <title>登录</title>
    <style>
        body {
            background: url("assets/img/bg.jpg") no-repeat fixed;
            background-size: cover;
            overflow: hidden;
        }

    </style>
</head>
<body>


<div class="container myBox">
    <div class="col-xs-8 col-xs-offset-4 col-sm-6 col-sm-offset-3 form-box">
        <div class="form-top">
            <div class="form-top-left">
                <h3>小标交友</h3>
                <p>请输入您的信息:</p>
            </div>
            <div class="form-top-right">
                <i class="fa fa-key"></i>
            </div>
        </div>
        <div class="form-bottom">
            <form role="form" action="<c:url value="/login/login"/>" method="post" class="login-form">

                <!--上面的输入框尽可能不需要外边距，使用row解决-->
                <div class="row">
                    <div class="form-group">
                        <label class="sr-only" for="form-username">Username</label>
                        <input type="text" name="form-username" placeholder="用户名" class="form-username form-control"
                               id="form-username">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="form-password">Password</label>
                        <input type="password" name="form-password" placeholder="密码" class="form-password form-control"
                               id="form-password">
                    </div>
                </div>
                <!--上面的输入框尽可能不需要外边距，使用row包裹起来解决-->

                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="remem-me"> 记住我
                    </label>
                </div>
                <button type="submit" class="btn">登录</button>

                <div class="row">
                    <div style="padding: 10px 25px">
                        <div style="display: inline-block;float: left" class="text-left"><a href="forget.jsp">忘记密码?</a></div>
                        <div style="display: inline-block;float: right" class="text-right"><a href="register.jsp">没有账号?</a></div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


<!-- Javascript -->
<script src="assets/js/jquery-1.11.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.backstretch.min.js"></script>
<script src="assets/js/scripts.js"></script>
</body>
</html>
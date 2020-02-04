<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="../assets/vendor/bootstrap/css/bootstrap.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="../assets/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Custom Font Icons CSS-->
    <link rel="stylesheet" href="../assets/css/font.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="../assets/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="../assets/css/custom.css">

    <link rel="stylesheet" href="../assets/css/layer.css">
    <title>发布会议</title>
    <style>
        /*这个是这页面特有的，需要单独写出来*/
        .myTitle > input{
            float: right;
            margin-left: 15px;
        }

    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <nav id="sidebar">
        <!-- Sidebar Header-->
        <div class="sidebar-header d-flex align-items-center">
            <div id="avatar" class="avatar"><img src="../assets/img/avatar-6.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
                <h1 class="h5">${sessionScope.userInfo.username}</h1>
                <p>${sessionScope.userInfo.deptName}</p>
            </div>
        </div>
        <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
        <ul class="list-unstyled">
            <li><a href="home.jsp"> <i class="icon-home"></i>主页 </a></li>
            <li><a href="#userDropdown"  data-toggle="collapse" > <i class="icon-windows"></i>用户列表</a>
                <ul id="userDropdown" class="collapse list-unstyled">
                    <li><a href="<c:url value="/user/page"/>">查看用户</a></li>
                    <li><a href="<c:url value="/myUser/page"/>">我关注的用户</a></li>
                    <li><a href="<c:url value="/article/showArticle"/>">发布文章</a></li>
                    <li><a href="<c:url value="/articleCol/showFavedArticles"/>">我的收藏</a></li>
                </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

            <li><a href="#depDropdown"  data-toggle="collapse" aria-expanded="true"> <i class="icon-windows2"></i>部门列表</a>
                <ul id="depDropdown" class="collapse show ">
                    <li><a href="<c:url value="/department/showMembers"/>">全部部门</a></li>
                    <li><a href="<c:url value="/meeting/showMeeting"/>">会议系统</a></li>
                </ul>
            </li>

        </ul>

    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">发布会议</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="block-body">
                <form>
                    <div class="form-group">
                        <label class="form-control-label">标题</label>
                        <input type="text" placeholder="会议标题" class="form-control" name="title">
                    </div>
                    <!--选择部门-->
                    <div class="form-group">
                        <select name="dept" class="form-control">
                            <option>请选择部门</option>
                            <c:forEach items="${sessionScope.departments}" var="department">
                                <option value="${department.id}">${department.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!--开始时间-->
                    <div class="form-group">
                        <label class="form-control-label">开始时间</label>
                        <input type="time" class="form-control" name="time">

                    </div>
                    <div class="form-group">
                        <label class="form-control-label">会议内容</label>
                        <textarea class="form-control" rows="5" name="content"></textarea>
                    </div>
                    <div class="text-center form-group">
                        <input type="button" value="发布" class="btn btn-primary submit">
                        <input type="reset" value="清空" class="btn btn-info">
                    </div>
                </form>
            </div>
        </section>

        <footer class="footer">
            <div class="footer__block block no-margin-bottom">
                <div class="container-fluid text-center">
                    <p class="no-margin-bottom">Copyright &copy; 2019.Company <a href="#" >东方标准</a> </p>
                </div>
            </div>
        </footer>
    </div>
</div>

<!-- JavaScript files-->
<script src="../assets/vendor/jquery/jquery.min.js"></script>
<script src="../assets/vendor/popper.js/umd/popper.min.js"> </script>
<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../assets/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="../assets/vendor/chart.js/Chart.min.js"></script>
<script src="../assets/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../assets/js/charts-home.js"></script>
<script src="../assets/js/front.js"></script>
<script src="../assets/js/custom.js"></script>
<script src="../assets/js/layer.js"></script>

<script>
    $(".submit").click(function () {
        let $title = $("[name='title']");
        let $dept = $("[name='dept']");
        let $time = $("[name='time']");
        let $content = $("[name='content']");
        var content = $content.val();
        var time = $time.val();
        var dept = $dept.val();
        var title = $title.val();
        $.post("/meeting/postMeeting", {
            title:title,
            content:content,
            dept:dept,
            time:time
        }, function (data) {
            if (data === "success")
                layer.msg("發佈成功");
            else
                layer.msg("發佈失敗");
        })
        $dept.val("请选择部门");
        $title.val("");
        $time.val("");
        $content.val("");
    })
</script>
</body>
</html>
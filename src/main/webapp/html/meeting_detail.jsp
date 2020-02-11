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
    <title>会议详情</title>
    <style>

        .myTitle > input {
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
            <div id="avatar" class="avatar">
                <c:if test="${sessionScope.userInfo.pic == null}">
                    <img src="../assets/img/avatar-6.jpg" alt="..."
                         class="img-fluid rounded-circle">
                </c:if>
                <c:if test="${sessionScope.userInfo.pic != null}">
                    <img src="${sessionScope.userInfo.pic}" alt="..."
                         class="img-fluid rounded-circle">
                </c:if>
            </div>
            <div class="title">
                <h1 class="h5">${sessionScope.userInfo.username}</h1>
                <p>${sessionScope.userInfo.deptName}</p>
            </div>
        </div>
        <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
        <ul class="list-unstyled">
            <li><a href="home.jsp"> <i class="icon-home"></i>主页 </a></li>
            <li><a href="#userDropdown" data-toggle="collapse"> <i class="icon-windows"></i>用户列表</a>
                <ul id="userDropdown" class="collapse list-unstyled">
                    <li><a href="<c:url value="/user/page"/>">查看用户</a></li>
                    <li><a href="<c:url value="/myUser/page"/>">我关注的用户</a></li>
                    <li><a href="<c:url value="/article/showArticle"/>">发布文章</a></li>
                    <li><a href="<c:url value="/articleCol/showFavedArticles"/>">我的收藏</a></li>
                </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

            <li><a href="#depDropdown" data-toggle="collapse" aria-expanded="true"> <i
                    class="icon-windows2"></i>部门列表</a>
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
                <h2 class="h5 no-margin-bottom">会议详情</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="myTitle">
                <h3 class="text-center">${sessionScope.conference.title}</h3>
                <c:if test="${sessionScope.conference.status != 2}">
                    <input type="button" value="${buttonVal}" class="btn btn-info attend">
                </c:if>
                <c:if test="${sessionScope.conference.status == 2}">
                    <%--                    <input type="submit" value="取消会议" class="btn btn-block">--%>
                    <input type="submit" value="会议已结束" class="btn btn-light" disabled>
                </c:if>

            </div>

            <div class="myContent">
                <p class="h6"><strong>部门：</strong>${sessionScope.conference.deptName}</p>
                <p class="h6" id="attendance"><strong>应到：</strong>${MembersNum}<span>人</span></p>
                <p class="h6"><strong>实到：</strong>${conJoinNum}<span>人</span></p>
                <p class="h6"><strong>未到：</strong>1<span>人</span></p>
                <textarea style="padding: 2px" disabled="disabled" class="form-control"
                          rows="11">${sessionScope.conference.content}</textarea>


            </div>

            <div class="text-right myList">
                <p>日期：${sessionScope.conference.publishDate}</p>
            </div>
        </section>

        <footer class="footer">
            <div class="footer__block block no-margin-bottom">
                <div class="container-fluid text-center">
                    <p class="no-margin-bottom">Copyright &copy; 2019.Company <a href="#">东方标准</a></p>
                </div>
            </div>
        </footer>
    </div>
</div>

<!-- JavaScript files-->
<script src="../assets/vendor/jquery/jquery.min.js"></script>
<script src="../assets/vendor/popper.js/umd/popper.min.js"></script>
<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../assets/vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="../assets/vendor/chart.js/Chart.min.js"></script>
<script src="../assets/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../assets/js/charts-home.js"></script>
<script src="../assets/js/front.js"></script>
<script src="../assets/js/custom.js"></script>
<script src="../assets/js/layer.js"></script>
<script>
    $(".attend").click(function () {
        if ($(this).val() === "参加会议") {
            $.post("/meeting/attend",
                {
                    mId:${sessionScope.conference.id},
                    status:${sessionScope.conference.status},
                    conJoinId:${conJoinId}
                },
                function (data) {
                    if (data === "success") {
                        layer.msg("已成功報名參加會議");

                    } else
                        layer.msg(data);
                }
            );
            $(this).val("取消参加");
        } else if ($(this).val() === "取消参加") {
            $.post("/meeting/abort",
                {
                    conJoinId:${conJoinId},
                },
                function (data) {
                    if (data === "success") {
                        layer.msg("已成功取消報名");

                    } else {
                        layer.msg(data);
                    }
                }
            );
            $(this).val("参加会议");
        } else if ($(this).val() === "开始会议") {
            $.post("/meeting/changeStatus",
                {
                    mId:${sessionScope.conference.id},
                    status: 1,
                    conJoinId:${conJoinId}
                },
                function (data) {
                    layer.msg("會議開始了")
                }
            );
            $(this).val("结束会议");
        } else {
            $.post("/meeting/changeStatus",
                {
                    mId:${sessionScope.conference.id},
                    status: 2,
                    conJoinId:${conJoinId}
                },
                function (data) {
                    layer.msg("會議結束了")
                }
            );
            $(this).val("会议已结束");
            $(this).attr("disabled", "disabled");
        }
    });
</script>
</body>
</html>
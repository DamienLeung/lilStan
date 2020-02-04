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
    <title>我的用户列表</title>

</head>
<body>
<%@ include file="header.jsp" %>

<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <nav id="sidebar">
        <!-- Sidebar Header-->
        <div class="sidebar-header d-flex align-items-center">
            <div id="avatar" class="avatar"><img src="../assets/img/avatar-6.jpg" alt="..."
                                                 class="img-fluid rounded-circle"></div>
            <div class="title">
                <h1 class="h5">${sessionScope.userInfo.username}</h1>
                <p>${sessionScope.userInfo.deptName}</p>
            </div>
        </div>
        <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
        <ul class="list-unstyled">
            <li><a href="<c:url value="/view/home"/>"> <i class="icon-home"></i>主页 </a></li>
            <li><a href="#userDropdown" data-toggle="collapse" aria-expanded="true"> <i
                    class="icon-windows"></i>用户列表</a>
                <ul id="userDropdown" class="collapse show">
                    <li><a href="<c:url value="/user/page"/>">查看用户</a></li>
                    <li class="active"><a href="<c:url value="/myUser/page"/>">我关注的用户</a></li>
                    <li><a href="<c:url value="/article/showArticle"/>">发布文章</a></li>
                    <li><a href="<c:url value="/articleCol/showFavedArticles"/>">我的收藏</a></li>
                </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

            <li><a href="#depDropdown" data-toggle="collapse"> <i class="icon-windows2"></i>部门列表</a>
                <ul id="depDropdown" class="collapse list-unstyled ">
                    <li><a href="<c:url value="/department/showMembers"/>">全部部门</a></li>
                    <li><a href="meeting.html">会议系统</a></li>
                </ul>
            </li>

        </ul>

    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">我的关注列表</h2>
            </div>
        </div>
        <section class="no-padding-bottom">

            <div class="list-group myList">
                <ul>
                    <c:forEach items="${sessionScope.userList}" var="follower">
                        <li class="list-group-item" id="li${follower.id}">
                            <a href="<c:url value="/user/getUserDetail?id=${follower.uId}"/>">${follower.realName}</a>
                            <div style="float: right" class="text-right">
                                <input type="submit" data-id="${follower.id}" class="btn btn-danger unfollow"
                                       value="取消关注">
                            </div>
                        </li>
                    </c:forEach>


                </ul>
                <nav class="text-center" aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous" id="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach var="index" varStatus="status" begin="${requestScope.startPage}"
                                   end="${requestScope.endPage}" step="1">
                            <li>
                                <a href="<c:url value="/myUser/page?page=${index}"/>">${index}</a>
                            </li>
                        </c:forEach>
                        <li>
                            <a href="#" aria-label="Next" id="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
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
<script src="../assets/js/layer.js"></script>
<script src="../assets/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../assets/js/charts-home.js"></script>
<script src="../assets/js/front.js"></script>
<script src="../assets/js/custom.js"></script>

<script>
    $("#Previous").click(function () {
        var index = $(this).parent().siblings()[0];
        var firstPage = $(index).text();
        console.log(Number(firstPage));
        if (Number(firstPage) < 6) {
            layer.msg("頁碼已經到頂了");
            return;
        } else {
            layer.msg("hello");
            window.location.href = '${pageContext.request.contextPath}/myUser/page?page=' + (Number(firstPage) - 1);
        }

    });

    $("#Next").click(function () {
        var index = $(this).parent().siblings()[1];
        var firstPage = $(index).text();
        console.log(Number(firstPage));
        if (Number(firstPage) + 5 > ${requestScope.maxPage}) {
            layer.msg("頁碼已經到底了");
            return;
        } else {
            layer.msg("hello");
            window.location.href = '${pageContext.request.contextPath}/myUser/page?page=' + Number(Number(firstPage) + 5);
        }
        console.log();

    });

    $(".unfollow").on("click", function () {
        var ufId = $(this).attr("data-id");
        $.post("/myUser/unfollow", {id: ufId}, function (data) {
            if ("success" === data) {
                var className = ".li" + ufId;
                $(className).remove();
                layer.msg("已取消關注");
            } else {
                layer.msg(data);
            }
        })
    });
</script>
</body>
</html>
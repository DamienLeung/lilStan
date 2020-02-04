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
    <title>我的收藏</title>

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
            <li><a href="#userDropdown"  data-toggle="collapse" aria-expanded="true"> <i class="icon-windows"></i>用户列表</a>
                <ul id="userDropdown" class="collapse show">
                    <li><a href="<c:url value="/user/page"/>">查看用户</a></li>
                    <li><a href="<c:url value="/myUser/page"/>">我关注的用户</a></li>
                    <li><a href="<c:url value="/article/showArticle"/>">发布文章</a></li>
                    <li class="active"><a href="<c:url value="/articleCol/showFavedArticles"/>">我的收藏</a></li>
                </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

            <li><a href="#depDropdown"  data-toggle="collapse"> <i class="icon-windows2"></i>部门列表</a>
                <ul id="depDropdown" class="collapse list-unstyled ">
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
                <h2 class="h5 no-margin-bottom">我收藏的文章</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="list-group myFavList">
                <!--搜索文章的条件-->
                <div class="myTitle">
                    <form class="form-inline" action="<c:url value="/articleCol/showFavedArticles"/>">
                        <div class="form-group">
                            <label for="inlineFormInput" class="sr-only">Name</label>
                            <input id="inlineFormInput" type="text" placeholder="按标题名字查找" value="${sessionScope.pattern}" class="mr-sm-3 form-control" name="pattern">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="查询" class="btn btn-primary">
                        </div>
                    </form>

                    <!--<input id = "addArt" type="submit" value="发布文章" class="btn btn-primary">-->

                </div>

                <ul>
                    <c:forEach items="${sessionScope.favArticles}" var="article">
                        <li class="list-group-item">
                            <div style="float: right;">
                                <span><strong>收藏数：</strong>${article.favCount}</span>
                                <span>&nbsp;</span>
                                <span>&nbsp;</span>
                                <span>&nbsp;</span>
                                <span><strong>浏览数：</strong>${article.view}</span>
                            </div>
                            <a href="<c:url value="/article/getArticleDetail?articleId=${article.aId}"/>">${article.title}</a>
                            <p class="h6"><strong>作者：</strong>${article.author}</p>
                            <p class="h6"><strong>时间：</strong>${article.publishDate}</p>
                            <p style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis">${article.content}</p>
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
                        <c:forEach var="index" varStatus="status" begin="${sessionScope.startPage}"
                                   end="${sessionScope.endPage}" step="1">
                            <li>
                                <a href="<c:url value="/articleCol/showFavedArticles?page=${index}&&pattern=${sessionScope.pattern}"/>">${index}</a>
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
    $("#Previous").click(function () {
        var firstPage = '${sessionScope.startPage}';
        var pattern = '${sessionScope.pattern}';
        if (Number(firstPage) < 6) {
            layer.msg("頁碼已經到頂了");
        } else {
            window.location.href = '${pageContext.request.contextPath}/articleCol/showFavedArticles?page=' + (Number(firstPage) - 1) + '&&pattern=' + pattern;
        }

    });

    $("#Next").click(function () {
        var firstPage = '${sessionScope.startPage}';
        var pattern = '${sessionScope.pattern}';

        if (Number(firstPage) + 5 > ${sessionScope.maxPage}) {
            layer.msg("頁碼已經到底了");
        } else {
            window.location.href = '${pageContext.request.contextPath}/articleCol/showFavedArticles?page=' + Number(Number(firstPage) + 5) + '&&pattern=' + pattern;
        }
    });
</script>
</body>
</html>
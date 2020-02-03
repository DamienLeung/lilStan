<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>文章详情</title>
    <style>
        .myList li {
            display: inline-block;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <%@ include file="sidebar.jsp" %>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">文章详情</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="myTitle">
                <h3 class="text-center">${sessionScope.articleDetails.title}</h3>
                <input type="button" value="${sessionScope.buttonVal}" class="btn btn-info" id="subscribe">

            </div>

            <div class="myContent">
                <p class="h6"><strong>发布人：</strong>${sessionScope.articleDetails.author}</p>
                <p class="h6"><strong>创建时间：</strong>${sessionScope.articleDetails.publishDate}</p>
                <p class="h6"><strong>浏览次数：</strong>${sessionScope.articleDetails.view}</p>
                <p class="h6"><strong>收藏次数：</strong>${sessionScope.articleDetails.FavCount}</p>
                <textarea style="padding: 2px" disabled="disabled" class="form-control"
                          rows="11">${sessionScope.articleDetails.content}</textarea>

            </div>

            <div class="myList">
                <p>您关注的小伙伴等人也收藏了该文章</p>
                <ul>
                    <c:forEach var="user" items="${sessionScope.usernames}">
                        <li class="list-group-item">
                            <a href="<c:url value="/user/getUserDetail?id=${user.id}"/>">${user.realName == null ? user.username : user.realName}</a>
                        </li>
                    </c:forEach>
                </ul>
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
<script src="../assets/js/layer.js"></script>
<script src="../assets/js/custom.js"></script>

<script>
    $("#subscribe").click(function () {
        if ($(this).val() === "取消收藏") {
            console.log($(this).val());
            $.post("/article/unfav/",
                {fId: '${sessionScope.fId}'},
                function (data) {
                    if (data === "success") {

                        layer.msg("已取消收藏");
                    } else
                        layer.msg(data);
                });
            $(this).val("收藏");
        } else {
            console.log($(this).val());
            $.post("/article/fav/",
                {articleId: '${sessionScope.articleDetails.id}'},
                function (data) {
                    if (data === "success") {
                        layer.msg("成功收藏");
                    } else
                        layer.msg(data);
                });
            $(this).val("取消收藏");
        }
    })
</script>
</body>
</html>
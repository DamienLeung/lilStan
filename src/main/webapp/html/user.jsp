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
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap-switch.min.css">
    <title>查看用户</title>
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
            <li><a href="home.jsp"> <i class="icon-home"></i>主页 </a></li>
            <li><a href="#userDropdown" data-toggle="collapse" aria-expanded="true"> <i
                    class="icon-windows"></i>用户列表</a>
                <ul id="userDropdown" class="collapse show">
                    <li class="active"><a href="<c:url value="/user/page"/>">查看用户</a></li>
                    <li><a href="my_user.html">我关注的用户</a></li>
                    <li><a href="article.html">发布文章</a></li>
                    <li><a href="article_collect.html">我的收藏</a></li>
                </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

            <li><a href="#depDropdown" data-toggle="collapse"> <i class="icon-windows2"></i>部门列表</a>
                <ul id="depDropdown" class="collapse list-unstyled ">
                    <li><a href="department.html">全部部门</a></li>
                    <li><a href="meeting.html">会议系统</a></li>
                </ul>
            </li>

        </ul>

    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">用户列表</h2>
            </div>
        </div>
        <section class="no-padding-bottom">
            <div class="title">
                <form class="form-inline" action="<c:url value="/user/searchUser"/>">
                    <div class="form-group">
                        <label for="inlineFormInput" class="sr-only">Name</label>
                        <input name="pattern" id="inlineFormInput" type="text" placeholder="按名字查找"
                               value="${requestScope.pattern}" class="mr-sm-3 form-control">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="查询" class="btn btn-primary">
                    </div>
                </form>

            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>简介</th>
                        <th>操作</th>
                        <th>加关注</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${sessionScope.userList}">
                        <tr>
                            <th scope="row">${user.id}</th>
                            <td>${user.realName==null?user.username:user.realName}</td>
                            <td>
                                    <%--if--%>
                                <c:if test="${user.gender==0}">
                                    男
                                </c:if>
                                    <%--else--%>
                                <c:if test="${user.gender==1}">
                                    女
                                </c:if>
                            </td>
                            <td>${user.age}</td>
                            <td>${user.desc}</td>
                            <td>
                                <input type="submit" data-userid="${user.id}" value="详细信息" class="btn btn-xs btn-primary userDetail">
                            </td>

                            <td>
                                <c:if test="${user.ufId == null}">
                                    <input type="checkbox" data-ufid="${user.ufId}" data-uid="${user.id}" value="" class="checkbox-template">
                                </c:if>
                                <c:if test="${user.ufId != null}">
                                    <input checked="checked" type="checkbox"  data-ufid="${user.ufId}" data-uid="${user.id}" value="" class="checkbox-template">
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <nav class="text-center" aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous" id="prePage">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach var="index" varStatus="status" begin="${requestScope.startPage}"
                                   end="${requestScope.endPage}" step="1">
                            <c:if test="${requestScope.pattern == null}">
                                <li><a href="<c:url value="/user/page?page=${index}"/>">${index}</a></li>
                            </c:if>
                            <c:if test="${requestScope.pattern != null}">
                                <li>
                                    <a href="<c:url value="/user/searchUser?pattern=${requestScope.pattern}&&page=${index}"/>">${index}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <li>
                            <a href="#" aria-label="Next" id="nextPage">
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
<script src="../assets/bootstrap/js/bootstrap-switch.min.js"></script>
<script src="../assets/vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="../assets/vendor/chart.js/Chart.min.js"></script>
<script src="../assets/js/layer.js"></script>
<script src="../assets/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../assets/js/charts-home.js"></script>
<script src="../assets/js/front.js"></script>
<script src="../assets/js/custom.js"></script>


<script>
    $("#prePage").click(function () {
        var index = $(this).parent().siblings()[0];
        var firstPage = $(index.firstChild).html();
        var search = '${requestScope.pattern}';
        if ('' === search) {
            if (Number(firstPage) < 6) {
                layer.msg("頁碼已經到頂了");
                return;
            } else {
                window.location.href = '${pageContext.request.contextPath}/user/page?page=' + (Number(firstPage) - 1);
            }
        } else {
            firstPage = index.innerText;
            if (Number(firstPage) < 6) {
                layer.msg("頁碼已經到頂了");
                return;
            } else {
                window.location.href = '${pageContext.request.contextPath}/user/searchUser?pattern=${requestScope.pattern}' + '&&page=' + (Number(firstPage) - 1);
            }
        }
    });

    $("#nextPage").click(function () {
        var index = $(this).parent().siblings()[1];
        var firstPage = $(index.firstChild).html();
        var search = '${requestScope.pattern}';
        if ('' === search) {
            if (Number(firstPage) + 5 > ${requestScope.maxPage}) {
                layer.msg("頁碼已經到底了");
                return;
            } else {
                window.location.href = '${pageContext.request.contextPath}/user/page?page=' + Number(Number(firstPage) + 5);
            }
            console.log();
        } else {
            firstPage = index.innerText;
            if (Number(firstPage) + 5 > ${requestScope.maxPage}) {
                layer.msg("頁碼已經到底了");
                return;
            } else {
                window.location.href = '${pageContext.request.contextPath}/user/searchUser?pattern=${requestScope.pattern}' + '&&page=' + Number(Number(firstPage) + 5);
            }
        }

    })

    $(".table").find("input[type='checkbox]").click(function () {
        if ($(this).prop("checked")) {
            var uId = $(this).attr("data-id");
            var id = ${sessionScope.userId};
            if (uId === id) {
                $(this).click();
                layer.msg("不能關注自己");
            } else {
                $.post("/user/follow", {uId: uId}, function (data) {
                    if ("success" === data) {
                        layer.msg("關注成功");
                    } else {
                        layer.msg(data);
                    }
                });
            } else {
                var ufId = $(this).attr("data-ufId");
                $.post("/user/unfollow", {ufId: ufId}, function(data) {
                    if ("success" === data) {
                        layer.msg("已取消關注");
                    }
                })
            }

        }
    })
</script>
</body>
</html>
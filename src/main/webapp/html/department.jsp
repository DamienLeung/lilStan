<%@ page import="dfbz.com.service.DepartmentService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
    <title>部门</title>

</head>
<body>

<%@ include file="header.jsp" %>

<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <nav id="sidebar">
        <!-- Sidebar Header-->
        <div class="sidebar-header d-flex align-items-center">
            <div id="avatar" class="avatar"><img src="../assets/img/avatar-6.jpg" alt="..." class="img-fluid rounded-circle">
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

            <li><a href="#depDropdown" data-toggle="collapse" aria-expanded="true"> <i class="icon-windows2"></i>部门列表</a>
                <ul id="depDropdown" class="collapse show ">
                    <li class="active"><a href="<c:url value="/department/showMembers"/>">全部部门</a></li>
                    <li><a href="<c:url value="/meeting/showMeeting"/>">会议系统</a></li>
                </ul>
            </li>

        </ul>

    </nav>
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">全部部门</h2>
            </div>
        </div>
        <section class="no-padding-bottom">

            <div class="list-group myDepList">
                <!--                <ul>
                                    <li class="list-group-item">
                                        <a href="#">研发部</a>
                                        <span>&nbsp;</span>
                                        <span>6</span>人
                                    </li>
                                    <li class="list-group-item">
                                        <a href="#">推广部</a>
                                        <span>&nbsp;</span>
                                        <span>8</span>人
                                    </li>
                                </ul>
                 -->
                <ul>
                    <%
                        DepartmentService service = new DepartmentService();
                        List<Map<String, Object>> departments = service.getDepartments();
                        String userId = request.getSession().getAttribute("userId").toString();
                        for (Map<String, Object>department:
                             departments) {
                    %>
                        <li class="list-group-item">
                            <a href="#"><%=department.get("name")%></a>
                            <span>&nbsp;</span>
                            <span><%=department.get("amount")%></span>人
                            <%
                                List<Map<String, Object>> members = service.getMembers(Integer.parseInt(department.get("id").toString()));
                            %>
                            <div class="list-group myGroup isHidden">
                                <ul>
                                    <%
                                        for (Map<String, Object> member:
                                             members) {
                                            if (member.get("realName") == null)
                                                member.put("realName", member.get("member"));
                                            String url;
                                            if (userId.equals(member.get("id")))
                                                url = request.getContextPath() + "/userLook/showDetail";
                                            else
                                                url = request.getContextPath() + "/user/getUserDetail?id=" + member.get("id");
                                    %>
                                    <li class="list-group-item">
                                        <a href="<%=url%>"><%=member.get("realName")%></a>
                                    </li>
                                    <%
                                        }
                                    %>
                                </ul>
                            </div>
                        </li>
                    <%
                        }
                    %>

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
<script src="../assets/js/custom.js"></script>
</body>
</html>
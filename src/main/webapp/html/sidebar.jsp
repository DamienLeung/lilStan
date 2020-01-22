<%--
  Created by IntelliJ IDEA.
  User: ryo_h
  Date: 20/01/2020
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
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
                <li class="active"><a href="<c:url value="/user/page"/>">查看用户</a></li>
                <li><a href="my_user.jsp">我关注的用户</a></li>
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
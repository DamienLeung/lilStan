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
        <li><a href="<c:url value="/view/home"/>"> <i class="icon-home"></i>主页 </a></li>
        <li><a href="#userDropdown" data-toggle="collapse" aria-expanded="true"> <i
                class="icon-windows"></i>用户列表</a>
            <ul id="userDropdown" class="collapse show">
                <li><a href="<c:url value="/user/page"/>">查看用户</a></li>
                <li><a href="<c:url value="/myUser/page"/>">我关注的用户</a></li>
                <li><a href="<c:url value="/article/showArticle"/>">发布文章</a></li>
                <li><a href="<c:url value="/articleCol/showFavedArticles"/>">我的收藏</a></li>
            </ul>
        </li>
        <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

        <li><a href="#depDropdown" data-toggle="collapse"> <i class="icon-windows2"></i>部门列表</a>
            <ul id="depDropdown" class="collapse list-unstyled ">
                <li><a href="<c:url value="/department/showMembers"/>">全部部门</a></li>
                <li><a href="<c:url value="/meeting/showMeeting"/>">会议系统</a></li>
            </ul>
        </li>

    </ul>

</nav>


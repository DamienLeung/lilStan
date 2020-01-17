<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FTKJ
  Date: 16/01/2020
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<header class="header">
    <nav class="navbar navbar-expand-lg">
        <div class="search-panel">
            <div class="search-inner d-flex align-items-center justify-content-center">
                <div class="close-btn">Close <i class="fa fa-close"></i></div>
                <form id="searchForm" action="#">
                    <div class="form-group">
                        <input type="search" name="search" placeholder="What are you searching for...">
                        <button type="submit" class="submit">Search</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="container-fluid d-flex align-items-center justify-content-between">
            <div class="navbar-header">
                <!-- Navbar Header--><a href="<c:url value="/user/home"/>" class="navbar-brand">
                <div class="brand-text brand-big visible text-uppercase"><strong class="text-primary">小标</strong><strong>交友</strong></div>
                <div class="brand-text brand-sm"><strong class="text-primary">X</strong><strong>B</strong></div></a>
                <!-- Sidebar Toggle Btn-->
                <button class="sidebar-toggle"><i class="fa fa-long-arrow-left"></i></button>
            </div>
            <div class="right-menu list-inline no-margin-bottom">
                <!-- Log out -->
                <div class="list-inline-item logout">
                    <a id="logout" href="<c:url value="/login/logout"/>" class="nav-link"><span class="d-none d-sm-inline">Logout </span></a>
                </div>
            </div>
        </div>
    </nav>
</header>
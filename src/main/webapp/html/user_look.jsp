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
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap-select.min.css">
    <title>个人中心</title>

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
            <li><a href="#userDropdown"  data-toggle="collapse"> <i class="icon-windows"></i>用户列表</a>
                <ul id="userDropdown" class="collapse list-unstyled">
                    <li><a href="<c:url value="/user/page"/>">查看用户</a></li>
                    <li><a href="<c:url value="/myUser/page"/>">我关注的用户</a></li>
                    <li><a href="article.html">发布文章</a></li>
                    <li><a href="article_collect.html">我的收藏</a></li>
                </ul>
            </li>
            <!--<li><a href="login.html"> <i class="icon-logout"></i>Login page </a></li>-->

            <li><a href="#depDropdown"  data-toggle="collapse"> <i class="icon-windows2"></i>部门列表</a>
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
                <h2 class="h5 no-margin-bottom">个人中心</h2>
            </div>
        </div>
        <section class="no-padding-bottom">
            <!-- Form Elements -->
            <div class="col-lg-12">
                <div class="block">
                    <div class="title"><strong>我的头像</strong></div>
                    <div class="avatar"><img src="../assets/img/avatar-6.jpg" alt="加载中..." style="width: 150px;height: 150px" class="img-thumbnail rounded-circle"></div>
                    <div style="margin-top: 15px" class="text-left">
                        <!-- 用于展示上传文件名的表单 -->
                        <input id="showname" onclick="makeThisfile()" type="button" class="btn btn-primary" value="更换头像">
                        <!-- 真正的文件上传表单 -->
                        <input name="realFile" type="file" id="myFile" style="display: none" />
                    </div>
                    <div class="title">
                        <br>
                        <p class="h5"><strong>关注数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>52</span></p>
                        <br>
                        <p class="h5"><strong>被看数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>1168</span></p>
                        <br>
                    </div>
                    <div class="title"><strong>我的数据</strong></div>
                    <div class="block-body">
                        <form class="form-horizontal">
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">真实姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" value="东方标准" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">所属部门</label>
                                <div class="col-sm-9">
                                    <select class="selectpicker" data-live-search="true">
                                        <option>研发部</option>
                                        <option>销售部</option>
                                        <option>行政部</option>
                                        <option>财务部</option>
                                        <option>总裁办公室</option>
                                    </select>
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">电话</label>
                                <div class="col-sm-9">
                                    <input type="text" value="020-xxxxxxxx" name="password" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">年龄</label>
                                <div class="col-sm-9">
                                    <input type="text" placeholder="20" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">性别 </label>
                                <div class="col-sm-9">

                                    <div class="i-checks">
                                        <input id="radioCustom1" type="radio" value="1" name="sex" class="radio-template">
                                        <label for="radioCustom1">男</label>
                                        <span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
                                        <input id="radioCustom2" type="radio" value="0" name="sex" class="radio-template">
                                        <label for="radioCustom2">女</label>
                                    </div>
                                </div>


                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">注册时间</label>
                                <div class="col-sm-9">
                                    <input type="text" disabled="" placeholder="2019-10-30 09:30:00" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">登录时间</label>
                                <div class="col-sm-9">
                                    <input type="text" disabled="" placeholder="2019-10-30 19:30:00" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>

                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">是否私密 <br><small class="text-primary">默认为否，可以不设置</small></label>
                                <div class="col-sm-9">
                                    <div class="i-checks">
                                        <input id="checkboxCustom1" type="checkbox" value="" class="checkbox-template">
                                        <label for="checkboxCustom1">是否私密</label>
                                    </div>

                                </div>


                            </div>
                            <div class="line"></div>
                            <div class="text-center">
                                <input type="submit" class="btn btn-primary" value="保存">
                            </div>
                        </form>
                    </div>
                </div>
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
<script src="../assets/bootstrap/js/bootstrap-select.min.js"></script>
<script src="../assets/vendor/popper.js/umd/popper.min.js"> </script>
<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../assets/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="../assets/vendor/chart.js/Chart.min.js"></script>
<script src="../assets/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../assets/js/charts-home.js"></script>
<script src="../assets/js/front.js"></script>
<script src="../assets/js/custom.js"></script>

<script>

</script>
</body>
</html>
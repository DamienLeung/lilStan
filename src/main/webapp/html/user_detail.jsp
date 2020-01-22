<%@ page pageEncoding="UTF-8" %>
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
    <title>用户详情</title>

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
                <h2 class="h5 no-margin-bottom">用户详情</h2>
            </div>
        </div>
        <section class="no-padding-bottom">
            <!-- Form Elements -->
            <div class="col-lg-12">
                <div class="block">
                    <div class="title"><strong>TA的头像</strong></div>
                    <c:if test="${sessionScope.userDetail.pic == null}">
                        <div class="avatar"><img src="../assets/img/avatar-6.jpg" alt="加载中..." style="width: 150px;height: 150px" class="img-thumbnail rounded-circle"></div>
                    </c:if>
                    <c:if test="${sessionScope.userDetail.pic != null}">
                        <div class="avatar"><img src="${sessionScope.userDetail.pic}" alt="加载中..." style="width: 150px;height: 150px" class="img-thumbnail rounded-circle"></div>
                    </c:if>
                    <!--<div style="margin-top: 15px" class="text-left">-->
                        <!--&lt;!&ndash; 用于展示上传文件名的表单 &ndash;&gt;-->
                        <!--<input id="showname" onclick="makeThisfile()" type="button" class="btn btn-primary" value="更换头像">-->
                        <!--&lt;!&ndash; 真正的文件上传表单 &ndash;&gt;-->
                        <!--<input name="realFile" type="file" id="myFile" style="display: none" />-->
                    <!--</div>-->
                    <div class="title">
                        <br>
                        <p class="h5"><strong>粉丝数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>${sessionScope.userDetail.fansCount}</span></p>
                        <br>
                        <p class="h5"><strong>被看数：</strong><span>&nbsp;</span><span>&nbsp;</span><span>${sessionScope.userDetail.look}</span></p>
                        <br>
                    </div>
                    <div class="title"><strong>TA的数据</strong></div>
                    <div class="block-body">
                        <form class="form-horizontal">
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">真实姓名</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="${sessionScope.userDetail.realName}" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">所属部门</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="${sessionScope.userDetail.name}" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">电话</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="${sessionScope.userDetail.phone}" name="password" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">年龄</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" value="${sessionScope.userDetail.age}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">性别</label>
                                <div class="col-sm-9">

                                    <div class="i-checks">
                                        <c:if test="${sessionScope.userDetail.gender == 0}">
                                            <input disabled="disabled" id="radioCustom1" type="radio" value="1" name="sex" class="radio-template" checked>
                                            <label for="radioCustom1">男</label>
                                            <span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
                                            <input disabled="disabled" id="radioCustom2" type="radio" value="0" name="sex" class="radio-template">
                                            <label for="radioCustom2">女</label>
                                        </c:if>
                                        <c:if test="${sessionScope.userDetail.gender == 1}">
                                            <input disabled="disabled" id="radioCustom1" type="radio" value="1" name="sex" class="radio-template">
                                            <label for="radioCustom1">男</label>
                                            <span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
                                            <input disabled="disabled" id="radioCustom2" type="radio" value="0" name="sex" class="radio-template" checked>
                                            <label for="radioCustom2">女</label>
                                        </c:if>

                                    </div>
                                </div>


                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">注册时间</label>
                                <div class="col-sm-9">
                                    <input type="text" disabled="" value="${sessionScope.userDetail.regTime}" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>
                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">登录时间</label>
                                <div class="col-sm-9">
                                    <input type="text" disabled="" value="${sessionScope.userDetail.loginTime}" class="form-control">
                                </div>
                            </div>
                            <div class="line"></div>

                            <div class="form-group row">
                                <label class="col-sm-3 form-control-label">是否私密 <br><small class="text-primary">默认为否，可以不设置</small></label>
                                <div class="col-sm-9">
                                    <div class="i-checks">
                                        <input disabled="disabled" id="checkboxCustom1" type="checkbox" value="" class="checkbox-template">
                                        <label for="checkboxCustom1">是否私密</label>
                                    </div>

                                </div>


                            </div>
                            <div class="line"></div>
                            <!--<div class="text-center">-->
                                <!--<input type="submit" class="btn btn-primary" value="保存">-->
                            <!--</div>-->
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
<script src="../assets/vendor/popper.js/umd/popper.min.js"> </script>
<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../assets/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="../assets/vendor/chart.js/Chart.min.js"></script>
<script src="../assets/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="../assets/js/charts-home.js"></script>
<script src="../assets/js/front.js"></script>
<script src="../assets/js/custom.js"></script>
</body>
</html>
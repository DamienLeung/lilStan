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
    <title>添加文章</title>

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
                <h2 class="h5 no-margin-bottom">添加文章</h2>
            </div>
        </div>

        <section class="no-padding-bottom">

            <div class="block-body">
                <form>
                    <div class="form-group">
                        <label class="form-control-label">标题</label>
                        <input type="text" placeholder="文章标题" class="form-control" name="title">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label">文章内容</label>
                        <textarea class="form-control" rows="5" name="content"></textarea>
                    </div>
                    <div class="text-center form-group">
                        <input type="button" value="发布" class="btn btn-primary submit">
                    </div>
                </form>
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

<script>
    $(".submit").on("click", function () {
        var title = $("[name='title']").val();
        var content = $("[name='content']").val();
        $.post("/article/sendArticle", {
            title: title,
            content: content
        }, function (data) {
            if ("success" === data) {
                console.log(1);
                layer.msg("發佈成功");
                $("[name='title']").val("");
                $("[name='content']").val("");
            } else {
                console.log(2);
                layer.msg(data);
            }
        })
    });
</script>
</body>
</html>
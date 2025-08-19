<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>注册用户</title>
    <link rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $('.menux p').click(function(){
                $(this).siblings('ul').slideUp(200);
                $(this).next('ul').slideToggle(200);
            });
            $('.menux p:first').trigger("click");

            $('.menux ul a').click(function(){
                $('iframe').attr("src",$(this).attr("url"));
                $('.menu_title').html($(this).attr("title"));
            });
        })
    </script>
</head>
<body>
<div class="header">
    <div class="logo">房屋管理系统</div>
</div>
<div class="left">
    <div class="title">
        <i class="fa fa-home"></i>
        注册用户
    </div>
    <div class="menux">
        <p>
            <i class="fa fa-info-circle"></i>
            <i class="fa fa-angle-right point"></i>
            注册用户类型
        </p>
        <ul>
            <li>
                <a  href="javascript:void(0);" url="page/Owner_users/register.jsp"  title="注册房主">
                    <i class="fa fa-caret-right"></i>
                    注册房主
                </a>
            </li>
            <li>
                <a  href="javascript:void(0);" url="page/Tenant_users/register.jsp"  title="注册租赁者">
                    <i class="fa fa-caret-right"></i>
                    注册租赁者
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="main">
    <div class="location">
        <i class="fa fa-home"></i>
        <span class="menu_title"></span>
    </div>
    <iframe src="main.jsp"  width="100%" height="90%" name="mainFrame"  id="mainFrame" frameborder="0px"></iframe>
</div>
</body>
</html>
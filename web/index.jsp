<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>系统首页</title>
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

			$('.logout').click(function(){
				if(confirm("确定要退出吗？")){
					window.location.href = "logout";
				}
			});

		})
	</script>
</head>
<body>
<div class="header">
	<div class="logo">房屋管理系统</div>
	<div class="user">
		<i class="fa fa-caret-down point"></i>
		<i class="fa fa-user"></i>
		${user.userName}
		<ul>
			<li><a  target="mainFrame" href="info.jsp">个人信息</a></li>
			<li><a  href="javascript:void(0)" class="logout">退出登录</a></li>
		</ul>
	</div>
</div>
<div class="left">
	<div class="title">
		<i class="fa fa-home"></i>
		系统功能
	</div>
	<div class="menux">
		<c:if test="${type == 0}">
			<p>
				<i class="fa fa-info-circle"></i>
				<i class="fa fa-angle-right point"></i>
				房屋管理
			</p>
			<ul>
				<li>
					<a  href="javascript:void(0);" url="http://localhost:8080/untitled1_war_exploded/owner_users?method=listHouse"  title="已注册房屋">
						<i class="fa fa-caret-right"></i>
						已注册房屋
					</a>
				</li>
				<li>
					<a  href="javascript:void(0);" url="page/Owner_users/addHouse.jsp"  title="添加房屋">
						<i class="fa fa-caret-right"></i>
						添加房屋
					</a>
				</li>
			</ul>
			<p>
				<i class="fa fa-info-circle"></i>
				<i class="fa fa-angle-right point"></i>
				租房论坛
			</p>
			<ul>
				<li>
					<a  href="javascript:void(0)" url="http://localhost:8080/untitled1_war_exploded/forum?method=getForum"  title="查看论坛">
						<i class="fa fa-caret-right"></i>
						查看论坛
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)" url="page/leaveMessage.jsp"  title="留言">
						<i class="fa fa-caret-right"></i>
						留言
					</a>
				</li>
			</ul>
		</c:if>
		<c:if test="${type == 1}">
			<p>
				<i class="fa fa-info-circle"></i>
				<i class="fa fa-angle-right point"></i>
				租 房
			</p>
			<ul>
				<li>
					<a  href="javascript:void(0)" url="http://localhost:8080/untitled1_war_exploded/tenant_users?method=getVacantHouse"  title="查看房屋">
						<i class="fa fa-caret-right"></i>
						查看房屋
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)" url="http://localhost:8080/untitled1_war_exploded/tenant_users?method=getSeenHouse"  title="已看房屋">
						<i class="fa fa-caret-right"></i>
						已看房屋
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)" url="http://localhost:8080/untitled1_war_exploded/tenant_users?method=getRentedHouse"  title="已租房屋">
						<i class="fa fa-caret-right"></i>
						已租房屋
					</a>
				</li>
			</ul>
			<p>
				<i class="fa fa-info-circle"></i>
				<i class="fa fa-angle-right point"></i>
				租房论坛
			</p>
			<ul>
				<li>
					<a  href="javascript:void(0)" url="http://localhost:8080/untitled1_war_exploded/forum?method=getForum"  title="查看论坛">
						<i class="fa fa-caret-right"></i>
						查看论坛
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)" url="page/leaveMessage.jsp"  title="留言">
						<i class="fa fa-caret-right"></i>
						留言
					</a>
				</li>
			</ul>
		</c:if>
	</div>
</div>
<div class="main">
	<div class="location">
		<i class="fa fa-home"></i>
		<span class="menu_title"></span>
	</div>
	<iframe src="main.jsp"  scrolling="no" width="100%" height="90%" name="mainFrame"  id="mainFrame" frameborder="0px"></iframe>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>列表</title>
		<link rel="stylesheet"  href="${basePath}static/css/styles.css" />
		<link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
		<script>
			$(function(){
				$('.remove').click(function () {
					if(confirm("确定要删除吗？")){
						window.location.href="http://localhost:8080/untitled1_war_exploded/owner_users?method=listHouse";
					}
				})
			})
		</script>
	</head>
	<body>
		<table class="tablelist">
			<thead>
				<tr>
					<th>房屋ID</th>
					<th>地址</th>
					<th>房型</th>
					<th>容量</th>
					<th>租金</th>
					<th>状态</th>
					<th width="120px">操作</th>
				</tr>
			</thead>
			<c:forEach items="${pageInfo.list}" var="house">
			<tr>
				<td>${house.HID}</td>
				<td>${house.HAddress}</td>
				<td>${house.layout}</td>
				<td>${house.capacity}</td>
				<td>${house.rent}</td>
				<c:if test="${house.con==false}"><td>未出租</td></c:if>
				<c:if test="${house.con==true}"><td>已出租</td></c:if>
				<td>
					<button class="edit" type="button" onclick="window.location.href='http://localhost:8080/untitled1_war_exploded/owner_users?method=editHouse&HID=${house.HID}'">
						<i class="fa fa-edit"></i>
						修改
					</button>
					<button class="remove" type="button" onclick="window.location.href='http://localhost:8080/untitled1_war_exploded/owner_users?method=deleteHouse&HID=${house.HID}'">
						<i class="fa fa-remove"></i>
						删除
					</button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%@include file="../include/page.jsp"%>
		</form>
	</body>
</html>

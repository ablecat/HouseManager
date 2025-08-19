<%@ page import="entity.Owner_users" %>
<%@ page import="entity.Tenant_users" %>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<link  rel="stylesheet"  href="static/css/styles.css" />
		<link rel="stylesheet"  href="static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
		<script  src="static/js/jquery.min.js" type="text/javascript"></script>
		<script  src="static/js/jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
		<script  src="static/js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
</head>
<body style="height: 100%; margin: 0">
<div class="add">
	<table class="tablelist" style="width: 50%;margin-left:50px">
		<c:if test="${type == 0}">
			<%Owner_users owner_users = (Owner_users) session.getAttribute("user");%>
			<tr>
				<td width="120px">身份</td>
				<td>房主</td>
			</tr>
			<tr>
				<td width="120px">用户id</td>
				<td><%=owner_users.getOID()%></td>
			</tr>
			<tr>
				<td width="120px">用户名</td>
				<td><%=owner_users.getUserName()%></td>
			</tr>
			<tr>
				<td width="120px">密码</td>
				<td><%=owner_users.getPassword()%></td>
			</tr>
			<tr>
				<td width="120px">姓名</td>
				<td><%=owner_users.getOName()%></td>
			</tr>
			<tr>
				<td width="120px">地址</td>
				<td><%=owner_users.getOAddress()%></td>
			</tr>
			<tr>
				<td width="120px">电话</td>
				<td><%=owner_users.getOTelephone()%></td>
			</tr>
		</c:if>
		<c:if test="${type == 1}">
			<%Tenant_users tenant_users = (Tenant_users) session.getAttribute("user");%>
			<tr>
				<td width="120px">身份</td>
				<td>租赁者</td>
			</tr>
			<tr>
				<td width="120px">用户id</td>
				<td><%=tenant_users.getTID()%></td>
			</tr>
			<tr>
				<td width="120px">用户名</td>
				<td><%=tenant_users.getUserName()%></td>
			</tr>
			<tr>
				<td width="120px">密码</td>
				<td><%=tenant_users.getPassword()%></td>
			</tr>
			<tr>
				<td width="120px">姓名</td>
				<td><%=tenant_users.getTName()%></td>
			</tr>
			<tr>
				<td width="120px">地址</td>
				<td><%=tenant_users.getTAddress()%></td>
			</tr>
			<tr>
				<td width="120px">电话</td>
				<td><%=tenant_users.getTTelephone()%></td>
			</tr>
			<tr>
				<td width="120px">性别</td>
				<td><%=tenant_users.getTSex()%></td>
			</tr>
		</c:if>
	</table>
	</div>
</body>
</html>
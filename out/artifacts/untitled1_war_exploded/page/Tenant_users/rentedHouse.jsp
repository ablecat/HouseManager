<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>已租房屋</title>
    <link rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<table class="tablelist">
    <thead>
    <tr>
        <th>房屋ID</th>
        <th>房屋地址</th>
        <th>房型</th>
        <th>容量</th>
        <th>租金</th>
        <th>房主ID</th>
        <th>房主姓名</th>
        <th>房主电话</th>
        <th>手续费</th>
        <th width="120px">操作</th>
    </tr>
    </thead>
    <c:forEach items="${pageInfo.list}" var="rentedHouse">
        <tr>
            <td>${rentedHouse.HID}</td>
            <td>${rentedHouse.HAddress}</td>
            <td>${rentedHouse.layout}</td>
            <td>${rentedHouse.capacity}</td>
            <td>${rentedHouse.rent}</td>
            <td>${rentedHouse.OID}</td>
            <td>${rentedHouse.OName}</td>
            <td>${rentedHouse.OTelephone}</td>
            <td>${rentedHouse.amount}</td>
            <td>
                <button class="edit" type="button" onclick="window.location.href='http://localhost:8080/untitled1_war_exploded/tenant_users?method=checkItOut&CID=${rentedHouse.CID}'">
                    <i class="fa fa-edit"></i>
                    退租
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../include/page.jsp"%>
</form>
</body>
</html>

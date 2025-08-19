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
        <th>房主编号</th>
        <th>房主姓名</th>
        <th>房主电话</th>
        <th width="120px">操作</th>
    </tr>
    </thead>
    <c:forEach items="${pageInfo.list}" var="vacantHouse">
        <tr>
            <td>${vacantHouse.HID}</td>
            <td>${vacantHouse.HAddress}</td>
            <td>${vacantHouse.layout}</td>
            <td>${vacantHouse.capacity}</td>
            <td>${vacantHouse.rent}</td>
            <td>${vacantHouse.OID}</td>
            <td>${vacantHouse.OName}</td>
            <td>${vacantHouse.OTelephone}</td>
            <td>
                <button class="edit" type="button" onclick="window.location.href='http://localhost:8080/untitled1_war_exploded/tenant_users?method=seeHouse&HID=${vacantHouse.HID}'">
                    <i class="fa fa-edit"></i>
                    看房
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../include/page.jsp"%>
</form>
</body>
</html>

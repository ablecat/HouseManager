<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>论坛</title>
    <link rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<table class="tablelist">
    <thead>
    <tr>
        <th>帖子编号</th>
        <th>帖子标题</th>
        <th>发帖时间</th>
        <th>帖主身份</th>
        <th>帖主编号</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${pageInfo.list}" var="message">
        <tr>
            <td>${message.MID}</td>
            <td>${message.content}</td>
            <td><fmt:formatDate value="${message.createDate}" type="both"/></td>
            <c:if test="${message.OID!=null}">
                <td>房主</td>
                <td>${message.OID}</td>
            </c:if>
            <c:if test="${message.TID!=null}">
                <td>租赁者</td>
                <td>${message.TID}</td>
            </c:if>
            <td>
                <button class="edit" type="button" onclick="window.location.href='http://localhost:8080/untitled1_war_exploded/forum?method=discuss&MID=${message.MID}'">
                    <i class="fa fa-edit"></i>
                    查看帖子
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="include/page.jsp"%>
</form>
</body>
</html>

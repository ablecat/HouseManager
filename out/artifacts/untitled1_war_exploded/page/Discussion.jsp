<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>查看帖子</title>
    <link rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<div>
<table class="tablelist">
    <thead>
    <tr>
        <th>楼层</th>
        <th>评论内容</th>
        <th>评论时间</th>
        <th>层主身份</th>
        <th>层主编号</th>
    </tr>
    </thead>

    <c:forEach items="${pageInfo.list}" var="message" varStatus="userStatus">
        <tr>
            <td>${userStatus.count}</td>
            <td>${message.content}</td>
            <td><fmt:formatDate value="${message.createDate}" type="both"/></td>
            <c:if test="${message.OID==pageInfo.list.get(0).OID&&message.OID!=null}">
                <td>房主(楼主)</td>
                <td>${message.OID}</td>
            </c:if>
            <c:if test="${message.TID==pageInfo.list.get(0).TID&&message.TID!=null}">
                <td>租赁者(楼主)</td>
                <td>${message.TID}</td>
            </c:if>
            <c:if test="${message.OID!=pageInfo.list.get(0).OID&&message.OID!=null}">
                <td>房主</td>
                <td>${message.OID}</td>
            </c:if>
            <c:if test="${message.TID!=pageInfo.list.get(0).TID&&message.TID!=null}">
                <td>租赁者</td>
                <td>${message.TID}</td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<%@include file="include/page.jsp"%>
</form>
</div>
<div align="center">
    <form id="addForm" action="http://localhost:8080/untitled1_war_exploded/forum?method=reply&MID=${pageInfo.list.get(0).MID}" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>请输入需要评论的内容:</td>
            </tr>
            <tr>
                <td><textarea name="content" maxlength="1000" rows="10" cols="100"></textarea></td>
            </tr>
            <tr>
                <td colspan="4">
                    <button class="remove" type="submit">
                        <i class="fa fa-save"></i>
                        提交
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

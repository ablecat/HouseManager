<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>留言</title>
    <link rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $("#addForm").validate({
                rules:{
                    content:"required"
                }
            });
        });
    </script>
</head>
<body>
<div class="add" align="center">
    <form id="addForm" action="http://localhost:8080/untitled1_war_exploded/forum?method=leaveMessage" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>请输入需要留言的内容:</td>
            </tr>
            <tr>
                <td><textarea name="content" maxlength="1000" rows="20" cols="100"></textarea></td>
            </tr>
            <tr>
                <td colspan="4">
                    <button class="add" type="button" onclick="window.history.back(-1);">
                        <i class="fa fa-arrow-left"></i>
                        返回
                    </button>
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

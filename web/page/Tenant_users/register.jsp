<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册租赁者</title>
    <link  rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
    <script  src="${basePath}static/js/jquery-validation-1.14.0/jquery.validate.js" type="text/javascript"></script>
    <script  src="${basePath}static/js/jquery-validation-1.14.0/localization/messages_zh.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $("#addForm").validate({
                rules:{
                    userName:"required",
                    password:"required",
                    TName:"required",
                    TAddress:"required",
                    TTelephone:"required",
                }
            });
        });
    </script>
</head>
<body>
<div class="add">
    <form id="addForm" action="http://localhost:8080/untitled1_war_exploded/tenant_users?method=register" target="_parent" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>用户名</td>
                <td><input type="text" name="userName" value="yonghu1"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password" value="123456"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="TName" value="zhangsan"></td>
            </tr>
            <tr>
                <td>地址</td>
                <td><input type="text" name="TAddress" value="qingdao"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="TTelephone" value="10086"></td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <select name="TSex">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left">
                    <button class="edit" type="button" onclick="window.history.back(-1);">
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

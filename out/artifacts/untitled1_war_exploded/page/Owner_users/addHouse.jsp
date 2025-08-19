<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加</title>
    <link rel="stylesheet"  href="${basePath}static/css/styles.css" />
    <link rel="stylesheet"  href="${basePath}static/css/font-awesome-4.7.0/css/font-awesome.min.css" />
    <script  src="${basePath}static/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $("#addForm").validate({
                rules:{
                    HAddress:"required",
                    layout:"required",
                    capacity:{
                        required: true,
                        digits:true
                    },
                    rent:{
                        required: true,
                        digits:true
                    },
                    con:"required"
                }
            });
        });
    </script>
</head>
<body>
<div class="add" align="center">
    <form id="addForm" action="http://localhost:8080/untitled1_war_exploded/owner_users?method=addHouse" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>房屋地址</td>
                <td><input type="text" name="HAddress" value="qingdao"></td>
            </tr>
            <tr>
                <td>房屋类型</td>
                <td><input type="text" name="layout" value="da"></td>
            </tr>
            <tr>
                <td>房屋容量</td>
                <td><input type="text" name="capacity" value="20"></td>
            </tr>
            <tr>
                <td>房屋租金</td>
                <td><input type="text" name="rent" value="20000"></td>
            </tr>
            <tr>
                <td>房屋状态</td>
                <td>
                    <select name="con">
                        <option value="0" selected="selected">未出租</option>
                        <option value="1" >已出租</option>
                    </select>
                </td>
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

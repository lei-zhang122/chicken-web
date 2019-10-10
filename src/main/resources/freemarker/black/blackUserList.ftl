<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Document</title>
</head>

<body>
<div class="main-container">
</div>

<div class="main-container">

    <h2>黑名单列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/blackUser/blackUserPageList" method="post">
            <input type="text" name="userId" id="userId" value="<#if userId??>${userId}</#if>" placeholder="用户id">
            <input type="text" name="userName" id="userName" value="<#if userName??>${userName}</#if>" placeholder="昵称">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="submit" class="btn btn-primary" value="查询">
            <input type="button" class="btn btn-success" onclick="javascript:window.location.href='/blackUser/blackUserAdd'" value="新建">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">用户id</td>
            <td class="text-left">昵称</td>
            <td class="text-left">添加人</td>
            <td class="text-left">添加时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left"><#if item.userId??>${item.userId}</#if></td>
            <td class="text-left"><#if item.userName??>${item.userName}</#if></td>
            <td class="text-left"><#if item.createUser??>${item.createUser}</#if></td>
            <td class="text-left"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd hh:mm:ss')}</#if></td>
            <td><a href="/blackUser/blackUserEdit/${item.id}">修改</a>&nbsp;<a href="/blackUser/blackUserDelete/${item.id}">删除</a></td>
        </tr>
        </#list>
        </tbody>
    </table>
<#include "../common/page.ftl">
</div>

</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/public.js"></script>
</html>
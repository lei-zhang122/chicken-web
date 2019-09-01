<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${base}/css/style.css">
    <title>Document</title>
</head>

<body>
<div class="main-container">
</div>

<div class="main-container">

    <h2>用户列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/user/userPageList" method="post">
            <input class="inline-block" type="text" name="userName" id="userName" value="<#if userName??>${userName}</#if>" placeholder="真实姓名">
            <input class="inline-block" type="text" name="loginName" id="loginName" value="<#if loginName??>${loginName}</#if>" placeholder="登陆名称">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="hidden" name="status" id="status" value="1">
            <input type="submit" class="btn btn-primary" value="查询">
            <input type="button" class="btn btn-success" onclick="javascript:window.location.href='/user/userAdd'" value="新建">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">真实姓名</td>
            <td class="text-left">登陆名称</td>
            <td class="text-left">手机号</td>
            <td>创建时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.user_name}</td>
            <td class="text-left">${item.login_name}</td>
            <td class="text-left">${item.phone}</td>
            <td>${item.create_time}</td>
            <td><a href="/user/userEdit/${item.id}">编辑</a>
                &nbsp;<a href="/user/userResetPwd/${item.id}">重置密码</a>
                &nbsp;<a href="/user/userSetRole/${item.id}">设置角色</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>

    <#include "../common/page.ftl">

</div>

</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
<script src="${base}/js/public.js"></script>

</html>
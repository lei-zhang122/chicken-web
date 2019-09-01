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

    <h2>角色列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/sysrole/sysRolePageList" method="post">
            <input class="inline-block" type="text" name="role" id="role" value="<#if role??>${role}</#if>" placeholder="角色名称">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="hidden" name="status" id="status" value="1">
            <input type="submit" class="btn btn-primary" value="查询">
            <input type="button" class="btn btn-success" onclick="javascript:window.location.href='/sysrole/sysRoleAdd'" value="新建">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">角色名称</td>
            <td class="text-left">角色描述</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.role}</td>
            <td class="text-left">${item.description}</td>
            <td><a href="/sysrole/sysRoleEdit/${item.id}">编辑</a></td>
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
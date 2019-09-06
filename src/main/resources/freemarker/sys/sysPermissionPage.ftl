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

    <h2>功能列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/syspermission/permissionPageList" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="hidden" name="parentId" id="parentId" value="${parentId?c}">
            <input type="button" class="btn btn-success" onclick="javascript:window.location.href='/syspermission/sysPermissionAdd/${parentId?c}'" value="新建">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">功能名称</td>
            <td class="text-left">权限字符串</td>
            <td class="text-left">权限类型</td>
            <td class="text-left">URL</td>
            <td class="text-left">是否有效</td>
            <td class="text-left">显示顺序</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.name}</td>
            <td class="text-left"><#if item.url??>${item.permission}</#if></td>
            <td class="text-left"><#if item.url??>${item.resourceType}</#if></td>
            <td class="text-left"><#if item.url??>${item.url}</#if></td>
            <td class="text-left">
                <#if item.available=="1">在用</#if>
                <#if item.available=="0">停用</#if>
            </td>
            <td class="text-left"><#if item.parentIds??>${item.parentIds}</#if></td>
            <td><a href="/syspermission/sysPermissionEdit/${item.id}">编辑</a></td>
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
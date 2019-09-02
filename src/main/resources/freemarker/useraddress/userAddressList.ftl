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

    <h2>用户地址列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/userAddress/userAddressPageList" method="post">
            <select class="inline-block" name="userId" id="userId">
                <option value="">请选择用户</option>
                <#if userList?exists>
                    <#list userList as user>
                        <option value="${user.id}" <#if user.id == userId> selected="selected" </#if> > ${user.nickName!}</option>
                    </#list>
                </#if>
            </select>
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="submit" class="btn btn-primary" value="查询">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">用户昵称</td>
            <td class="text-left">OPENID</td>
            <td class="text-left">联系人</td>
            <td class="text-left">手机号</td>
            <td class="text-left">地址</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.nick_name}</td>
            <td class="text-left">${item.openid}</td>
            <td class="text-left">${item.contact}</td>
            <td class="text-left">${item.phone}</td>
            <td class="text-left">${item.user_address}</td>
            <td><a href="/userAddress/userAddressEdit/${item.id}">编辑</a></td>
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
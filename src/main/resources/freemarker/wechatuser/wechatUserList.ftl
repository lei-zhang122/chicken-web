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

    <h2>用户列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/wechatUser/wechatUserPageList" method="post">
            <input class="inline-block" type="text" name="nickName" id="nickName" value="<#if nickName??>${nickName}</#if>" placeholder="用户昵称">
            <input class="inline-block" type="text" name="regSource" id="regSource" value="<#if regSource??>${regSource}</#if>" placeholder="注册来源">
            <input class="inline-block" type="text" name="inviteNum" id="inviteNum" value="<#if inviteNum??>${inviteNum}</#if>" placeholder="邀请码">
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
            <td class="text-left">注册来源</td>
            <td class="text-left">邀请码</td>
            <td class="text-left">当前积分</td>
            <td class="text-left">获得积分总数</td>
            <td class="text-left">消费积分总数</td>
            <td class="text-left">状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left"><#if item.nick_name??>${item.nick_name}</#if></td>
            <td class="text-left"><#if item.openid??>${item.openid}</#if></td>
            <td class="text-left"><#if item.reg_source??>${item.reg_source}</#if></td>
            <td class="text-left"><#if item.invite_num??>${item.invite_num}</#if></td>
            <td class="text-left">${item.balance}</td>
            <td class="text-left">${item.attent_count}</td>
            <td class="text-left">${item.consume_count}</td>
            <td class="text-left"><#if item.status=='1'>在用</#if><#if item.status=='0'>停用</#if></td>
            <td><a href="/wechatUser/wechatUserEdit/${item.id}">编辑</a></td>
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
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
        <form class="form" name="fromSubmit" id="fromSubmit" action="/wechatUser/wechatUserDialogPageList" method="post">
            <input class="inline-block" type="text" name="nickName" id="nickName" value="<#if nickName??>${nickName}</#if>" placeholder="用户昵称">
            <input class="inline-block" type="text" name="regSource" id="regSource" value="<#if regSource??>${regSource}</#if>" placeholder="注册来源">
            <input class="inline-block" type="text" name="inviteNum" id="inviteNum" value="<#if inviteNum??>${inviteNum}</#if>" placeholder="邀请码">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="submit" class="btn btn-primary" value="查询">
            <input type="button" class="btn btn-success" onclick="onSubmit()" value="确定">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td>&nbsp;</td>
            <td class="text-left">用户昵称</td>
            <td class="text-left">OPENID</td>
            <td class="text-left">注册来源</td>
            <td class="text-left">邀请码</td>
            <td class="text-left">状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td><input type="radio" name="ids" id="ids" value="${item.id}&${item.nick_name}"></td>
            <td class="text-left">${item.nick_name}</td>
            <td class="text-left">${item.openid}</td>
            <td class="text-left">${item.reg_source}</td>
            <td class="text-left">${item.invite_num}</td>
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
<script src="../../js/layer/layer.js"></script>
<script>

    var index = parent.layer.getFrameIndex(window.name);

    function onSubmit(){

        var val = $("input[name='ids']:checked").val();
        var vals = val.split("&");
        parent.$('#nickName').val(vals[1]);
        parent.$('#userId').val(vals[0]);
        parent.layer.close(index);
    }
</script>
</html>
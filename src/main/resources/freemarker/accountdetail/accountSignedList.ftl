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

    <h2>用户打卡列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/accountSigned/accountSignedPageList" method="post">
            <input type="text" name="nickName" id="nickName" maxlength="20" value="<#if nickName??>${nickName}</#if>" onclick="getUser()" placeholder="用户信息">
            <input type="hidden" name="userId" id="userId" value="<#if userId??>${userId}</#if>">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="submit" class="btn btn-primary" value="查询">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">用户昵称</td>
            <td class="text-left">消耗积分</td>
            <td class="text-left">当前积分</td>
            <td class="text-left">备注</td>
            <td class="text-left">打卡时间</td>
            <td class="text-left">状态</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left"><#if item.nick_name??>${item.nick_name}<br>${item.openid}</#if></td>
            <td class="text-left"><#if item.score??>${item.score}</#if></td>
            <td class="text-left"><#if item.score_count??>${item.score_count}</#if></td>
            <td class="text-left"><#if item.remark??>${item.remark}</#if></td>
            <td class="text-left"><#if item.signed_time??>${item.signed_time?string('yyyy-MM-dd hh:mm:ss')}</#if></td>
            <td class="text-left"><#if item.status=='1'>在用</#if><#if item.status=='0'>停用</#if></td>
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
    function getUser(){
        layer.open({
            type: 2,
            area: ['1400px', '750px'],
            offset: '100px',
            fixed: false, //不固定
            maxmin: true,
            content: '/wechatUser/wechatUserDialogPage'
        });

    }
</script>
</html>
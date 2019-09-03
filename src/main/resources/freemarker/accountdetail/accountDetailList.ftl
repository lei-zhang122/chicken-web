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

    <h2>用户流水列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/accountDetail/accountDetailPageList" method="post">
            <input type="text" name="nickName" id="nickName" maxlength="20" value="<#if nickName??>${nickName}</#if>" onclick="getUser()">
            <input type="hidden" name="userId" id="userId" value="<#if userId??>${userId}</#if>">
            <select class="inline-block" name="detailFlag" id="detailFlag">
                <option value="">请选择类型</option>
                <option value="3" <#if detailFlag == '3'> selected="selected" </#if>>邀请好友</option>
                <option value="3" <#if detailFlag == '4'> selected="selected" </#if>>兑换奖品</option>
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
            <td class="text-left">明细类型</td>
            <td class="text-left">消耗积分</td>
            <td class="text-left">当前积分</td>
            <td class="text-left">商品信息</td>
            <td class="text-left">备注</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left"><#if item.nick_name??>${item.nick_name}<br>${item.openid}</#if></td>
            <td class="text-left"><#if item.detail_type??>${item.detail_type}</#if></td>
            <td class="text-left"><#if item.score??>${item.score}</#if></td>
            <td class="text-left"><#if item.score_count??>${item.score_count}</#if></td>
            <td class="text-left"><#if item.good_name??>${item.good_name}</#if></td>
            <td class="text-left"><#if item.remark??>${item.remark}</#if></td>
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
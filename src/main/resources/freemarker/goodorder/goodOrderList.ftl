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

    <h2>订单列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/goodOrder/goodOrderPageList" method="post">
            <input class="inline-block" type="text" name="orderNum" id="orderNum" value="<#if orderNum??>${orderNum}</#if>" placeholder="订单号">
            <select class="inline-block" name="goodId" id="goodId">
                <option value="">请选择商品信息</option>
                <#if goodInfoList?exists>
                    <#list goodInfoList as info>
                        <option value="${info.id}" <#if info.id == goodId> selected="selected" </#if> > ${info.goodName!}</option>
                    </#list>
                </#if>
            </select>
            <input type="text" name="nickName" id="nickName" maxlength="20" value="<#if nickName??>${nickName}</#if>" onclick="getUser()" placeholder="用户信息">
            <input type="hidden" name="userId" id="userId" value="<#if userId??>${userId}</#if>">
            <input class="inline-block" type="text" name="expressNum" id="expressNum" value="<#if expressNum??>${expressNum}</#if>" placeholder="快递单号">
            <input class="inline-block" type="text" name="expressName" id="expressName" value="<#if expressName??>${expressName}</#if>" placeholder="快递名称">
            <select class="inline-block" name="exchangeStatus" id="exchangeStatus">
                <option value="">请选择订单状态</option>
                <option value="1" <#if exchangeStatus == '1'> selected="selected" </#if>>已下单</option>
                <option value="2" <#if exchangeStatus == '2'> selected="selected" </#if>>已发货</option>
                <option value="3" <#if exchangeStatus == '3'> selected="selected" </#if>>已完成</option>
            </select>
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="submit" class="btn btn-primary" value="查询">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">订单号</td>
            <td class="text-left">商品名称</td>
            <td class="text-left">用户信息</td>
            <td class="text-left">快递信息</td>
            <td class="text-left">订单状态</td>
            <td class="text-left">下单时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.order_num}</td>
            <td class="text-left">${item.good_type}<br>${item.good_name}</td>
            <td class="text-left">${item.nick_name}<br>${item.openid}</td>
            <td class="text-left"><#if item.express_name??>${item.express_name}</#if><br><#if item.express_num??>${item.express_num}</#if></td>
            <td class="text-left"><#if item.exchange_status=='1'>已下单</#if><#if item.exchange_status=='2'>已发货</#if><#if item.exchange_status=='3'>已完成</#if></td>
            <td class="text-left"><#if item.exchange_time??>${item.exchange_time?string('yyyy-MM-dd hh:mm:ss')}</#if></td>
            <td class="text-left">
            <a href="/goodOrder/goodOrderEdit/${item.id}">修改</a></td>
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
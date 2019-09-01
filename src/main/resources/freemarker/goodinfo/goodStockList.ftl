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

    <h2>商品列表</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/goodStock/goodStockPageList" method="post">
            <input class="inline-block" type="text" name="goodType" id="goodType" value="<#if goodType??>${goodType}</#if>" placeholder="商品类型">
            <input class="inline-block" type="text" name="goodName" id="goodName" value="<#if goodName??>${goodName}</#if>" placeholder="商品名称">
            <select class="inline-block" name="goodStatus" id="goodStatus">
                <option value="">请选择商品状态</option>
                <option value="1" <#if goodStatus == '1'> selected="selected" </#if>>未上架</option>
                <option value="2" <#if goodStatus == '2'> selected="selected" </#if>>已上架</option>
                <option value="3" <#if goodStatus == '3'> selected="selected" </#if>>没有库存</option>
            </select>
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage?c}">
            <input type="submit" class="btn btn-primary" value="查询">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">商品类型</td>
            <td class="text-left">商品名称</td>
            <td class="text-left">剩余数量</td>
            <td class="text-left">虚拟价值</td>
            <td class="text-left">商品状态</td>
            <td class="text-left">状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.goodType}</td>
            <td class="text-left">${item.goodName}</td>
            <td class="text-left">${item.goodNum}</td>
            <td class="text-left">${item.goodVirtual}</td>
            <td class="text-left"><#if item.goodStatus=='1'>未上架</#if><#if item.goodStatus=='2'>已上架</#if><#if item.goodStatus=='3'>没有库存</#if></td>
            <td class="text-left"><#if item.status=='1'>在用</#if><#if item.status=='0'>停用</#if></td>
            <td class="text-left">
            <#if item.goodStatus=='1'><a href="/goodStock/goodStockEditGoodStatus/${item.id}/2">上架</a></#if>
            <#if item.goodStatus=='2'><a href="/goodStock/goodStockEditGoodStatus/${item.id}/1">下架</a></#if>
            <a href="/goodStock/goodStockEdit/${item.id}">修改库存</a></td>
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
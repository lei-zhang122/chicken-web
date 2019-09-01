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
        <form class="form" name="fromSubmit" id="fromSubmit" action="/goodDetail/goodDetailPageList" method="post">
            <select class="inline-block" name="goodId" id="goodId">
                <option value="">请选择商品</option>
                <#if goodInfoList?exists>
                    <#list goodInfoList as info>
                        <option value="${info.id}" <#if info.id == goodId> selected="selected" </#if> > ${info.goodName!}</option>
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
            <td class="text-left">商品类型</td>
            <td class="text-left">商品名称</td>
            <td class="text-left">增加库存</td>
            <td class="text-left">修改时间</td>
            <td class="text-left">备注</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">${item.good_type}</td>
            <td class="text-left">${item.good_name}</td>
            <td class="text-left">${item.good_num}</td>
            <td class="text-left">
            <#if item.create_time??>
                ${item.create_time?string('yyyy-MM-dd hh:mm:ss')}
            </#if>
            </td>
            <td class="text-left">
            <#if item.remark??>
                ${item.remark}
            </#if>
            </td>
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
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
    <form class="form" action="" method="">
        <h2><a href="/goodOrder/goodOrderPage">订单管理</a> / 订单维护</h2>
        <dl>
            <dt>用户昵称：</dt>
            <dd>
                ${nickName}
            </dd>
            <dt>订单号：</dt>
            <dd>
                ${goodOrder.orderNum}
            </dd>
            <dt>收件人信息：</dt>
            <dd>
                ${userAddress.contact} - ${userAddress.phone}<br>
                ${userAddress.provinceName} - ${userAddress.cityName} - ${userAddress.countyName}<br>
                ${userAddress.userAddress} - ${userAddress.postalCode}
            </dd>
            <dt>商品名称：</dt>
            <dd>
                <select class="inline-block" name="goodId" id="goodId">
                    <option value="">请选择商品信息</option>
                    <#if goodInfoList?exists>
                        <#list goodInfoList as info>
                            <option value="${info.id}" <#if info.id == goodOrder.goodId> selected="selected" </#if> > ${info.goodName!}</option>
                        </#list>
                    </#if>
                </select>
            </dd>
            <dt>快递单号：</dt>
            <dd>
                <input type="text" name="expressNum" id="expressNum" maxlength="30"  value="<#if goodOrder.expressNum??>${goodOrder.expressNum}</#if>">
            </dd>
            <dt>快递名称：</dt>
            <dd>
                <input type="text" name="expressName" id="expressName" maxlength="10"
                       value="<#if goodOrder.expressName??>${goodOrder.expressName}</#if>">
            </dd>
            <dt>订单状态：</dt>
            <dd>
                <select class="inline-block" name="exchangeStatus" id="exchangeStatus">
                    <option value="">请选择订单状态</option>
                    <option value="1" <#if goodOrder.exchangeStatus == '1'> selected="selected" </#if>>已下单</option>
                    <option value="2" <#if goodOrder.exchangeStatus == '2'> selected="selected" </#if>>已发货</option>
                    <option value="3" <#if goodOrder.exchangeStatus == '3'> selected="selected" </#if>>已完成</option>
                </select>
            </dd>
            <dt>兑换时间：</dt>
            <dd>
                ${exchangeTime}
            </dd>
            <dt>修改时间：</dt>
            <dd>
                <#if modifyTime??>${modifyTime}</#if>
            </dd>
            <dt>修改人：</dt>
            <dd>
                <#if modifyUser??>${modifyUser}</#if>
            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if goodOrder.id??>${goodOrder.id}</#if>">
                <input type="hidden" name="status" id="status" value="<#if goodOrder.status??>${goodOrder.status}</#if>">
                <input type="hidden" name="orderNum" id="orderNum" value="<#if goodOrder.orderNum??>${goodOrder.orderNum}</#if>">
                <input type="hidden" name="userId" id="userId" value="<#if goodOrder.userId??>${goodOrder.userId}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="hidden" name="exchangeTime" id="exchangeTime" value="<#if exchangeTime??>${exchangeTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/goodOrder/goodOrderPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.goodOrder.addGoodOrder(this)"">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/goodOrder.js"></script>
</html>
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
        <h2><a href="/userAddress/userAddressPage">用户地址管理</a> / 地址维护</h2>
        <dl>
            <dt>用户昵称：</dt>
            <dd>
                ${nickName}
            </dd>
            <dt>联系人：</dt>
            <dd>
                <input type="text" name="contact" id="contact" maxlength="20"  value="<#if userAddress.contact??>${userAddress.contact}</#if>">
            </dd>
            <dt>手机号：</dt>
            <dd>
                <input type="text" name="phone" id="phone" maxlength="11"  value="<#if userAddress.phone??>${userAddress.phone}</#if>">
            </dd>
            <dt>地址：</dt>
            <dd>
                <input type="text" name="userAddress" id="userAddress" maxlength="200"
                       value="<#if userAddress.userAddress??>${userAddress.userAddress}</#if>">
            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="status" id="status" value="0"  <#if userAddress.status=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="status" id="status" value="1"  <#if userAddress.status=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if userAddress.id??>${userAddress.id}</#if>">
                <input type="hidden" name="userId" id="userId" value="<#if userAddress.userId??>${userAddress.userId}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/userAddress/userAddressPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.userAddress.addUserAddress(this)"">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/userAddress.js"></script>
</html>
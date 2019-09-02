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
        <h2><a href="/accountUser/accountUserPage">用户账户管理</a> / 账户维护</h2>
        <dl>
            <dt>用户昵称：</dt>
            <dd>
                ${nickName}
            </dd>
            <dt>当前积分：</dt>
            <dd>
                <input type="text" name="balance" id="balance" maxlength="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if accountUser.balance??>${accountUser.balance?c}</#if>">
            </dd>
            <dt>获得积分总数：</dt>
            <dd>
                <input type="text" name="attentCount" id="attentCount" maxlength="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if accountUser.attentCount??>${accountUser.attentCount?c}</#if>">
            </dd>
            <dt>消费积分总数：</dt>
            <dd>
                <input type="text" name="consumeCount" id="consumeCount" maxlength="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "
                       value="<#if accountUser.consumeCount??>${accountUser.consumeCount?c}</#if>">
            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if accountUser.id??>${accountUser.id}</#if>">
                <input type="hidden" name="status" id="status" value="<#if accountUser.status??>${accountUser.status}</#if>">
                <input type="hidden" name="userId" id="userId" value="<#if accountUser.userId??>${accountUser.userId}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/accountUser/accountUserPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.accountUser.addAccountUser(this)"">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/accountUser.js"></script>
</html>
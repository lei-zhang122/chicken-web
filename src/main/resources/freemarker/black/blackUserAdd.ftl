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
        <h2><a href="/blackUser/blackUserPage">黑名单管理</a> / 黑名单维护</h2>
        <dl>
            <dt>用户id：</dt>
            <dd>
                <input type="text" name="userId" id="userId" maxlength="20"  value="<#if blackUser.userId??>${blackUser.userId}</#if>">
            </dd>
            <dt>昵称：</dt>
            <dd>
                <input type="text" name="userName" id="userName" maxlength="20"  value="<#if blackUser.userName??>${blackUser.userName}</#if>">
            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if blackUser.id??>${blackUser.id}</#if>">
                <input type="hidden" name="createUser" id="createUser" value="<#if blackUser.createUser??>${blackUser.createUser}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/blackUser/blackUserPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.blackUser.addBlackUser(this)"">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/blackUser.js"></script>
</html>
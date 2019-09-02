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
        <h2><a href="/wechatUser/wechatUserPage">用户管理</a> / 用户维护</h2>
        <dl>
            <dt>用户昵称：</dt>
            <dd>
                <input type="text" name="nickName" id="nickName" maxlength="20" value="<#if wechatUser.nickName??>${wechatUser.nickName}</#if>">
            </dd>
            <dt>OPENID：</dt>
            <dd>
                <input type="text" name="openid" id="openid" maxlength="20" value="<#if wechatUser.openid??>${wechatUser.openid}</#if>">
            </dd>
            <dt>头像地址：</dt>
            <dd>
                <input type="text" name="avatar" id="avatar" maxlength="100"  value="<#if wechatUser.avatar??>${wechatUser.avatar}</#if>">
            </dd>
            <dt>UNIONID：</dt>
            <dd>
                <input type="text" name="unionid" id="unionid" maxlength="10"
                       value="<#if wechatUser.unionid??>${wechatUser.unionid}</#if>">
            </dd>
            <dt>邀请码：</dt>
            <dd>
                <input type="text" name="inviteNum" id="inviteNum" maxlength="20"
                value="<#if wechatUser.inviteNum??>${wechatUser.inviteNum}</#if>">
            </dd>
            <dt>注册来源：</dt>
            <dd>
                <input type="text" name="regSource" id="regSource" maxlength="20"   value="<#if wechatUser.regSource??>${wechatUser.regSource}</#if>">
            </dd>
            <dt>性别：</dt>
            <dd>
                <label >
                    <input type="radio" name="sex" id="sex" value="0"  <#if wechatUser.sex=='0'>checked</#if>> 女
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="sex" id="sex" value="1"  <#if wechatUser.sex=='1'>checked</#if>> 男
                </label>

            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="status" id="status" value="0"  <#if wechatUser.status=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="status" id="status" value="1"  <#if wechatUser.status=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if wechatUser.id??>${wechatUser.id}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/wechatUser/wechatUserPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.wechatUser.addWechatUser(this)"">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/wechatUser.js"></script>
</html>
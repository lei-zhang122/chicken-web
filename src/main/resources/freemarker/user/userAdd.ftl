<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${base}/css/style.css">
    <title>Document</title>
</head>

<body>
<div class="main-container">
    <form class="form" action="" method="">
        <h2><a href="/user/userPage">用户管理</a> / 用户维护</h2>
        <dl>
            <dt>真实姓名：</dt>
            <dd>
                <input type="text" name="userName" id="userName" maxlength="20" value="<#if user.userName??>${user.userName}</#if>">
            </dd>
            <dt>用户名称：</dt>
            <dd>
                <input type="text" name="loginName" id="loginName" maxlength="11" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if user.loginName??>${user.loginName}</#if>">
            </dd>
            <dt>用户密码：</dt>
            <dd>
                <input type="text" name="userPwd" id="userPwd" maxlength="11" value="<#if user.userPwd??>${user.userPwd}</#if>">
            </dd>
            <dt>手机号：</dt>
            <dd>
                <input type="text" name="phone" id="phone" maxlength="11" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if user.phone??>${user.phone}</#if>">
            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="status" id="status" value="0"  <#if user.status=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="status" id="status" value="1"  <#if user.status=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>描述</dt>
            <dd>
                <textarea cols="30" rows="10" name="remark" id="remark" maxlength="25" ><#if user.remark??>${user.remark}</#if></textarea>
            </dd>
            <dt>

                <input type="hidden" name="id" id="id" value="<#if user.id??>${user.id}</#if>">
                <input type="hidden" name="createUser" id="createUser" value="<#if user.createUser??>${user.createUser}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="hidden" name="editTime" id="editTime" value="<#if editTime??>${editTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/user/userPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.user.addUser(this)">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
<script src="${base}/js/user.js"></script>
</html>
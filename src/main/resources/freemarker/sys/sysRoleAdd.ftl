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
        <h2><a href="/sysrole/sysRolePage">角色管理</a> / 角色维护</h2>
        <dl>
            <dt>角色名称：</dt>
            <dd>
                <input type="text" name="role" id="role" maxlength="20" value="<#if sysRole.role??>${sysRole.role}</#if>">
            </dd>
            <dt>角色描述：</dt>
            <dd>
                <input type="text" name="description" id="description" maxlength="11" value="<#if sysRole.description??>${sysRole.description}</#if>">
            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="available" id="available" value="0"  <#if sysRole.available=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="available" id="available" value="1"  <#if sysRole.available=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>

                <input type="hidden" name="id" id="id" value="<#if sysRole.id??>${sysRole.id}</#if>">
                <input type="button" onclick="javascript:window.location.href='/sysrole/sysRolePage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.sysRole.addSysRole(this)">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
<script src="${base}/js/role.js"></script>
</html>
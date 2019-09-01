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
    <form class="form" action="/syspermission/insertSysPermission" method="post">
        <h2>功能管理 / 功能维护</h2>
        <dl>
            <dt>功能名称：</dt>
            <dd>
                <input type="text" name="name" id="name" maxlength="20" value="<#if sysPermission.name??>${sysPermission.name}</#if>">
            </dd>
            <dt>权限字符串：</dt>
            <dd>
                <input type="text" name="permission" id="permission" maxlength="100" value="<#if sysPermission.permission??>${sysPermission.permission}</#if>">
            </dd>
            <dt>权限类型：</dt>
            <dd>
                <label >
                    <input type="radio" name="resourceType" id="resourceType" value="menu"  <#if sysPermission.resourceType=='menu'>checked</#if>> menu
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="resourceType" id="resourceType" value="button"  <#if sysPermission.resourceType=='button'>checked</#if>> button
                </label>
            </dd>
            <dt>URL：</dt>
            <dd>
                <input type="text" name="url" id="url" maxlength="200" value="<#if sysPermission.url??>${sysPermission.url}</#if>">
            </dd>
            <dt>显示顺序：</dt>
            <dd>
                <input type="text" name="parentIds" id="parentIds" maxlength="20" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if sysPermission.parentIds??>${sysPermission.parentIds}</#if>">
            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="available" id="available" value="0"  <#if sysPermission.available=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="available" id="available" value="1"  <#if sysPermission.available=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>

                <input type="hidden" name="id" id="id" value="<#if sysPermission.id??>${sysPermission.id}</#if>">
                <input type="hidden" name="parentId" id="parentId" value="<#if sysPermission.parentId??>${sysPermission.parentId}</#if>">
                <input type="button" onclick="javascript:window.location.href='/syspermission/permissionList/${sysPermission.parentId}'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="submit" class="btn btn-primary" value="提交">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
</html>
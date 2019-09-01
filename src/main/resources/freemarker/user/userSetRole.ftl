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
</div>

<div class="main-container">

    <h2>设置用户角色</h2>
    <div class="query-box">
        <form class="form" name="fromSubmit" id="fromSubmit" action="/user/insertUserRole" method="post">
            为<font color="#1e90ff">${user.userName}</font>设置角色
            <input type="hidden" name="userId" id="userId" value="${user.id}">
            <input type="hidden" name="roleId" id="roleId" value="<#if myrole??>${myrole}</#if>">
            <input type="button" class="btn btn-primary" onclick="tj()" value="提交">
        </form>
    </div>
    <table class="table" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <td>序号</td>
            <td class="text-left">角色姓名</td>
        </tr>
        </thead>
        <tbody>
        <#list list as item>
        <tr>
            <td>${item_index + 1}</td>
            <td class="text-left">
                <input type="radio" id="role" <#if item.userId??> checked </#if> onchange="getRole(this.value)" name="role" value="${item.id}">&nbsp;
            ${item.role}</td>
        </tr>
        </#list>
        </tbody>
    </table>

    <#include "../common/page.ftl">

</div>

</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
<script src="${base}/js/public.js"></script>
<script>
    function getRole(val) {
        $("#roleId").val(val);
    }

    function tj(){
        var userId = $("#userId").val();
        var roleId = $("#roleId").val();

        if(!userId){
            alert("用户不能为空");
            return false;
        }

        if(!roleId){
            alert("角色不能为空");
            return false;
        }

        document.getElementById('fromSubmit').submit();
    }
</script>
</html>
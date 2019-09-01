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
        <h2><a href="/user/userPage">用户管理</a> / 用户密码修改</h2>
        <dl>

            <dt>用户密码：</dt>
            <dd>
                <input type="text" name="userPwd" id="userPwd" maxlength="11">
            </dd>
            <dt>

                <input type="hidden" name="id" id="id" value="<#if user.id??>${user.id}</#if>">
                <input type="button" onclick="javascript:window.location.href='/user/userPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.user.resetPwd(this)">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
<script src="${base}/js/user.js"></script>
</html>
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
        <h2><a href="/wechatUser/wechatUserPage">用户管理</a> / 查看用户</h2>
        <dl>
            <dd>
                用户昵称：<#if wechatUser.nickName??>${wechatUser.nickName}</#if>
            </dd>
            <dd>
                OPENID：<#if wechatUser.openid??>${wechatUser.openid}</#if>
            </dd>
            <dd>
                积分数量：<#if balance??>${balance}</#if>个
            </dd>
            <dd>
                揍小鸡次数：<#if hitCount??>${hitCount}</#if>次
            </dd>
            <dd>
                被揍次数：<#if byHitCount??>${byHitCount}</#if>次
            </dd>
            <dd>
                签到天数：<#if signed??>${signed}</#if>天
            </dd>
            <dd>
                兑奖奖品：<#if goodsCount??>${goodsCount}</#if>个
            </dd>
            <dd>
                好友邀请：<#if invite??>${invite}</#if>个
            </dd>
            <dt>
                <input type="button" onclick="javascript:window.location.href='/wechatUser/wechatUserPage'" class="btn" value="返回">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
</html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/style.css">
    <title>后台管理系统</title>
</head>

<body>

<div class="top-bar">
    <div class="site-title">后台管理系统</div>
    <div style="padding-right:70px;"><#if user_session.userName??>${user_session.userName}</#if>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/logout" style="text-decoration:blink;text-decoration:underline;color: white;text-decoration: none;">注销</a></div>
</div>

<main>
    <menu class="side-menu">
        <#if parentList?exists>
        <#list parentList?keys as strKey>
            <h3>${strKey}</h3>
            <div class="sub-menu hidden">
            <#assign dlong = parentList[strKey]?number/>
            <#list childList?keys as key>
                <#if key?number == dlong>
                    <#list childList[key] as itemValue>
                        ${itemValue}
                    </#list>
                </#if>
            </#list>
            </div>
        </#list>
        </#if>
    </menu>
    <div class="content">
        <iframe name="mainframe" scrolling="yes" src="" frameborder="0" width="100%" height="100%"></iframe>
    </div>
</main>
<script src="js/jquery/jquery.min.3.3.1.js"></script>
<script src="js/js.js"></script>
</body>
</html>
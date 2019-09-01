<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理平台</title>
        <link href="../../css/index.css" rel="stylesheet">
        <link href="../../css/common.css" rel="stylesheet">
        <style type="text/css">@keyframes resizeanim { from { opacity: 0; } to { opacity: 0; } }
    .resize-triggers { animation: 1ms resizeanim; visibility: hidden; opacity: 0; }
    .resize-triggers, .resize-triggers > div, .contract-trigger:before { content: " "; display: block; position: absolute; top: 0; left: 0; height: 100%; width: 100%; overflow: hidden; z-index: -1 }
    .resize-triggers > div { background: #eee; overflow: auto; }
    .contract-trigger:before { width: 200%; height: 200%; }</style>
    </head>
    <body style="">
        <div id="app">
            <div class="login">
                <div class="loginbox">
                    <h3>后台管理平台</h3>
                    <form class="form" name="fromSubmit" id="fromSubmit" action="/loginUser" method="post" onsubmit="return goLogin()">
                    <div>
                        <label class="name" style="width: 378px">
                            <i class="icon-phone"></i>
                            <input id="phonenumber" type="number" name="phonenumber" placeholder="请输入账号" value="15910666412">
                        </label>
                        <label class="password">
                            <i class="icon-pass"></i>
                            <input id="password" type="password" name="password" placeholder="请输入密码" value="123456">
                        </label>
                    </div>
                    <div id="loginDiv" class="submit" > <!---->
                        <button type="submit" style="width:400px;display:block;line-height: 42px;text-align: center;color: #fff;background: #169bd5;border-radius:4px;margin-top:20px;">登录</button>
                    </div>
                    <div align="center" style="margin-top: 10px;">
                        <a href="javascript:;" style="border-color: #fff;color: red;text-align: center;">${msg!}</a>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../../js/jquery/jquery.min.3.3.1.js"></script>
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/vendor.js"></script>
        <script type="text/javascript" src="../../js/mainfest.js"></script>
        <script type="text/javascript" src="../../js/app.js"></script>
    <textarea style="letter-spacing:normal;line-height:21px;padding-top:5px;padding-bottom:5px;font-family:monospace;font-weight:400;font-size:14px;text-rendering:auto;text-transform:none;width:840px;text-indent:0px;padding-left:7px;padding-right:7px;border-width:1px;box-sizing:border-box;
      height:0 !important;
      visibility:hidden !important;
      overflow:hidden !important;
      position:absolute !important;
      z-index:-1000 !important;
      top:0 !important;
      right:0 !important">
    </textarea>
    </body>
</html>
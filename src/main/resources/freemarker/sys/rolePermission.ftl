<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${base}/css/style.css">
    <link href="${base}/css/tree.css" rel="stylesheet" type="text/css" />
    <link href="${base}/css/page.css" rel="stylesheet" type="text/css" />
    <script src="${base}/js/shCore.js" type="text/javascript"></script>
    <script src="${base}/js/shBrushJScript.js" type="text/javascript"></script>
    <script src="${base}/js/shBrushCss.js" type="text/javascript"></script>
    <link href="${base}/css/shCore.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/css/shThemeDefault.css" rel="stylesheet" type="text/css"/>
    <title>Document</title>
</head>

<body>

<div class="main-container">

    <h2>角色列表</h2>
    <div style="padding:10px;">
        <div class="demo" style="width: 18%;float: left">
            <div style="border-bottom: #c3daf9 1px solid; border-left: #c3daf9 1px solid; width: 250px; height: 500px; overflow: auto; border-top: #c3daf9 1px solid; border-right: #c3daf9 1px solid;">
                <div id="tree"> </div>
            </div>
        </div>
        <div class="docs">
            <fieldset>
                <legend>选择角色</legend>
                <pre>
                <#list sysRoles as item>
                    <input type="radio" id="role" name="role" value="${item.id}" onchange="getRole(this.value)">${item.role}&nbsp;
                </#list>
                </pre>
                <form class="form" name="fromSubmit" id="fromSubmit" action="/sysrole/insertRolePermission" method="post">
                    <input type="hidden" name="roleId" id="roleId">
                    <input type="hidden" name="perIds" id="perIds">
                    <input type="button" class="btn btn-primary" onclick="zzz()" id="showchecked" value="提交">
                </form>


            </fieldset>

        </div>
    </div>


</div>

</body>
<script src="${base}/js/jquery/jquery.min.3.3.1.js"></script>
<script src="${base}/js/js.js"></script>
<script src="${base}/js/public.js"></script>
<script src="${base}/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${base}/js/jquery.tree.js" type="text/javascript"></script>
<script src="${base}/js/tree2.js" type="text/javascript"></script>
<script type="text/javascript">

    var userAgent = window.navigator.userAgent.toLowerCase();

    $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);

    $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);

    $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);



    function load(val) {

        var o = {
            showcheck: true
        };
        treedata = [createNoesAjax(val)];
        o.data = treedata;

        $("#tree").treeview(o);
    }

    if ($.browser.msie6) {
        load(0);
    }
    else {
        $(document).ready(load(0));
    }



    function getRole(val){
        $("#roleId").val(val);
        $("#perIds").val();
        if ($.browser.msie6) {
            load(val);
        }
        else {
            $(document).ready(load(val));
        }
    }

    function zzz() {

        var s = $("#tree").getCheckedNodes();
        var a = $("#roleId").val();
        if(!a){
           alert("请选择角色");
           return false;
        }

        if (s.length!=0){
            $("#perIds").val(s.join(","));
            document.getElementById('fromSubmit').submit();
        }else{
            alert("请选择功能");
            return false;
        }
    }


</script>
</html>
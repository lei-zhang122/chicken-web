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
            <#--<div style="margin-bottom:5px;"> <a class="button" href="javascript:void(0);" id="showchecked">获取选定的节点</a> <a class="button" href="javascript:void(0);" id="showcurrent">获取当前节点</a> </div>-->
            <div style="border-bottom: #c3daf9 1px solid; border-left: #c3daf9 1px solid; width: 250px; height: 500px; overflow: auto; border-top: #c3daf9 1px solid; border-right: #c3daf9 1px solid;">
                <div id="tree"> </div>
            </div>
        </div>
        <div class="docs">
            <fieldset style="height: 900px;">
                <pre>
            <iframe name="myiframe" id="myiframe" scrolling="yes" src="" frameborder="0" width="94%" height="700"></iframe>
            </pre>
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
<script src="${base}/js/tree1.js" type="text/javascript"></script>
<script type="text/javascript">

    var userAgent = window.navigator.userAgent.toLowerCase();

    $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);

    $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);

    $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);

    function load() {

        var o = {
            showcheck: true,
            onnodeclick:function(item){
                document.getElementById("myiframe").src="/syspermission/permissionList/"+item.value;
            }
        };

        o.data = treedata;

        $("#tree").treeview(o);

        $("#showchecked").click(function (e) {

            var s = $("#tree").getCheckedNodes();
            alert(s);
            if (s != null)
                alert(s.join(","));
            else
                alert("NULL");
        });

        $("#showcurrent").click(function (e) {
            var s = $("#tree").getCurrentNode();
            if (s != null)
                alert(s.text);
            else
                alert("NULL");
        });

    }

    if ($.browser.msie6) {
        load();
    }
    else {
        $(document).ready(load);
    }

</script>
</html>
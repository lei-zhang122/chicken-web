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
    <form class="form" name="fromSubmit" id="fromSubmit" action="/goodStock/updateGoodNum" method="post">
        <h2><a href="/goodInfo/goodInfoPage">商品管理</a> / 商品维护</h2>
        <dl>
            <dt>商品类型：</dt>
            <dd>
                <#if goodInfo.goodType??>${goodInfo.goodType}</#if>
            </dd>
            <dt>商品名称：</dt>
            <dd>
                <#if goodInfo.goodName??>${goodInfo.goodName}</#if>
            </dd>
            <dt>商品数量：</dt>
            <dd>
                <input type="text" name="goodNum" id="goodNum" maxlength="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if goodInfo.goodNum??>${goodInfo.goodNum}</#if>">
            </dd>
            <dt>商品价格：</dt>
            <dd>
                <#if goodInfo.goodPrice??>${goodInfo.goodPrice?c}</#if>
            </dd>
            <dt>虚拟价格：</dt>
            <dd>
                <#if goodInfo.goodVirtual??>${goodInfo.goodVirtual?c}</#if>
            </dd>
            <dt>虚拟降价价格：</dt>
            <dd>
                <#if goodInfo.goodDownVirtual??>${goodInfo.goodDownVirtual?c}</#if>
            </dd>
            <dt>商品状态：</dt>
            <dd>
                <#if goodInfo.goodStatus=='1'>未上架</#if><#if goodInfo.goodStatus=='2'>已上架</#if><#if goodInfo.goodStatus=='3'>没有库存</#if>
            </dd>
            <dt>商品图片：</dt>
            <dd>
                <#if goodInfo.goodImg??>
                <img src="${goodInfo.goodImg}">
                </#if>
            </dd>
            <dt>商品明细：</dt>
            <dd>
                <#if goodInfo.goodDetail??>${goodInfo.goodDetail}</#if>
            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if goodInfo.id??>${goodInfo.id}</#if>">
                <input type="button" onclick="javascript:window.location.href='/goodStock/goodStockPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="tijiao()">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script type="text/javascript">
    function tijiao() {

        var goodNum = $("#goodNum").val();
        if(!goodNum){
            alert("请填写商品数量");
            return false;
        }
        document.getElementById('fromSubmit').submit();
    }


</script>
</html>
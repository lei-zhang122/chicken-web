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
    <form class="form" name="fromSubmit" id="fromSubmit" action="/goodInfo/insertGoodInfo" method="post" enctype="multipart/form-data">
        <h2><a href="/goodInfo/goodInfoPage">商品管理</a> / 商品维护</h2>
        <dl>
            <dt>商品类型：</dt>
            <dd>
                <input type="text" name="goodType" id="goodType" maxlength="20" value="<#if goodInfo.goodType??>${goodInfo.goodType}</#if>">
            </dd>
            <dt>商品名称：</dt>
            <dd>
                <input type="text" name="goodName" id="goodName" maxlength="20" value="<#if goodInfo.goodName??>${goodInfo.goodName}</#if>">
            </dd>
            <dt>商品数量：</dt>
            <dd>
                <input type="text" name="goodNum" id="goodNum" maxlength="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if goodInfo.goodNum??>${goodInfo.goodNum}</#if>">
            </dd>
            <dt>商品价格：</dt>
            <dd>
                <input type="text" name="goodPrice" id="goodPrice" maxlength="10"
                       onkeyup="value=value.replace(/[^\d^\.]+/g,'').replace('.','$#$').replace(/\./g,'').replace('$#$','.')"
                       value="<#if goodInfo.goodPrice??>${goodInfo.goodPrice?c}</#if>">
            </dd>
            <dt>虚拟价格：</dt>
            <dd>
                <input type="text" name="goodVirtual" id="goodVirtual" maxlength="10"
                       onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "
                value="<#if goodInfo.goodVirtual??>${goodInfo.goodVirtual?c}</#if>">
            </dd>
            <dt>虚拟降价价格：</dt>
            <dd>
                <input type="text" name="goodDownVirtual" id="goodDownVirtual" maxlength="10"  onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if goodInfo.goodDownVirtual??>${goodInfo.goodDownVirtual?c}</#if>">
            </dd>
            <dt>商品图片：</dt>
            <dd>
                <input class="inline-block" type="file" name="fileName" id="fileName" placeholder="文件名称">
                <#if goodInfo.goodImg??><a href="/goodInfo/imgDelete/${goodInfo.id}">删除</a></#if>
            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="status" id="status" value="0"  <#if goodInfo.status=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="status" id="status" value="1"  <#if goodInfo.status=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>商品明细：</dt>
            <dd>
                <textarea name="goodDetail" id="goodDetail"><#if goodInfo.goodDetail??>${goodInfo.goodDetail}</#if></textarea>
            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if goodInfo.id??>${goodInfo.id}</#if>">
                <input type="hidden" name="createUser" id="createUser" value="<#if goodInfo.createUser??>${goodInfo.createUser}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/goodInfo/goodInfoPage'" class="btn" value="返回">
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
        var goodType = $("#goodType").val();
        if(!goodType){
            alert("请填写商品类型");
            return false;
        }
        var goodName = $("#goodName").val();
        if(!goodName){
            alert("请填写商品名称");
            return false;
        }
        var goodNum = $("#goodNum").val();
        if(!goodNum){
            alert("请填写商品数量");
            return false;
        }
        var goodPrice = $("#goodPrice").val();
        if(!goodPrice){
            alert("请填写商品价格");
            return false;
        }
        var goodVirtual = $("#goodVirtual").val();
        if(!goodVirtual){
            alert("请填写虚拟价格");
            return false;
        }
        var goodDownVirtual = $("#goodDownVirtual").val();
        if(!goodDownVirtual){
            alert("请填写虚拟降价价格");
            return false;
        }

        document.getElementById('fromSubmit').submit();
    }


</script>
</html>
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
        <h2><a href="/dictionary/dictionaryCommonPage">通用内容管理</a> / 通用内容维护</h2>
        <dl>
            <dt>标题：</dt>
            <dd>
                <input type="text" name="dictName" id="dictName" maxlength="20"  value="<#if dictionary.dictName??>${dictionary.dictName}</#if>">
            </dd>
            <dt>内容：</dt>
            <dd>
                <input type="text" name="dictContent" id="dictContent" maxlength="100"  value="<#if dictionary.dictContent??>${dictionary.dictContent}</#if>">
            </dd>
            <dt>排序：</dt>
            <dd>
                <input type="text" name="dictOrder" id="dictOrder" maxlength="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                       onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " value="<#if dictionary.dictOrder??>${dictionary.dictOrder!c}</#if>">
            </dd>
            <dt>状态：</dt>
            <dd>
                <label >
                    <input type="radio" name="status" id="status" value="0"  <#if dictionary.status=='0'>checked</#if>> 停用
                </label>
                &nbsp;&nbsp;
                <label >
                    <input type="radio" name="status" id="status" value="1"  <#if dictionary.status=='1'>checked</#if>> 在用
                </label>

            </dd>
            <dt>描述：</dt>
            <dd>
                <textarea name="dictDetail" id="dictDetail"><#if dictionary.dictDetail??>${dictionary.dictDetail}</#if></textarea>
            </dd>
            <dt>
                <input type="hidden" name="id" id="id" value="<#if dictionary.id??>${dictionary.id}</#if>">
                <input type="hidden" name="status" id="status" value="<#if dictionary.status??>${dictionary.status}</#if>">
                <input type="hidden" name="dictType" id="dictType" value="<#if dictionary.dictType??>${dictionary.dictType}</#if>">
                <input type="hidden" name="createUser" id="createUser" value="<#if dictionary.createUser??>${dictionary.createUser}</#if>">
                <input type="hidden" name="createTime" id="createTime" value="<#if createTime??>${createTime}</#if>">
                <input type="button" onclick="javascript:window.location.href='/dictionary/dictionaryCommonPage'" class="btn" value="返回">
                &nbsp;&nbsp;
                <input type="button" class="btn btn-primary" value="提交" onclick="TT.dictionary.addDictionary(this)"">
            </dt>
        </dl>
    </form>
</div>
</body>
<script src="../../js/jquery/jquery.min.3.3.1.js"></script>
<script src="../../js/js.js"></script>
<script src="../../js/dictionary.js"></script>
</html>
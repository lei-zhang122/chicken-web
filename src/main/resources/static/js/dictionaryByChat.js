var TT = TT || {};
TT.dictionary = TT.dictionary || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.dictionary.addDictionary = function(obj) {

    var addDictionary = {} ;
    addDictionary.id = $("#id").val();
    addDictionary.dictName = $("#dictName").val();
    addDictionary.dictContent = $("#dictContent").val();
    addDictionary.dictOrder = $("#dictOrder").val();
    addDictionary.dictType = $("#dictType").val();
    addDictionary.createTime = $("#createTime").val();
    addDictionary.status = $("input[name='status']:checked").val();
    addDictionary.dictDetail = $("#dictDetail").val();
    addDictionary.createUser = $("#createUser").val();
    addDictionary.differentFlag = $("#differentFlag").val();

    var type = $("#dictType").val();

    var res = doAjaxPostRequest("/dictionary/insertDictionary", JSON.stringify(addDictionary));
    if(res == null) {
        alert("操作成功");
        if(type=='dk'){
            window.location.href='/dictionary/dictionaryPage'
        } else if(type=='xjdh'){
            window.location.href='/dictionary/dictionaryChatPage'
        } else if(type=='tg'){
            window.location.href='/dictionary/dictionaryNotifyPage'
        } else if(type=='common'){
            window.location.href='/dictionary/dictionaryCommonPage'
        } else if(type=='hdgz'){
            window.location.href='/dictionary/dictionaryActivePage'
        }

    }else {
        alert(res);
        if(res=="请登陆之后，再操作。"){
            window.parent.location.href='/';
        }
    }

};

doAjaxPostRequest = function (url, params) {
    var result = null;
    $.ajax({
        type: "POST",
        async: false,
        url: url,
        data: params,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (msg) {
            if(msg.result.code != 200) {
                result = msg.result.message;
            }
        },
        error: function () {
            result = "内部错误";
        }
    });
    return result;
};
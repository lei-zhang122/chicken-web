var TT = TT || {};
TT.blackUser = TT.blackUser || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.blackUser.addBlackUser = function(obj) {

    var addBlackUser = {} ;
    addBlackUser.id = $("#id").val();
    addBlackUser.userId = $("#userId").val();
    addBlackUser.userName = $("#userName").val();
    addBlackUser.createTime = $("#createTime").val();
    addBlackUser.createUser = $("#createUser").val();

    var res = doAjaxPostRequest("/blackUser/insertBlackUser", JSON.stringify(addBlackUser));
    if(res == null) {
        alert("操作成功");
        window.location.href='/blackUser/blackUserPage'

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
var TT = TT || {};
TT.wechatUser = TT.wechatUser || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.wechatUser.addWechatUser = function(obj) {

    var wechatUser = {} ;
    wechatUser.id = $("#id").val();
    wechatUser.nickName = $("#nickName").val();
    wechatUser.openid = $("#openid").val();
    wechatUser.avatar = $("#avatar").val();
    wechatUser.unionid = $("#unionid").val();
    wechatUser.sex = $("input[name='sex']:checked").val();
    wechatUser.inviteNum = $("#inviteNum").val();
    wechatUser.regSource = $("#regSource").val();
    wechatUser.createTime = $("#createTime").val();
    wechatUser.cityName = $("#cityName").val();
    wechatUser.status = $("input[name='status']:checked").val();

    var res = doAjaxPostRequest("/wechatUser/insertWechatUser", JSON.stringify(wechatUser));
    if(res == null) {
        alert("操作成功");
        window.location.href='/wechatUser/wechatUserPage'
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
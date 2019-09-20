var TT = TT || {};
TT.accountUser = TT.accountUser || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.accountUser.addAccountUser = function(obj) {

    var accountUser = {} ;
    accountUser.id = $("#id").val();
    accountUser.balance = $("#balance").val();
    accountUser.attentCount = $("#attentCount").val();
    accountUser.consumeCount = $("#consumeCount").val();
    accountUser.goodsCount = $("#goodsCount").val();
    accountUser.userId = $("#userId").val();
    accountUser.createTime = $("#createTime").val();
    accountUser.status = $("input[name='status']:checked").val();
console.log(accountUser);
    var res = doAjaxPostRequest("/accountUser/insertAccountUser", JSON.stringify(accountUser));
    if(res == null) {
        alert("操作成功");
        window.location.href='/accountUser/accountUserPage'
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
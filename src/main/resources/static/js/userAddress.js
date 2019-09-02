var TT = TT || {};
TT.userAddress = TT.userAddress || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.userAddress.addUserAddress = function(obj) {

    var addUserAddress = {} ;
    addUserAddress.id = $("#id").val();
    addUserAddress.contact = $("#contact").val();
    addUserAddress.phone = $("#phone").val();
    addUserAddress.userAddress = $("#userAddress").val();
    addUserAddress.userId = $("#userId").val();
    addUserAddress.createTime = $("#createTime").val();
    addUserAddress.status = $("input[name='status']:checked").val();

    var res = doAjaxPostRequest("/userAddress/insertUserAddress", JSON.stringify(addUserAddress));
    if(res == null) {
        alert("操作成功");
        window.location.href='/userAddress/userAddressPage'
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
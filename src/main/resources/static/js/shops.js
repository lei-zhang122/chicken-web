var TT = TT || {};
TT.shopsInfo = TT.shopsInfo || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.shopsInfo.addShopsInfo = function(obj) {
    console.log("开始维护用户");

    var shopsInfo = {} ;
    shopsInfo.id = $("#id").val();
    shopsInfo.brandId = $("#brandId").val();
    shopsInfo.shopsName = $("#shopsName").val();
    shopsInfo.address = $("#address").val();
    shopsInfo.scope = $("#scope").val();
    shopsInfo.contact = $("#contact").val();
    shopsInfo.contactPhone = $("#contactPhone").val();
    shopsInfo.status = $("input[name='status']:checked").val();
    shopsInfo.createUser = $("#createUser").val();
    shopsInfo.createTime = $("#createTime").val();
    shopsInfo.editTime = $("#editTime").val();


    var res = doAjaxPostRequest("/shops/insertShopsInfo", JSON.stringify(shopsInfo));
    if(res == null) {
        alert("操作成功");
        window.location.href='/shops/shopsInfoPage'
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
            if(msg.result.code != 0) {
                result = msg.result.message;
            }
        },
        error: function () {
            result = "内部错误";
        }
    });
    return result;
};
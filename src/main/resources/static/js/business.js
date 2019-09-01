var TT = TT || {};
TT.business = TT.business || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.business.addBusiness = function(obj) {

    var business = {} ;
    business.id = $("#id").val();
    business.interfaceType = $("#interfaceType").val();
    business.businessUid = $("#businessUid").val();
    business.businessDesc = $("#businessDesc").val();
    business.md5Salt = $("#md5Salt").val();
    business.infoState = $("input[name='infoState']:checked").val();
    business.linkMan = $("#linkMan").val();
    business.usableDate = $("#usableDate").val();
    business.limitDay = $("#limitDay").val();
    business.limitTotal = $("#limitTotal").val();
    business.createTime = $("#createTime").val();
    business.editTime = $("#editTime").val();

    business.callbackUrl = $("#callbackUrl").val();
    business.managerUsername = $("#managerUsername").val();
    business.managerPassword = $("#managerPassword").val();
    business.lexiconType = $("input[name='lexiconType']:checked").val();
    business.realTimeType = $("input[name='realTimeType']:checked").val();
    business.openImg = $("input[name='openImg']:checked").val();
    business.aesSalt = $("#aesSalt").val();
    business.scoreType = $("input[name='scoreType']:checked").val();

    var res = doAjaxPostRequest("/businessinfo/insertBusiness", JSON.stringify(business));
    if(res == null) {
        alert("操作成功");
        window.location.href='/businessinfo/businessPage'
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
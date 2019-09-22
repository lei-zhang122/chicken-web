var TT = TT || {};
TT.goodOrder = TT.goodOrder || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.goodOrder.addGoodOrder = function(obj) {

    var goodOrder = {} ;
    goodOrder.id = $("#id").val();
    goodOrder.orderNum = $("#orderNum").val();
    goodOrder.userId = $("#userId").val();
    goodOrder.createTime = $("#createTime").val();
    goodOrder.exchangeTime = $("#exchangeTime").val();
    goodOrder.exchangeStatus = $("#exchangeStatus").val();
    goodOrder.expressName = $("#expressName").val();
    goodOrder.expressNum = $("#expressNum").val();
    goodOrder.goodId = $("#goodId").val();
    goodOrder.addressId = $("#addressId").val();
    goodOrder.score = $("#score").val();
    goodOrder.status = $("#status").val();

    var res = doAjaxPostRequest("/goodOrder/insertGoodOrder", JSON.stringify(goodOrder));
    if(res == null) {
        alert("操作成功");
        window.location.href='/goodOrder/goodOrderPage'
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
var TT = TT || {};
TT.sysRole = TT.sysRole || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.sysRole.addSysRole = function(obj) {
    console.log("开始维护用户");

    var sysRole = {} ;
    sysRole.id = $("#id").val();
    sysRole.role = $("#role").val();
    sysRole.description = $("#description").val();
    sysRole.available = $("input[name='available']:checked").val();



    var res = doAjaxPostRequest("/sysrole/insertSysRole", JSON.stringify(sysRole));
    if(res == null) {
        alert("操作成功");
        window.location.href='/sysrole/sysRolePage'
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
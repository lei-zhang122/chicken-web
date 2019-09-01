var TT = TT || {};
TT.user = TT.user || {};

$(document).ready(function () {
    //页面渲染完成执行的js代码
});

TT.user.addUser = function(obj) {
    console.log("开始维护用户");

    var user = {} ;
    user.id = $("#id").val();
    user.userName = $("#userName").val();
    user.userPwd = $("#userPwd").val();
    user.loginName = $("#loginName").val();
    user.phone = $("#phone").val();
    user.remark = $("#remark").val();
    user.status = $("input[name='status']:checked").val();
    user.createUser = $("#createUser").val();
    user.createTime = $("#createTime").val();
    user.editTime = $("#editTime").val();

    var res = doAjaxPostRequest("/user/insertUser", JSON.stringify(user));
    if(res == null) {
        alert("操作成功");
        window.location.href='/user/userPage'
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

TT.user.resetPwd = function(obj) {
    console.log("开始修改用户密码");

    var user = {} ;
    user.id = $("#id").val();
    user.userPwd = $("#userPwd").val();

    var res = doAjaxPostRequest("/user/resetPwdUser", JSON.stringify(user));
    if(res == null) {
        alert("操作成功");
        window.location.href='/user/userPage'
    }else {
        alert(res);
        if(res=="请登陆之后，再操作。"){
            window.parent.location.href='/';
        }
    }

};
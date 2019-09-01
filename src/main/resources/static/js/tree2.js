function createNoesAjax(val) {
    var result = null;
    $.ajax({
        type: "GET",
        async: false,
        url: '/sysrole/getAllTree/'+val,
        data: null,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            result = data;
        },
        error: function () {
            result = null;
        }
    });
    console.log(result)
    return result;
}

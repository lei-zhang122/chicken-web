//跳转至黑名单添加页面
// function goAdd() {
//     window.location.href = "/blackText/addBlackTextUI";
// }


//选项卡
window.onload=function () {
    /*var oUl=document.getElementById('left-sider-ul');
    var aDiv=oUl.getElementsByTagName('div');
    var aUl=oUl.getElementsByTagName('ul');

    //$("#div的id").children("ul:first")
    
        for(var i=0;i<aDiv.length;i++){
            aDiv[i].index=i;

            aDiv[i].onclick=function (){
                for(var i=0;i<aDiv.length;i++){
                    aUl[i].style.display='none';
                }
                aUl[this.index].style.display='block';
            };
        }*/
    var urlTag = $("#menu_left").html();
    if("1"==urlTag) {
        $("#liCiku").addClass("is-active");
        $("#unm1").css("display", "block");
        $("#unm").css("display", "none");
        $("#unm2").css("display", "none");

        $("#liBlack").removeClass("is-active");
        $("#liWhite").removeClass("is-active");
        $("#liUser").removeClass("is-active");
    } else if("2"==urlTag) {
        $("#liCiku").removeClass("is-active");
        $("#unm").css("display", "block");
        $("#unm1").css("display", "none");
        $("#unm2").css("display", "none");

        $("#liBlack").addClass("is-active");
        $("#liWhite").removeClass("is-active");
        $("#liUser").removeClass("is-active");
    } else if("3"==urlTag) {
        $("#liCiku").removeClass("is-active");
        $("#unm").css("display", "block");
        $("#unm1").css("display", "none");
        $("#unm2").css("display", "none");

        $("#liBlack").removeClass("is-active");
        $("#liWhite").addClass("is-active");
        $("#liUser").removeClass("is-active");
    } else if("4"==urlTag){
        $("#liUser").addClass("is-active");
        $("#unm2").css("display", "block");
        $("#unm1").css("display", "none");
        $("#unm").css("display", "none");


        $("#liBlack").removeClass("is-active");
        $("#liWhite").removeClass("is-active");
        $("#liCiku").removeClass("is-active");
    }

    $("#thesaurusDiv").click(function () {
        $("#unm1").css("display", "block");
        $("#unm").css("display", "none");
        $("#unm2").css("display", "none");
    });
    $("#blackTextDiv").click(function () {
        $("#unm").css("display", "block");
        $("#unm1").css("display", "none");
        $("#unm2").css("display", "none");
    });
    $("#sysDiv").click(function () {
        $("#unm2").css("display", "block");
        $("#unm").css("display", "none");
        $("#unm1").css("display", "none");

    });

    //信息监听
    var message=$("#message").val();
    if(message!='' && message.length>0){
        alert(message);
    }
};


/**
 * 跳转至指定页面
 * @param path
 */
function goAdd(path) {
    // alert(path);
    window.location.href = path;
}

/**
 * 显示隐藏
 * @param eleId 被控制元素id
 * @param meleId 控制元素id
 */
function showAndHidden(eleId,meleId){
    var ele = document.getElementById(eleId);
    var mele = document.getElementById(meleId);
    var eleStyle = window.getComputedStyle(ele, null).getPropertyValue("display");
    if(eleStyle == "block"){
        //隐藏
        //ele.setAttribute("display","none");
        ele.style.display = 'none';
        mele.style.backgroundColor = "#2d3647";
    }else{
        //显示
        //ele.setAttribute("display","block");
        ele.style.display = 'block';
        mele.style.backgroundColor = "#354052";
    }
}
/**
 * 根据id去对应页面
 * @param id
 * @param path
 * @returns {boolean}
 */
function goPathById(id,path) {
    var url = path + '?id=' + id;
    //判断访问的URL中是否包含del
    if(path.search('del') != -1){
        if(window.confirm("确认执行此操作?")){
            window.location.href = url;
        }else{
            return false;
        }
    }
    window.location.href = url;
}

/**
 * 根据id去对应页面
 * @param id
 * @param path
 * @returns {boolean}
 */
function delByPathAndId(id,path,page) {
    var url = path + '?id=' + id;

    if(window.confirm("确认执行此操作?")){

        if(page == '' || typeof page == "undefined")  page = 1;

        var text = $("#text").val();
        if(typeof text == "undefined") text = "";

        var businessId = $("#businessId").val();
        if(typeof businessId == "undefined") businessId = "";
        //带上页面条件
        url = url + "&businessId=" + businessId + "&queryStr=" + text + "&page=" + page;

        window.location.href = url;
    }else{
        return false;
    }
}

/**
 * excel导出
 * @param path
 * @param type  下载类型（1：词条）
 */
function goDownLoad(path) {
    var wordState = $("#wordState").val();
    if(typeof wordState == "undefined") wordState='';
    var thesaurusId = $("#thesaurusId").val();
    if(typeof thesaurusId == "undefined") thesaurusId='';
    var wordText = $("#wordText").val();
    if(typeof wordText == "undefined") wordText='';

    var url = path + '?wordState=' + wordState + '&thesaurusId=' + thesaurusId + '&wordText=' + wordText;

    // alert(url);

    window.location.href = url;
}

/**
 * 屏蔽与开启
 * @param id
 * @param path
 * @param page
 */
function openAndCloseById(id,path,page){

    var url = path + '?id=' + id;

    if(window.confirm("确认执行此操作?")){

        if(page == '' || typeof page == "undefined"){
            page = 1;
        }
        var dateTime = $("#dateTime").val();
        if(typeof dateTime == "undefined") dateTime = "";
        var wordState = $("#wordState").val();
        if(typeof wordState == "undefined") wordState = "";
        var thesaurusId = $("#thesaurusId").val();
        if(typeof thesaurusId == "undefined") thesaurusId = "";
        var wordText = $("#wordText").val();
        if(typeof wordText == "undefined") wordText = "";
        //带上页面条件
        url = url + '&dateTime=' + dateTime+ '&wordState=' + wordState + '&thesaurusId=' + thesaurusId + '&wordText=' + wordText + '&page=' + page;//这里pageSize先定为10

        // alert(url);
        window.location.href = url;
    }else{
        return false;
    }
}

/**
 * 条件复合查询词条(加分页)
 * @param path
 * @param page
 */
function goFindWordListByPageQuery(path,page){

    if(page == '' || typeof page == "undefined"){
        page = 1;
    }
    var dateTime = $("#dateTime").val();
    if(typeof dateTime == "undefined") dateTime = "";
    var wordState = $("#wordState").val();
    if(typeof wordState == "undefined") wordState = "";
    var thesaurusId = $("#thesaurusId").val();
    if(typeof thesaurusId == "undefined") thesaurusId = "";
    var wordText = $("#wordText").val();
    if(typeof wordText == "undefined") wordText = "";

    var url = path + '?dateTime=' + dateTime + '&wordState=' + wordState + '&thesaurusId=' + thesaurusId + '&wordText=' + wordText + '&page=' + page + '&pageSize=' + 10;//这里pageSize先定为10

    // alert(url);
    window.location.href = url;
}

/**
 * 条件复合查询词库(加分页)
 * @param path
 * @param page
 */
function goFindThesaurusByPageQuery(path,page) {
    if(page == '' || typeof page == "undefined"){
        page = 1;
    }
    var name = $("#name").val();
    if(typeof name == "undefined") name = "";

    var url = path + '?name=' + name + '&page=' + page + '&pageSize=' + 10;//这里pageSize先定为10

    // alert(url);
    window.location.href = url;
}

/**
 * 复合条件查询用户(加分页)
 * @param path
 * @param page
 */
function goFindUserInfoByPageQuery(path,page){
    if(page == '' || typeof page == "undefined"){
        page = 1;
    }
    var username = $("#username").val();
    if(typeof username == "undefined") username = "";

    var businessId = $("#businessId").val();
    if(typeof businessId == "undefined") businessId = "";

    var url = path + '?username=' + username + '&businessId=' + businessId + '&page=' + page + '&pageSize=' + 10;//这里pageSize先定为10
    
    // alert(url);
    window.location.href = url;
}

/**
 * 条件复合查询黑名单(加分页)
 * @param path
 * @param page
 */
function goFindBlackTextByPageQuery(path,page){
    if(page == '' || typeof page == "undefined"){
        page = 1;
    }
    var text = $("#text").val();
    if(typeof text == "undefined") text = "";

    var businessId = $("#businessId").val();
    if(typeof businessId == "undefined") businessId = "";

    var state = $("#state").val();
    if(typeof state == "undefined") state = "";

    var url = path + '?text=' + text + '&businessId=' + businessId + '&state=' + state + '&page=' + page + '&pageSize=' + 10;//这里pageSize先定为10

    // alert(url);
    window.location.href = url;
}
/**
 * 条件复合查询白名单名单(加分页)
 * @param path
 * @param page
 */
function goFindWhiteTextByPageQuery(path,page){
    
    if(page == '' || typeof page == "undefined"){
        page = 1;
    }

    var userId = $("#userId").val();
    if(typeof userId == "undefined") userId = "";

    var businessId = $("#businessId").val();
    if(typeof businessId == "undefined") businessId = "";

    var state = $("#state").val();
    if(typeof state == "undefined") state = "";
    
    var url = path + '?businessId=' + businessId + '&userId=' + userId+ '&state=' + state + '&page=' + page + '&pageSize=' + 10;//这里pageSize先定为10
    window.location.href = url;
}

/**
 * 登陆
 * @param path
 */
function goLogin(path){
    var phonenumber = $.trim($('#phonenumber').val());
    var password = $.trim($('#password').val());

    //手机号正则
    var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
    //电话
    if (password.length == 0) {
        alert('请输入账号！');
        return false;
    }else if(password.length == 0){
        alert("请输入密码！");
        return false;
    }

    return true;
}

/**
 * 条件复合查询黑名单
 * @param path
 */
function goLinkQueryBlackText(path){
    var text = $("#text").val();
    var businessName = $("#businessName").val();

    var url = path + '?text=' + text + '&businessName=' + businessName;
    window.location.href = url;
}

/**
 * 条件复合查询白名单名单
 * @param path
 */
function goLinkQueryWhiteText(path){
    var userId = $("#userId").val();
    var businessName = $("#businessName").val();

    var url = path + '?userId=' + userId + '&businessName=' + businessName;
    window.location.href = url;
}

/**
 * 去查询
 * @param path
 * @returns {boolean}
 */
function goQuery(path) {

    var queryId = $("#queryId").val();
    var queryStr = $("#queryStr").val();

    if(queryId=='' && queryStr.trim()!='') {
        alert("请选择查询类型！");
        return false;
    }else if(queryStr.trim()==''&& queryId!=''){
        alert("请输入查询内容！");
        return false;
    }else if(queryId==''&& queryStr.trim()==''){
        window.location.reload();
        return false;//不做查询或者查询列表
    }
    var url = path + '?queryId=' + queryId + '&queryStr=' + queryStr;
    window.location.href = url;
}

/**
 * 检查返回信息内容
 */
// function checkMessage(){
//     var message=$("#message").val();
//     if(message!='' && message.length>0){
//
//         alert(message);
//         // $("#message").val("");
//     }
// }

/**
 * 显示注销按钮
 */
function showDiv(){
    $("#logoutDiv").css("display","block");
}
/**
 * 隐藏注销按钮
 */
function hideDiv(){
    $("#logoutDiv").css("display","none");
}

function showLi(obj){
    alert(obj);
    obj.addClass("is-active")

}
function hideLi(obj){
    obj.removeClass("is-active")

}

const hiddenClass = 'hidden';

// 初始化 tab
function tabSwitch() {
  const $tabSwitch = $('[data-role="tabSwitch"]');
  const activeClass = 'activate';
  $tabSwitch.on('click', '[data-role="tab"]', (e) => {
    $tab = $(e.target);
    $tab.addClass(activeClass)
      .siblings().removeClass(activeClass);
  
    const idx = $tab.index();
    $tabSwitch.find('.tab-content > div')
      .eq(idx).removeClass(hiddenClass)
      .siblings().addClass(hiddenClass);
  });
}

// 初始化左侧菜单折叠
function menuToggle() {
  const $menu = $('.side-menu');
  $menu.on('click', 'h3', (e) => {
    $(e.target).next('.sub-menu').toggleClass(hiddenClass);
  });
}

function init() {
  tabSwitch();
  menuToggle();
}

init();

var TT = TT || {};
TT.mainPage = TT.mainPage || {};

TT.mainPage.bussinessListPage = function(obj) {

    var res = doAjaxGetRequest("/bussinesses/bussinessListPage", null);
    if(res=="请登陆之后，再操作。"){
        window.parent.location.href='/';
    }

};

doAjaxGetRequest = function (url, params) {
    var result;
    $.ajax({
        type: "GET",
        async: false,
        url: url,
        data: params,
        success: function (msg) {
            result = msg;
        },
        error: function () {
            result = null;
        }
    });
    return result;
};
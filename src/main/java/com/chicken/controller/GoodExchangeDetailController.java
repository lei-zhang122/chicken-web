package com.chicken.controller;

import com.chicken.service.AccountDetailService;
import com.chicken.service.GoodInfoService;
import com.chicken.service.WechatUserService;
import com.chicken.util.ContantUtil;
import com.chicken.vo.AccountDetailRequest;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-04 19:08
 */
@Controller
@RequestMapping("goodExchange")
public class GoodExchangeDetailController {

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    AccountDetailService accountDetailService;

    @Autowired
    GoodInfoService goodInfoService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/goodExchangePage")
    @RequiresPermissions("goodExchangePage")
    public Object goodExchangePage(Model model) {

        AccountDetailRequest info = new AccountDetailRequest();
        info.setDetailFlag("4");
        PageInfo<Map> result = this.accountDetailService.selectByAccountDetail(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "goodinfo/goodExchangeList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodExchangePageList", method = RequestMethod.POST)
    public Object goodExchangePageList(@ModelAttribute AccountDetailRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }
        info.setDetailFlag("4");
        PageInfo<Map> result = this.accountDetailService.selectByAccountDetail(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "goodinfo/goodExchangeList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, AccountDetailRequest info, PageInfo<Map> result) {
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("remark", info.getRemark());
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("nickName", info.getNickName());
        model.addAttribute("detailFlag", info.getDetailFlag());
    }
}

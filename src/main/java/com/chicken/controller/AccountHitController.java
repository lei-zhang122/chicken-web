package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.service.AccountHitService;
import com.chicken.service.AccountSignedService;
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
 * @date 2019-09-02 17:42
 */
@Controller
@RequestMapping("accountHit")
public class AccountHitController extends BaseController {


    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    AccountHitService accountHitService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/accountHitPage")
    @RequiresPermissions("accountHitPage")
    public Object accountHitPage(Model model) {

        AccountDetailRequest info = new AccountDetailRequest();
        PageInfo<Map> result = this.accountHitService.selectByAccountHit(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "accountdetail/accountHitList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/accountHitPageList", method = RequestMethod.POST)
    public Object accountHitPageList(@ModelAttribute AccountDetailRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.accountHitService.selectByAccountHit(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "accountdetail/accountHitList";
    }

    /**
     * 保存公共部分
     *
     * @param model
     * @param info
     */
    private void saveModel(Model model, AccountDetailRequest info, PageInfo<Map> result) {
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("list", result.getList());
        model.addAttribute("countPage", result.getPages());
        model.addAttribute("currentPage", result.getPageNum());
        model.addAttribute("nickName", info.getNickName());
    }
}

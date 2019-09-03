package com.chicken.controller;

import com.chicken.base.BaseController;
import com.chicken.model.AccountDetail;
import com.chicken.model.AccountUser;
import com.chicken.model.WechatUser;
import com.chicken.service.AccountDetailService;
import com.chicken.service.AccountUserService;
import com.chicken.service.WechatUserService;
import com.chicken.util.AccountUserUtils;
import com.chicken.util.CallResult;
import com.chicken.util.CodeEnum;
import com.chicken.util.ContantUtil;
import com.chicken.vo.AccountDetailRequest;
import com.chicken.vo.AccountUserRequest;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-02 17:42
 */
@Controller
@RequestMapping("accountDetail")
public class AccountDetailController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WechatUserService wechatUserService;

    @Autowired
    AccountDetailService accountDetailService;

    /**
     * 进入查询列表页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/accountDetailPage")
    @RequiresPermissions("accountDetailPage")
    public Object accountDetailPage(Model model) {

        AccountDetailRequest info = new AccountDetailRequest();
        PageInfo<Map> result = this.accountDetailService.selectByAccountDetail(info, ContantUtil.DEFAULT_PAGE_NUM, ContantUtil.DEFAULT_PAGE_SIZE);
        saveModel(model, info, result);

        return "accountdetail/accountDetailList";
    }

    /**
     * 查询列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/accountDetailPageList", method = RequestMethod.POST)
    public Object accountDetailPageList(@ModelAttribute AccountDetailRequest info, Model model) {

        Integer pageNum = 0;
        if (null != info.getCurrentPage() && !"0".equals(info.getCurrentPage())) {
            pageNum = Integer.valueOf(info.getCurrentPage());
        }

        PageInfo<Map> result = this.accountDetailService.selectByAccountDetail(info, pageNum, ContantUtil.DEFAULT_PAGE_SIZE);

        saveModel(model, info, result);

        return "accountdetail/accountDetailList";
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
        model.addAttribute("userId", info.getUserId());
        model.addAttribute("nickName", info.getNickName());
        model.addAttribute("detailFlag", StringUtils.isBlank(info.getDetailFlag()) ? '0' : info.getDetailFlag());
    }
}
